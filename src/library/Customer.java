package library;

import library.inventory.Inventory;

import java.math.BigDecimal;

public class Customer {

    private String name;
    private BigDecimal money;
    private int age;
    private Inventory[] itemsCurrectlyInPossession;
    private int countOfItems;
    private BigDecimal outstandingFees;

    public Customer(String name, BigDecimal money, int age) {

        this.name = name;
        this.money = money;
        this.age = age;
        this.itemsCurrectlyInPossession = new Inventory[5];
        this.countOfItems = 0;
        this.outstandingFees = BigDecimal.valueOf(0);
    }

    public void takeItem(Inventory inventory) {

        if (countOfItems >= itemsCurrectlyInPossession.length) {
            Inventory[] temp = new Inventory[itemsCurrectlyInPossession.length * 2];
            System.arraycopy(itemsCurrectlyInPossession, 0, temp, 0, itemsCurrectlyInPossession.length);
            itemsCurrectlyInPossession = temp;
        }
        itemsCurrectlyInPossession[countOfItems] = inventory;
        countOfItems++;
    }

    public void returnItem(Inventory inventory) {

        for (int i = 0; i < countOfItems; i++) {
            if (itemsCurrectlyInPossession[i] == inventory) {
                itemsCurrectlyInPossession[i] = null;
                for (int j = i; j < countOfItems - 1; j++) {
                    Inventory temp = itemsCurrectlyInPossession[j];
                    itemsCurrectlyInPossession[j] = itemsCurrectlyInPossession[j + 1];
                    itemsCurrectlyInPossession[j + 1] = temp;
                }
                countOfItems--;
                break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}