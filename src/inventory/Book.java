package inventory;

import person.Author;
import person.Customer;
import organization.Publisher;
import workers.Supervisor;

import java.math.BigDecimal;

public class Book extends Inventory {

    private BigDecimal price;

    public Book(String name, Author author, Supervisor supervisor, BigDecimal price, Publisher publisher, Genre genre) {
        super(name, author, supervisor, publisher, genre);
        this.price = price;
    }

    @Override
    public void bookItem(Customer customer) {

        if (this.getSupervisor() == null) {
            System.out.println("sorry, book not available!");
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
        if (customer.getMoney().compareTo(price) >= 0) {
            customer.setMoney(customer.getMoney().subtract(price));
            getSupervisor().removeInventoryItem(this);
            System.out.println("thanks, enjoy!");
        } else {
            System.out.println("sorry, you dont have enough funds");
        }
    }

    @Override
    public void returnItem() {

        getSupervisor().addInventoryItem(this);
        System.out.println("than you, come again!");
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
