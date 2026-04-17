package com.solvd.library.transaction;

import com.solvd.library.reflection.LibraryReflection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LibrarySession implements AutoCloseable {

    private static final Logger log = LogManager.getLogger(LibrarySession.class);
    private final String sessionName;

    public LibrarySession(String sessionName) {
        this.sessionName = sessionName;
        log.info("Opening session: " + sessionName);
    }

    public void log(String message) {
        log.info("[" + sessionName + "] " + message);
    }

    @Override
    public void close() {
        log.info("Closing session: " + sessionName);
    }
}