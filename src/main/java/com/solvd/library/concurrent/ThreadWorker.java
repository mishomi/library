package com.solvd.library.concurrent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadWorker extends Thread {

    private static final Logger log = LogManager.getLogger(ThreadWorker.class);
    private final String taskName;

    public ThreadWorker(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        log.info("Thread task started: {}", taskName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread task interrupted: {}", taskName, e);
        }
        log.info("Thread task finished: {}", taskName);
    }
}