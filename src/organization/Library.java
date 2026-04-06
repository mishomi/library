package organization;

import annotations.LibraryFeature;
import inventory.Inventory;
import person.Customer;
import workers.Worker;

import java.util.ArrayList;
import java.util.List;

public class Library extends Organization {

    private GenericOrganization<Inventory> inventory;
    private GenericOrganization<Worker> workers;
    private GenericOrganization<Customer> customers;

    private int customerCount;

    public Library(String name) {
        super(name);
        this.inventory = new GenericOrganization<>();
        this.workers = new GenericOrganization<>();
        this.customers = new GenericOrganization<>();
        this.customerCount = 0;
    }

    public void addInventoryItem(Inventory inventoryItem) {

        if (inventory.contains(inventoryItem)){
            System.out.println("item already accounted for");
            return;
        }
        inventory.add(inventoryItem);
    }

    public void removeInventoryItem(Inventory inventoryItem) {

        if (!inventory.contains(inventoryItem)){
            System.out.println("item no longer i inventory");
            return;
        }
        inventory.remove(inventoryItem);
    }

    public void addWorker(Worker libraryWorker) {

        if (workers.contains(libraryWorker)){
            System.out.println("worker already in the books");
            return;
        }
        workers.add(libraryWorker);
    }

    public void removeWorker(Worker libraryWorker) {

        if (!workers.contains(libraryWorker)){
            System.out.println("worker no longer works here");
            return;
        }
        workers.remove(libraryWorker);
    }

    public void addCustomer(Customer customer) {

        if (customers.contains(customer)){
            System.out.println("customer already in the books");
            return;
        }
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {

        if (!customers.contains(customer)){
            System.out.println("customer no longer shops here");
            return;
        }
        customers.remove(customer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Library library = (Library) o;
        return name != null ? name.equals(library.name) : library.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Library[name=" + name + "]";
    }

    public GenericOrganization<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(GenericOrganization<Inventory> inventory) {
        this.inventory = inventory;
    }

    public GenericOrganization<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(GenericOrganization<Worker> workers) {

        this.workers = workers;
    }

    public GenericOrganization<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(GenericOrganization<Customer> customers) {
        this.customers = customers;
    }

    @LibraryFeature("Returns a readable library description")
    public String getDescription() {
        return "Library named " + name;
    }

    public int getInventorySize(){
        return inventory.size();
    }

    public boolean isInventoryEmpty(){
        return inventory.isEmpty();
    }

    public Inventory getInventoryItemAtIndex(int n){
        return inventory.get(n);
    }

    public Inventory getFirstInventoryItem(){
        return inventory.getItems().stream()
                .findFirst()
                .orElse(null);
    }

    public int getWorkersSize(){
        return workers.size();
    }

    public boolean isWorkersEmpty(){
        return workers.isEmpty();
    }

    public Worker getWorkerAtIndex(int n){
        return workers.get(n);
    }

    public Worker getFirsWorker(){
        return workers.getItems().stream()
                .findFirst().orElse(null);
    }

    public int getCustomersSize(){
        return customers.size();
    }

    public boolean isCustomersEmpty(){
        return customers.isEmpty();
    }

    public Customer getCustomerAtIndex(int n){
        return customers.get(n);
    }

    public Customer getFirstCustomer(){
        return customers.getItems().stream()
                .findFirst()
                .orElse(null);
    }
}