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
    private Publisher publisher;

    public Inventory(String name, Author author, Supervisor supervisor, Publisher publisher, Genre genre) {
        this.name = name;
        this.author = author;
        this.supervisor = supervisor;
        publisher.addInventory(this);
        genre.addBook(this);
    }

    public abstract void bookItem(Customer customer);

    public abstract void returnItem();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author.getNationality();
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
}
