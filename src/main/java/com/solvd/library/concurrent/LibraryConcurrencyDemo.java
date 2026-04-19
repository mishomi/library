package com.solvd.library.concurrent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class LibraryConcurrencyDemo {

    private static final Logger log = LogManager.getLogger(LibraryConcurrencyDemo.class);

    private LibraryConcurrencyDemo() {
    }

    public static void runDemo() {
        Thread runnableThread = new Thread(new RunnableWorker("shelf audit"), "Runnable-Thread");
        Thread threadWorker = new ThreadWorker("catalog backup");
        threadWorker.setName("Thread-Worker");

        runnableThread.start();
        threadWorker.start();

        LibraryTerminalPool pool = LibraryTerminalPool.getInstance(5);
        ExecutorService executor = Executors.newFixedThreadPool(7);

        for (int i = 1; i <= 7; i++) {
            int customerNumber = i;
            executor.submit(() -> {
                LibraryTerminal terminal = null;
                try {
                    terminal = pool.getConnection();
                    log.info("Customer {} is using {}", customerNumber, terminal);
                    log.info(terminal.searchCatalog("Book " + customerNumber));
                    Thread.sleep(2000);
                    log.info(terminal.borrowBook("Book " + customerNumber));
                    log.info(terminal.returnBook("Book " + customerNumber));
                    log.info(terminal.reserveBook("Book " + customerNumber));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.error("Customer {} interrupted", customerNumber, e);
                } finally {
                    pool.releaseConnection(terminal);
                }
            });
        }

        executor.shutdown();

        try {
            runnableThread.join();
            threadWorker.join();
            if (!executor.awaitTermination(10, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executor.shutdownNow();
            log.error("Concurrency demo interrupted", e);
        }

        log.info("Library concurrency demo finished");
    }
}