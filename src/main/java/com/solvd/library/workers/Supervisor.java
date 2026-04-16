package com.solvd.library.workers;

import com.solvd.library.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Supervisor extends Worker implements StaffMember {

    private List<Inventory> assignedInventory;

    public Supervisor(int salary, String name) {
        super(salary, name, WorkerRole.SUPERVISOR);
        this.assignedInventory = new ArrayList<>();
    }

    public void addInventoryItem(Inventory inventory) {

        if (assignedInventory.contains(inventory)){
            System.out.println("main.java.com.solvd.library.inventory already accounted for");
            return;
        }
        assignedInventory.add(inventory);

    }

    public void removeInventoryItem(Inventory inventory) {

        if (!assignedInventory.contains(inventory)){
            System.out.println("item not in main.java.com.solvd.library.inventory");
            return;
        }
        assignedInventory.remove(inventory);
    }

    public List<Inventory> getAssignedInventory() {
        return assignedInventory;
    }

    public void setAssignedInventory(List<Inventory> assignedInventory) {
        this.assignedInventory = assignedInventory;
    }

    @Override
    public String getDuties() {
        return "Supervises main.java.com.solvd.library.inventory and staff";
    }
}