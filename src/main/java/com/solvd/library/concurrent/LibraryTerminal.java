package com.solvd.library.concurrent;

public class LibraryTerminal {

    private final int id;

    public LibraryTerminal(int id) {
        this.id = id;
    }

    public String searchCatalog(String query) {
        return "Terminal " + id + " searched catalog for: " + query;
    }

    public String borrowBook(String bookTitle) {
        return "Terminal " + id + " borrowed: " + bookTitle;
    }

    public String returnBook(String bookTitle) {
        return "Terminal " + id + " returned: " + bookTitle;
    }

    public String reserveBook(String bookTitle) {
        return "Terminal " + id + " reserved: " + bookTitle;
    }

    @Override
    public String toString() {
        return "LibraryTerminal-" + id;
    }
}