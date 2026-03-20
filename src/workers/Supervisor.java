package workers;

import inventory.Inventory;

public class Supervisor extends Worker implements StaffMember {

    private Inventory[] assignedInventory;
    private int pointer = 0;

    public Supervisor(int salary, String name) {
        super(salary, name);
        this.assignedInventory = new Inventory[20];
    }

    public void addInventoryItem(Inventory inventory) {

        for (int i = 0; i < pointer; i++) {
            if (assignedInventory[i] == inventory) {
                System.out.println("book already assigned");
                return;
            }
        }
        if (pointer >= assignedInventory.length) {
            Inventory[] temp = new Inventory[assignedInventory.length * 2];
            System.arraycopy(assignedInventory, 0, temp, 0, assignedInventory.length);
            assignedInventory = temp;
        }
        assignedInventory[pointer] = inventory;
        pointer++;
    }

    public void removeInventoryItem(Inventory inventory) {

        for (int i = 0; i < pointer; i++) {
            if (assignedInventory[i] == inventory) {
                for (int j = i; j < pointer - 1; j++) {
                    assignedInventory[j] = assignedInventory[j + 1];
                }
                pointer -= 1;
                return;
            }
        }
        System.out.println("no such inventory exists");
    }

    public String getDuties() {
        return "Supervises inventory and staff";
    }
}