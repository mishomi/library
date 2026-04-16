package com.solvd.library.transaction;

import com.solvd.library.inventory.Inventory;
import com.solvd.library.inventory.ItemUnavailableException;
import com.solvd.library.person.Customer;

import java.util.HashMap;
import java.util.Map;

public class BookingService {
    public Log log = new Log();
    private static Map<BorrowKey, Record> records;
    private static int recordsPointer;

    static {
        records = new HashMap<>();
        recordsPointer = 0;
    }

    public void book(Customer customer, Inventory inventory) throws ItemUnavailableException {
        BorrowKey borrowKey = new BorrowKey(customer, inventory);
        if (records.containsKey(borrowKey) ){
            Record existing = records.get(borrowKey);
            if (existing.getReturnDate() == null){
                throw new NotAvailableException("This item is already borrowed by this customer");
            }
        }

        inventory.bookItem(customer);
        customer.takeItem(inventory);
        Record record = new Record(customer, inventory);
        log.add(new Record(customer, inventory));
        records.put(borrowKey, record);
    }

    public void returnItem(Customer customer, Inventory inventory) {
        BorrowKey borrowKey = new BorrowKey(customer, inventory);
        Record record = records.get(borrowKey);
        if (record == null){
            System.out.println("item not on record");
            return;
        }
        inventory.returnItem();
        customer.returnItem(inventory);
        record.returnItem();
        log.add(new Record(customer, inventory));
        records.remove(borrowKey);
    }

    public static int getOutstandingItemsCount() {
        return records.size();
    }

    public Record getFirstRecord(){
        if (records.isEmpty()){
            System.out.println("no records to date");
            return null;
        }
        return records.values().stream().findFirst().orElse(null);
    }

    public static void getReCords() {
        records.forEach((key, value) -> System.out.println(value.getCustomer().getName()
                + "borrowed " + value.getInventory().getName()));
    }
}