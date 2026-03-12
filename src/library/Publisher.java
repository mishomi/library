package library;

import library.inventory.Inventory;

public class Publisher {

    private String name;
    private String country;
    private Inventory[] publishedBooks;
    private int pointer = 0;

    public Publisher(String name, String country) {

        this.country = country;
        this.name = name;
        this.publishedBooks = new Inventory[20];
        this.pointer = 0;
    }

    public void addInventory(Inventory inventory) {

        for (int i = 0; i < pointer; i++) {
            if (publishedBooks[i] == inventory) {
                System.out.println("book already accounted for");
                return;
            }
        }
        if (pointer >= publishedBooks.length) {
            Inventory[] temp = new Inventory[publishedBooks.length * 2];
            System.arraycopy(publishedBooks, 0, temp, 0, publishedBooks.length);
            publishedBooks = temp;
        }
        publishedBooks[pointer] = inventory;
        pointer++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Inventory[] getPublishedBooks() {
        return publishedBooks;
    }

    public void setPublishedBooks(Inventory[] publishedBooks) {
        this.publishedBooks = publishedBooks;
    }
}