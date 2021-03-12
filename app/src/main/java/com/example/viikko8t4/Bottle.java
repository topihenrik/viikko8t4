package com.example.viikko8t4;

public class Bottle {
    private String name;
    private String manufacturer;
    private double total_energy;
    private double size;
    private double price;

    public Bottle(String name, String manuf, double totE, double size, double price){
        this.name = name;
        this.manufacturer = manuf;
        this.total_energy = totE;
        this.size = size;
        this.price = price;
    }

    public String getName(){
        //String name_ = "Pepsi Max";
        return (name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer(){
        //String manufacturer_ = "Pepsi";
        return (manufacturer);
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getEnergy(){
        //double energy_ = 0.3;
        return (total_energy);
    }

    public void setEnergy(double total_energy) {
        this.total_energy = total_energy;
    }

    public double getSize(){
        //double size_ = 0.5;
        return (size);
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice(){
        //double price_ = 1.8;
        return (price);
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
