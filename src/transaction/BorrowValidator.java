package transaction;

import inventory.Inventory;
import person.Customer;

@FunctionalInterface
public interface BorrowValidator {
    boolean canBorrow(Customer customer, Inventory inventory);
}