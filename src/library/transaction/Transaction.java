package library.transaction;

import library.inventory.Inventory;
import library.person.Customer;

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
    public abstract String describe();
}