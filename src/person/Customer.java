package person;

import inventory.Inventory;

import java.math.BigDecimal;

public class Customer extends Person implements LibraryPeople {

    private String name;
    private BigDecimal money;
    private int age;
    private Inventory[] itemsCurrectlyInPossession;
    private int pointer;

    private BigDecimal outstandingFees;

    public Customer(String name, BigDecimal money, int age) {
        super(name, age);
        this.name = name;
        this.money = money;
        this.age = age;
        this.itemsCurrectlyInPossession = new Inventory[5];
        this.pointer = 0;
        this.outstandingFees = BigDecimal.valueOf(0);
    }

    @Override
    public String toString() {
        return "Customer[name=" + name + ", age=" + age + ", money=" + money + "]";
    }

    public void takeItem(Inventory inventory) {

        if (pointer >= itemsCurrectlyInPossession.length) {
            Inventory[] temp = new Inventory[itemsCurrectlyInPossession.length * 2];
            System.arraycopy(itemsCurrectlyInPossession, 0, temp, 0, itemsCurrectlyInPossession.length);
            itemsCurrectlyInPossession = temp;
        }
        itemsCurrectlyInPossession[pointer] = inventory;
        pointer++;
    }

    public void returnItem(Inventory inventory) {

        for (int i = 0; i < pointer; i++) {
            if (itemsCurrectlyInPossession[i] == inventory) {
                itemsCurrectlyInPossession[i] = null;
                for (int j = i; j < pointer - 1; j++) {
                    Inventory temp = itemsCurrectlyInPossession[j];
                    itemsCurrectlyInPossession[j] = itemsCurrectlyInPossession[j + 1];
                    itemsCurrectlyInPossession[j + 1] = temp;
                }
                pointer--;
                break;
            }
        }
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

    public void setOutstandingFees(BigDecimal outstandingFees) {

        this.outstandingFees = outstandingFees;
        if (outstandingFees.intValue() == 0) {
            return;
        } else {
            if (this.getMoney().intValue() >= this.getOutstandingFees().intValue()) {
                this.setMoney(this.getMoney().subtract(this.getOutstandingFees()));
                this.outstandingFees = BigDecimal.valueOf(0);
            }
        }
    }

    @Override
    public String getRole() {
        return "Customer";
    }
}