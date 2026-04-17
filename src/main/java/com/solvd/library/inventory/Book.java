package com.solvd.library.inventory;

import com.solvd.library.Main;
import com.solvd.library.annotations.LibraryFeature;
import com.solvd.library.person.Author;
import com.solvd.library.person.Customer;
import com.solvd.library.organization.Publisher;
import com.solvd.library.workers.Supervisor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

@LibraryFeature("Physical borrowable item")
public class Book extends Inventory implements ItemWithPrice, BorrowableItem {

    private static final Logger log = LogManager.getLogger(Book.class);

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
            log.info("thanks, enjoy!");
        } else {
            throw new InsufficientFundsException("Sorry, you don't have enough funds");
        }
    }

    @Override
    public void returnItem() {

        getSupervisor().addInventoryItem(this);
        log.info("than you, come again!");
    }

}