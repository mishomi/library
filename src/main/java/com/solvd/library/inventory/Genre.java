package com.solvd.library.inventory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashSet;
import java.util.Set;

public class Genre {

    private static final Logger log = LogManager.getLogger(Genre.class);
    protected String name;
    private Set<Inventory> booksInThisGenre;
    private GenreType genreType;

    public Genre(String name, GenreType genreType) {
        this.name = name;
        this.genreType = genreType;
        this.booksInThisGenre = new LinkedHashSet<>();
    }

    public void addBook(Inventory inventory) {

        if (booksInThisGenre.contains(inventory)) {
            log.warn("book already accounted for");
            return;
        }
        booksInThisGenre.add(inventory);
    }

    public void removeBook(Inventory inventory) {
        if (!booksInThisGenre.contains(inventory)) {
            log.warn("book already removed");
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
        return booksInThisGenre.stream()
                .findFirst()
                .orElse(null);
    }
}