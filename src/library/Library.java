package library;

import library.inventory.Inventory;
import library.workers.Worker;

public class Library {

    private String name;
    private Inventory[] inventory;
    private Worker[] workers;
    private Customer[] customers;

    private int inventoryCount;
    private int workerCount;
    private int customerCount;

    public Library(String name) {
        this.name = name;
        this.inventory = new Inventory[50];
        this.workers = new Worker[20];
        this.customers = new Customer[50];
        this.inventoryCount = 0;
        this.workerCount = 0;
        this.customerCount = 0;
    }

    public void addInventoryItem(Inventory inventoryItem) {

        if (inventoryCount >= inventory.length) {
            Inventory[] temp = new Inventory[inventory.length * 2];
            System.arraycopy(inventory, 0, temp, 0, inventory.length);
            inventory = temp;
        }
        inventory[inventoryCount] = inventoryItem;
        inventoryCount++;
    }

    public void removeInventoryItem(Inventory inventoryitem) {

        for (int i = 0; i < inventoryCount; i++) {
            if (inventory[i] == inventoryitem) {
                for (int j = i; j < inventoryCount - 1; j++) {
                    inventory[j] = inventory[j + 1];
                }
                return;
            }
        }
        System.out.println("no such item exists");
    }

    public void addWorker(Worker libraryWorker) {

        if (workerCount >= workers.length) {
            Worker[] temp = new Worker[workers.length * 2];
            System.arraycopy(workers, 0, temp, 0, workers.length);
            workers = temp;
        }
        workers[workerCount] = libraryWorker;
        workerCount++;
    }

    public void removeWorker(Worker libraryWorker) {

        for (int i = 0; i < workerCount; i++) {
            if (workers[i] == libraryWorker) {
                for (int j = i; j < workerCount - 1; j++) {
                    workers[j] = workers[j + 1];
                }
                return;
            }
        }
        System.out.println("no such worker in library system");
    }

    public void addCustomer(Customer customer) {

        if (customerCount >= customers.length) {
            Customer[] temp = new Customer[customers.length * 2];
            System.arraycopy(customers, 0, temp, 0, customers.length);
            customers = temp;
        }
        customers[customerCount] = customer;
        customerCount++;
    }

    public void removeCustomer(Customer customer) {

        for (int i = 0; i < customerCount; i++) {
            if (customers[i] == customer) {
                for (int j = i; j < customerCount - 1; j++) {
                    customers[j] = customers[j + 1];
                }
                return;
            }
        }
        System.out.println("no such customer exists");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory[] getInventory() {
        return inventory;
    }

    public void setInventory(Inventory[] inventory) {
        this.inventory = inventory;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public void setWorkers(Worker[] workers) {

        this.workers = workers;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }
}