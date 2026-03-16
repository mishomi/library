package person;

import java.time.LocalDateTime;

public class LibraryCard {

    private Customer customer;
    private LocalDateTime expirationDate;

    public LibraryCard(Customer customer) {
        this.customer = customer;
        this.expirationDate = LocalDateTime.now().plusYears(3);
    }

    public String getInformation(Customer customer) {

        return "name: " + customer.getName() + ", age: " + customer.getAge() + ", card expiration date: " + expirationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "LibraryCard [customer = " + (customer != null ? customer.getName() : "unknown") + ", expires at " + expirationDate + "]";
    }
}