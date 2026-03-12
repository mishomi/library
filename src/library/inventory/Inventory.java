package library.inventory;

import library.Author;
import library.Customer;
import library.Genre;
import library.Publisher;
import library.workers.Supervisor;

import java.time.LocalDateTime;

public abstract class Inventory {

    private String name;
    private Author author;
    private Supervisor supervisor;
    private LocalDateTime takenAt;
    private Publisher publisher;

    public Inventory(String name, Author author, Supervisor supervisor, Publisher publisher, Genre genre) {

        this.name = name;
        this.author = author;
        this.supervisor = supervisor;
        this.takenAt = null;
        publisher.addInventory(this);
        genre.addBook(this);
    }

    public abstract void bookItem(Customer customer);

    public abstract void returnItem();

    public void setTimeOfBooking(LocalDateTime localDateTime) {
        this.takenAt = localDateTime;
    }

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
}
