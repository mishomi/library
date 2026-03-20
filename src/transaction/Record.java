package transaction;

import inventory.Inventory;
import person.Customer;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class Record extends Transaction implements Describeable {


    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

    public Record(Customer customer, Inventory inventory) {
        super(customer, inventory);
        this.borrowDate = LocalDateTime.now();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    @Override
    public String describe() {
        return "Record[" + customer.getName() + " borrowed " + inventory.getName() + "]";
    }

    public String describe(boolean useDate) {
        if (useDate) {
            return "Record[" + customer.getName() + " borrowed " + inventory.getName() + "], borrowed at " + borrowDate;
        } else {
            return "Record[" + customer.getName() + " borrowed " + inventory.getName() + "]";
        }
    }
}