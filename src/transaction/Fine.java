package transaction;

import person.Customer;

import java.math.BigDecimal;

public class Fine implements Describeable {

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

    @Override
    public String toString() {
        return "Fine [amount = " + amount + ", customer = " + (customer != null ? customer.getName() : "unknown") + "]";
    }

    @Override
    public String describe() {
        return "Fine [amount = " + amount + ", customer = " + (customer != null ? customer.getName() : "unknown") + "]";
    }
}