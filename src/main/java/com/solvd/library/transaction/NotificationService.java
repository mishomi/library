package com.solvd.library.transaction;

import com.solvd.library.person.Customer;

@FunctionalInterface
public interface NotificationService {
    void notify(Customer customer, String message);
}