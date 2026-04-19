package com.solvd.library.concurrent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunnableWorker implements Runnable {

    private static final Logger log = LogManager.getLogger(RunnableWorker.class);
    private final String taskName;

    public RunnableWorker(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        log.info("Runnable task started: {}", taskName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Runnable task interrupted: {}", taskName, e);
        }
        log.info("Runnable task finished: {}", taskName);
    }
}