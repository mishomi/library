package inventory;

import annotations.LibraryFeature;
import person.Author;
import person.Customer;
import organization.Publisher;
import workers.Supervisor;

import java.math.BigDecimal;

@LibraryFeature("Physical borrowable item")
public class Book extends Inventory implements ItemWithPrice, BorrowableItem {

    public Book(String name, Author author, Supervisor supervisor, BigDecimal price, Publisher publisher, Genre genre) {
        super(name, author, supervisor, publisher, genre, ItemType.BOOK, price);
    }

    @Override
    public void bookItem(Customer customer) throws ItemUnavailableException{

        if (this.getSupervisor() == null) {
            throw new ItemUnavailableException("Sorry, book not available!");
        }
        if (customer.getOutstandingFees().intValue() > 0) {
            if (customer.getMoney().intValue() >= customer.getOutstandingFees().intValue()) {
                customer.setMoney(customer.getMoney().subtract(customer.getOutstandingFees()));
                customer.setOutstandingFees(BigDecimal.valueOf(0));
            } else {
                throw new OutstandingFeesException("pay your fine first");
            }
        }
        if (customer.getMoney().compareTo(this.getPrice()) >= 0) {
            customer.setMoney(customer.getMoney().subtract(this.getPrice()));
            getSupervisor().removeInventoryItem(this);
            System.out.println("thanks, enjoy!");
        } else {
            throw new InsufficientFundsException("Sorry, you don't have enough funds");
        }
    }

    @Override
    public void returnItem() {

        getSupervisor().addInventoryItem(this);
        System.out.println("than you, come again!");
    }

}
