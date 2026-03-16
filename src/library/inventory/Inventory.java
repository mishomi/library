package library.inventory;

import library.person.Author;
import library.person.Customer;
import library.organization.Publisher;
import library.workers.Supervisor;

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

    public void getAuthor() {
        System.out.println("changes i want hidden using git stash");
        return ;
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
