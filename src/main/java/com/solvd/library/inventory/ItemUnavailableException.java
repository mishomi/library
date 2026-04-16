package com.solvd.library.inventory;

public class ItemUnavailableException extends Exception {

    public ItemUnavailableException(String message) {
        super(message);
    }
}