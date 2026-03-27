package workers;

import inventory.Inventory;
import organization.Library;

import java.util.ArrayList;
import java.util.List;

public class Supervisor extends Worker implements StaffMember {

    private List<Inventory> assignedInventory;

    public Supervisor(int salary, String name) {
        super(salary, name);
        this.assignedInventory = new ArrayList<>();
    }

    public void addInventoryItem(Inventory inventory) {

        if (assignedInventory.contains(inventory)){
            System.out.println("inventory already accounted for");
            return;
        }
        assignedInventory.add(inventory);

    }

    public void removeInventoryItem(Inventory inventory) {

        if (!assignedInventory.contains(inventory)){
            System.out.println("item not in inventory");
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