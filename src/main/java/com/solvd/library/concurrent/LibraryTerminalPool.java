package com.solvd.library.concurrent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class LibraryTerminalPool {

    private static final Logger log = LogManager.getLogger(LibraryTerminalPool.class);
    private static volatile LibraryTerminalPool instance;

    private final BlockingQueue<LibraryTerminal> terminals;

    private LibraryTerminalPool(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Pool size must be positive");
        }
        this.terminals = new ArrayBlockingQueue<>(size);
        for (int i = 1; i <= size; i++) {
            terminals.offer(new LibraryTerminal(i));
        }
    }

    public static LibraryTerminalPool getInstance(int size) {
        if (instance == null) {
            synchronized (LibraryTerminalPool.class) {
                if (instance == null) {
                    instance = new LibraryTerminalPool(size);
                }
            }
        }
        return instance;
    }

    public LibraryTerminal getConnection() throws InterruptedException {
        LibraryTerminal terminal = terminals.take();
        log.info("Acquired {}", terminal);
        return terminal;
    }

    public void releaseConnection(LibraryTerminal terminal) {
        if (terminal == null) {
            return;
        }
        terminals.offer(terminal);
        log.info("Released {}", terminal);
    }
}