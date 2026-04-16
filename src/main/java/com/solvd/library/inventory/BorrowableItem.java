package com.solvd.library.inventory;

import com.solvd.library.person.Customer;

public interface BorrowableItem {

    void bookItem(Customer customer) throws ItemUnavailableException;

    void returnItem();
}