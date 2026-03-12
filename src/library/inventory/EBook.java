package library.inventory;

import library.Author;
import library.Customer;
import library.Genre;
import library.Publisher;
import library.workers.Supervisor;

import java.math.BigDecimal;

public class EBook extends Inventory {

    private String link;

    public EBook(String name, Author author, Supervisor supervisor, String link, Publisher publisher, Genre genre) {
        super(name, author, supervisor, publisher, genre);
        this.link = link;
    }

    @Override
    public void bookItem(Customer customer) {

        if (customer.getOutstandingFees().intValue() > 0) {
            if (customer.getMoney().intValue() >= customer.getOutstandingFees().intValue()) {
                customer.setMoney(customer.getMoney().subtract(customer.getOutstandingFees()));
                customer.setOutstandingFees(BigDecimal.valueOf(0));
            } else {
                System.out.println("pay your fine first");
                return;
            }
        }
        System.out.println("Download your E-library.inventory.Book here: " + link);
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