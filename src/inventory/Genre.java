package inventory;

import java.util.LinkedHashSet;
import java.util.Set;

public class Genre {

    protected String name;
    private Set<Inventory> booksInThisGenre;

    public Genre(String name) {
        this.name = name;
        this.booksInThisGenre = new LinkedHashSet<>();
    }

    public void addBook(Inventory inventory) {

        if (booksInThisGenre.contains(inventory)) {
            System.out.println("book already accounted for");
            return;
        }
        booksInThisGenre.add(inventory);
    }

    public void removeBook(Inventory inventory){
        if (!booksInThisGenre.contains(inventory)){
            System.out.println("book already removed");
            return;
        }
        booksInThisGenre.remove(inventory);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Inventory> getBooksInThisGenre() {
        return booksInThisGenre;
    }

    public void setBooksInThisGenre(Set<Inventory> booksInThisGenre) {
        this.booksInThisGenre = booksInThisGenre;
    }

    public Inventory getFirstItem() {
        if (booksInThisGenre.isEmpty())
            return null;
        else
            return booksInThisGenre.iterator().next();
    }
}