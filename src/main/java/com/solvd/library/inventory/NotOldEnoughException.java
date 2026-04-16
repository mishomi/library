package com.solvd.library.inventory;

public class NotOldEnoughException extends RuntimeException {

    public NotOldEnoughException(String message) {
        super(message);
    }
}