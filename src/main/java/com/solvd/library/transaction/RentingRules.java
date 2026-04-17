package com.solvd.library.transaction;

import com.solvd.library.person.Customer;
import com.solvd.library.reflection.LibraryReflection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public final class RentingRules {

    private static final Logger log = LogManager.getLogger(RentingRules.class);
    public static final int maxDaysToRent = 30;
    public static final BigDecimal lateFine = new BigDecimal("20");

    private RentingRules() {
    }

    public static boolean canPayFine(Customer customer) {
        return customer.getMoney().compareTo(customer.getOutstandingFees()) >= 0;
    }

    public static boolean hasOutstandingFees(Customer customer) {
        return customer.getOutstandingFees().compareTo(BigDecimal.ZERO) > 0;
    }

    public final void messageForCustomers() {
        log.info("Once you borrow an item, you have " + maxDaysToRent + "days to return it, otherwise you get a " + lateFine + " fine");
    }
}