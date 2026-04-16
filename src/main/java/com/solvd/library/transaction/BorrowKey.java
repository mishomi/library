package com.solvd.library.transaction;

import com.solvd.library.inventory.Inventory;
import com.solvd.library.person.Customer;

import java.util.Objects;

public class BorrowKey {

    private final Customer customer;
    private final Inventory inventory;

    public BorrowKey(Customer customer, Inventory inventory) {
        this.customer = customer;
        this.inventory = inventory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorrowKey)) return false;
        BorrowKey borrowKey = (BorrowKey) o;
        return Objects.equals(customer, borrowKey.customer) &&
                Objects.equals(inventory, borrowKey.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, inventory);
    }
}