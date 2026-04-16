package com.solvd.library.transaction;

public class LibrarySession implements AutoCloseable {

    private final String sessionName;

    public LibrarySession(String sessionName) {
        this.sessionName = sessionName;
        System.out.println("Opening session: " + sessionName);
    }

    public void log(String message) {
        System.out.println("[" + sessionName + "] " + message);
    }

    @Override
    public void close() {
        System.out.println("Closing session: " + sessionName);
    }
}