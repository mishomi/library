package com.solvd.library.organization;

import com.solvd.library.inventory.Inventory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashSet;
import java.util.Set;

public class Publisher extends Organization {

    private static final Logger log = LogManager.getLogger(Publisher.class);
    private String country;
    private Set<Inventory> publishedBooks;

    public Publisher(String name, String country) {
        super(name);
        this.country = country;
        this.name = name;
        this.publishedBooks = new LinkedHashSet<>();
    }

    public void addInventory(Inventory inventory) {

        if (publishedBooks.contains(inventory)) {
            log.warn("book already accounted for");
            return;
        }
        publishedBooks.add(inventory);
    }

    public void removeInventory(Inventory inventory) {
        if (!publishedBooks.contains(inventory)) {
            log.warn("book already removed");
            return;
        }
        publishedBooks.remove(inventory);
    }

    @Override
    public String toString() {
        return "Publisher[name=" + name + ", country=" + country + "]";
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Inventory> getPublishedBooks() {
        return publishedBooks;
    }

    public void setPublishedBooks(Set<Inventory> publishedBooks) {
        this.publishedBooks = publishedBooks;
    }

    public String getDescription() {
        return "Publisher " + name + " from " + country;
    }

    public Inventory getFirstItem() {
        return publishedBooks.stream()
                .findFirst()
                .orElse(null);
    }
}