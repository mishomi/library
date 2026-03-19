package transaction;

import person.Customer;

import java.math.BigDecimal;

public final class RentingRules {

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
    public final void messageForCustomers(){
        System.out.println("Once you borrow an item, you have " + maxDaysToRent + "days to return it, otherwise you get a " + lateFine + " fine");
    }
}