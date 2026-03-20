package inventory;

import person.Customer;

public interface BorrowableItem {

    void bookItem(Customer customer);

    void returnItem();

}