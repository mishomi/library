package com.solvd.library.transaction;

import com.solvd.library.inventory.Inventory;
import com.solvd.library.person.Customer;

public abstract class Transaction {
    protected Customer customer;
    protected Inventory inventory;

    protected Transaction(Customer customer, Inventory inventory) {
        this.customer = customer;
        this.inventory = inventory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Inventory getInventory() {
        return inventory;
    }
}