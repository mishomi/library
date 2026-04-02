package inventory;

import person.Author;
import person.Customer;
import organization.Publisher;
import workers.Supervisor;

import java.math.BigDecimal;

public class EBook extends Inventory implements BorrowableItem {

    private String link;

    public EBook(String name, Author author, Supervisor supervisor, String link, Publisher publisher, Genre genre) {
        super(name, author, supervisor, publisher, genre, ItemType.EBOOK, BigDecimal.valueOf(0));
        this.link = link;
    }

    @Override
    public void bookItem(Customer customer) throws ItemUnavailableException{

        if (customer.getOutstandingFees().intValue() > 0) {
            if (customer.getMoney().intValue() >= customer.getOutstandingFees().intValue()) {
                customer.setMoney(customer.getMoney().subtract(customer.getOutstandingFees()));
                customer.setOutstandingFees(BigDecimal.valueOf(0));
            } else {
                throw new OutstandingFeesException("Pay your fine first");
            }
        }
        System.out.println("Download your E-inventory.Book here: " + link);
    }

    @Override
    public void returnItem() {
        System.out.println("E-Books do not need to be returned.");
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}