package inventory;

import person.Author;
import person.Customer;
import organization.Publisher;
import workers.Supervisor;

import java.math.BigDecimal;

public class Movie extends Inventory implements ItemWithPrice, BorrowableItem {

    private int minimumRequiredAge;

    public Movie(String name, Author author, BigDecimal price, Supervisor supervisor, int minimumRequiredAge, Publisher publisher, Genre genre) {
        super(name, author, supervisor, publisher, genre, ItemType.MOVIE, price);
        this.minimumRequiredAge = minimumRequiredAge;
    }

    @Override
    public void bookItem(Customer customer) throws ItemUnavailableException{

        if (this.getSupervisor() == null) {
            throw new ItemUnavailableException("Sorry, movie not available!");
        }

        if (customer.getOutstandingFees().intValue() > 0) {
            if (customer.getMoney().intValue() >= customer.getOutstandingFees().intValue()) {
                customer.setMoney(customer.getMoney().subtract(customer.getOutstandingFees()));
                customer.setOutstandingFees(BigDecimal.valueOf(0));
            } else {
                throw new OutstandingFeesException("Pay your fine first");
            }
        }

        if (customer.getAge() < minimumRequiredAge) {
            throw new NotOldEnoughException("Sorry, choose a more age appropriate movie");

        }

        if (customer.getMoney().intValue() < this.getPrice().intValue()) {
            throw new InsufficientFundsException("Not enough money");
        }

        customer.setMoney(customer.getMoney().subtract(this.getPrice()));
        getSupervisor().removeInventoryItem(this);

        System.out.println("thank you, enjoy!");
    }

    @Override
    public void returnItem() {

        getSupervisor().addInventoryItem(this);
        System.out.println("thank you, come again!");
    }

    public int getMinimumRequiredAge() {
        return minimumRequiredAge;
    }

    public void setMinimumRequiredAge(int minimumRequiredAge) {
        this.minimumRequiredAge = minimumRequiredAge;
    }
}