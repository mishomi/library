package organization;

import inventory.Inventory;
import person.Customer;
import workers.Worker;

public interface Management {
    void addInventoryItem(Inventory inventoryItem);
    void removeInventoryItem(Inventory inventoryItem);
    void addWorker(Worker libraryWorker);
    void removeWorker(Worker libraryWorker);
    void addCustomer(Customer customer);
    void removeCustomer(Customer customer);
}
