package library;

import library.inventory.Inventory;

import java.time.LocalDateTime;

public class BookingService {

    private static Record[] records = new Record[20];
    private static int recordsPointer = 0;
    private static int countOutstandingItems = 0;

    public void book(Customer customer, Inventory inventory) {

        inventory.bookItem(customer);
        inventory.setTimeOfBooking(LocalDateTime.now());
        customer.takeItem(inventory);
        if (recordsPointer >= records.length) {
            Record[] temp = new Record[records.length * 2];
            System.arraycopy(records, 0, temp, 0, records.length);
            records = temp;
        }
        countOutstandingItems++;
        records[recordsPointer] = new Record(customer, inventory);
        recordsPointer++;
    }

    public void returnItem(Customer customer, Inventory inventory) {

        inventory.returnItem();
        inventory.setTimeOfBooking(null);
        countOutstandingItems--;
        customer.returnItem(inventory);
        for (int i = 0; i < recordsPointer; i++) {
            if (records[i].getCustomer() == customer && records[i].getInventory() == inventory && records[i].getReturnDate() == null) {
                records[i].returnItem();
                break;
            }
        }
    }

    public static int getOutstandingItemsCount() {
        return countOutstandingItems;
    }

    public static void getReCords() {

        for (int i = 0; i < recordsPointer; i++) {
            System.out.println("book name: " + records[i].getInventory().getName() + ", customer name: " + records[i].getCustomer().getName());
        }
    }
}