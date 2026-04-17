package com.solvd.library.inventory;

import com.solvd.library.Main;
import com.solvd.library.person.Author;
import com.solvd.library.person.Customer;
import com.solvd.library.organization.Publisher;
import com.solvd.library.workers.Supervisor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Movie extends Inventory implements ItemWithPrice, BorrowableItem {

    private static final Logger log = LogManager.getLogger(Movie.class);
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

        log.info("thank you, enjoy!");
    }

    @Override
    public void returnItem() {

        getSupervisor().addInventoryItem(this);
        log.info("thank you, come again!");
    }

    public int getMinimumRequiredAge() {
        return minimumRequiredAge;
    }

    public void setMinimumRequiredAge(int minimumRequiredAge) {
        this.minimumRequiredAge = minimumRequiredAge;
    }
}