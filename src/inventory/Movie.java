package inventory;

import person.Author;
import person.Customer;
import organization.Publisher;
import workers.Supervisor;

import java.math.BigDecimal;

public class Movie extends Inventory {

    private BigDecimal price;
    private int minimumRequiredAge;

    public Movie(String name, Author author, BigDecimal price, Supervisor supervisor, int minimumRequiredAge, Publisher publisher, Genre genre) {
        super(name, author, supervisor, publisher, genre);
        this.price = price;
        this.minimumRequiredAge = minimumRequiredAge;
    }

    @Override
    public void bookItem(Customer customer) {

        if (this.getSupervisor() == null) {
            System.out.println("sorry, movie not available!");
            return;
        }

        if (customer.getOutstandingFees().intValue() > 0) {
            if (customer.getMoney().intValue() >= customer.getOutstandingFees().intValue()) {
                customer.setMoney(customer.getMoney().subtract(customer.getOutstandingFees()));
                customer.setOutstandingFees(BigDecimal.valueOf(0));
            } else {
                System.out.println("pay your fine first");
                return;
            }
        }

        if (customer.getAge() < minimumRequiredAge) {
            System.out.println("sorry, choose a more age appropriate movie");
            return;
        }

        if (customer.getMoney().intValue() < price.intValue()) {
            System.out.println("Not enough money");
            return;
        }

        customer.setMoney(customer.getMoney().subtract(price));
        getSupervisor().removeInventoryItem(this);

        System.out.println("thank you, enjoy!");
    }

    @Override
    public void returnItem() {

        getSupervisor().addInventoryItem(this);
        System.out.println("thank you, come again!");
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getMinimumRequiredAge() {
        return minimumRequiredAge;
    }

    public void setMinimumRequiredAge(int minimumRequiredAge) {
        this.minimumRequiredAge = minimumRequiredAge;
    }
}