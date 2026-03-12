package library;

import java.math.BigDecimal;

public class Fine {

    private BigDecimal amount;
    private Customer customer;

    public Fine(BigDecimal amount, Customer customer) {
        this.amount = amount;
        this.customer = customer;
        customer.setOutstandingFees(customer.getOutstandingFees().add(amount));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}