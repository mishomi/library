package inventory;

import person.Customer;

public interface BorrowableItem {

    void bookItem(Customer customer) throws ItemUnavailableException;

    void returnItem();
}