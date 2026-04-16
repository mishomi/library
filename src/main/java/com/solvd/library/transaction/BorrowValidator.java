package com.solvd.library.transaction;

import com.solvd.library.inventory.Inventory;
import com.solvd.library.person.Customer;

@FunctionalInterface
public interface BorrowValidator {
    boolean canBorrow(Customer customer, Inventory inventory);
}