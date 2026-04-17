package com.solvd.library.workers;

import com.solvd.library.inventory.Inventory;
import com.solvd.library.reflection.LibraryReflection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Supervisor extends Worker implements StaffMember {

    private static final Logger log = LogManager.getLogger(Supervisor.class);
    private List<Inventory> assignedInventory;

    public Supervisor(int salary, String name) {
        super(salary, name, WorkerRole.SUPERVISOR);
        this.assignedInventory = new ArrayList<>();
    }

    public void addInventoryItem(Inventory inventory) {

        if (assignedInventory.contains(inventory)){
            log.warn("inventory already accounted for");
            return;
        }
        assignedInventory.add(inventory);

    }

    public void removeInventoryItem(Inventory inventory) {

        if (!assignedInventory.contains(inventory)){
            log.warn("item not in inventory");
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
        return "Supervises inventory and staff";
    }
}