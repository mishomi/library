package com.solvd.library.inventory;

import com.solvd.library.person.Author;
import com.solvd.library.person.Customer;
import com.solvd.library.organization.Publisher;
import com.solvd.library.workers.Supervisor;

import java.math.BigDecimal;

public abstract class Inventory {

    private String name;
    private Author author;
    private Supervisor supervisor;
    private Publisher publisher;
    private Genre genre;
    private ItemType itemType;
    private BigDecimal price;

    public Inventory(String name, Author author, Supervisor supervisor, Publisher publisher, Genre genre, ItemType itemType, BigDecimal price) {
        this.name = name;
        this.author = author;
        this.supervisor = supervisor;
        this.publisher = publisher;
        this.genre = genre;
        this.itemType = itemType;
        this.price = price;
        publisher.addInventory(this);
        genre.addBook(this);
    }

    public abstract void bookItem(Customer customer) throws ItemUnavailableException;

    public abstract void returnItem();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Inventory)) return false;

        Inventory inv = (Inventory) object;

        if (name != null ? !name.equals(inv.name) : inv.name != null) return false;
        return author != null ? author.equals(inv.author) : inv.author == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}