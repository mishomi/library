package com.solvd.library.transaction;

public class NotAvailableException extends RuntimeException {

    public NotAvailableException(String message) {
        super(message);
    }
}