package transaction;

import inventory.Inventory;
import person.Customer;

public class BookingService {

    private static transaction.Record[] records ;
    private static int recordsPointer ;

    static {
        records = new Record[20];
        recordsPointer = 0;
    }
    public void book(Customer customer, Inventory inventory) {
        inventory.bookItem(customer);
        customer.takeItem(inventory);
        if (recordsPointer >= records.length) {
            transaction.Record[] temp = new transaction.Record[records.length * 2];
            System.arraycopy(records, 0, temp, 0, records.length);
            records = temp;
        }
        records[recordsPointer] = new Record(customer, inventory);
        recordsPointer++;
    }

    public void returnItem(Customer customer, Inventory inventory) {

        inventory.returnItem();
        customer.returnItem(inventory);
        for (int i = 0; i < recordsPointer; i++) {
            if (records[i].getCustomer() == customer && records[i].getInventory() == inventory && records[i].getReturnDate() == null) {
                records[i].returnItem();
                break;
            }
        }
    }

    public static int getOutstandingItemsCount() {
        return records.length;
    }

    public static void getReCords() {

        for (int i = 0; i < recordsPointer; i++) {
            System.out.println("book name: " + records[i].getInventory().getName() + ", customer name: " + records[i].getCustomer().getName());
        }
    }
}