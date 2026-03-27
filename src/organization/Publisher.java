package organization;

import inventory.Inventory;

import java.util.LinkedHashSet;
import java.util.Set;

public class Publisher extends Organization {

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
            System.out.println("book already accounted for");
            return;
        }
        publishedBooks.add(inventory);
    }

    public void removeInventory(Inventory inventory){
        if (!publishedBooks.contains(inventory)){
            System.out.println("book already removed");
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
        return publishedBooks.isEmpty() ? null : publishedBooks.iterator().next();
    }
}