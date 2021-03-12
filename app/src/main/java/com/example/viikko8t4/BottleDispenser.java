package com.example.viikko8t4;

import java.util.ArrayList;

public class BottleDispenser{
    private static double money;
    private static ArrayList<Bottle> bottleList = new ArrayList<Bottle>();
    private static BottleDispenser bd = null;
    static String lastPurchase = null;

    private BottleDispenser(){}

    public static BottleDispenser getInstance() {
        if (bd == null) {
            bd = new BottleDispenser();
            money = 0;
            bottleList.add(new Bottle("Pepsi Max", "Pepsi", 0.3, 0.5, 1.8));
            bottleList.add(new Bottle("Pepsi Max", "Pepsi", 0.3, 1.5, 2.2));
            bottleList.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.3, 0.5, 2.0));
            bottleList.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.3, 1.5, 2.5));
            bottleList.add(new Bottle("Fanta Zero", "Coca-Cola", 0.3, 0.5, 1.95));
            return bd;
        }
        return bd;
    }

    public static double getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
        return;
    }


    public int buyBottle(String name, double size) {
        int errCode = -1, i;
        for (i=0;i<bottleList.size();i++) {
            if ((bottleList.get(i).getName().equals(name)) && (bottleList.get(i).getSize() == size)) {
                if (money >= bottleList.get(i).getPrice()) {
                    money -= bottleList.get(i).getPrice();
                    lastPurchase = ("Product: " + bottleList.get(i).getName() + " " + bottleList.get(i).getSize() + "L\nPrice: " + bottleList.get(i).getPrice() + "€");
                    System.out.println(lastPurchase);
                    errCode = 0; // KACHUNK! ITEM came out of the dispenser!
                    break;
                } else {
                    errCode = 2; // Add money first!
                    break;
                }
            } else {
                errCode = 1; // Not enough bottles!
            }
        }
        return errCode;
    }

    public void returnMoney() {
        money = 0;
        return;
    }

}
