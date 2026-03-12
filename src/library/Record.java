package library;

import library.inventory.Inventory;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class Record {

    private Customer customer;
    private Inventory inventory;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

    public Record(Customer customer, Inventory inventory) {

        this.customer = customer;
        this.inventory = inventory;
        this.borrowDate = LocalDateTime.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public void returnItem() {

        returnDate = LocalDateTime.now();
        if (Duration.between(borrowDate, returnDate).toDays() > 30) {
            Fine fine = new Fine(BigDecimal.valueOf(20), customer);
        }
    }
}