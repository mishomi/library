package com.solvd.library.inventory;

import com.solvd.library.person.Author;
import com.solvd.library.person.Customer;
import com.solvd.library.organization.Publisher;
import com.solvd.library.workers.Supervisor;

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
        System.out.println("Download your E-main.java.com.solvd.library.inventory.Book here: " + link);
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