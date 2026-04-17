package com.solvd.library.person;

import com.solvd.library.inventory.Inventory;
import com.solvd.library.organization.Library;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person implements LibraryPeople {

    private static final Logger log = LogManager.getLogger(Customer.class);
    private String name;
    private BigDecimal money;
    private int age;
    private List<Inventory> itemsCurrentlyInPossession;
    private Library library;
    private BigDecimal outstandingFees;
    private PaymentStatus paymentStatus;

    public Customer(String name, BigDecimal money, int age, Library library) {
        super(name, age);
        this.name = name;
        this.money = money;
        this.age = age;
        this.library = library;
        this.paymentStatus = PaymentStatus.PAID;
        if (!library.getCustomers().contains(this)){
            library.addCustomer(this);
        }
        this.itemsCurrentlyInPossession = new ArrayList<>();
        this.outstandingFees = BigDecimal.valueOf(0);
    }

    @Override
    public String toString() {
        return "Customer[name=" + name + ", age=" + age + ", money=" + money + "]";
    }

    public void takeItem(Inventory inventory) {

        if (!library.getInventory().contains(inventory)){
            log.warn("item not available");
            return;
        }
        itemsCurrentlyInPossession.add(inventory);
        library.removeInventoryItem(inventory);
    }

    public void returnItem(Inventory inventory) {

        if (!itemsCurrentlyInPossession.contains(inventory)){
            log.warn("item not in possession");
            return;
        }
        itemsCurrentlyInPossession.remove(inventory);
        library.addInventoryItem(inventory);
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getOutstandingFees() {
        return outstandingFees;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    public List<Inventory> getItemsCurrentlyInPossession() {
        return itemsCurrentlyInPossession;
    }

    public void setItemsCurrentlyInPossession(List<Inventory> itemsCurrentlyInPossession) {
        this.itemsCurrentlyInPossession = itemsCurrentlyInPossession;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void setOutstandingFees(BigDecimal outstandingFees) {

        this.outstandingFees = outstandingFees;
        if (outstandingFees.intValue() == 0) {
            paymentStatus = PaymentStatus.PAID;
        } else {
            if (this.getMoney().intValue() >= this.getOutstandingFees().intValue()) {
                this.setMoney(this.getMoney().subtract(this.getOutstandingFees()));
                this.outstandingFees = BigDecimal.valueOf(0);
                paymentStatus = PaymentStatus.PAID;
            }
            else {
                paymentStatus = PaymentStatus.UNPAID;
            }
        }
    }

    @Override
    public String getRole() {
        return "Customer";
    }
}