package com.example.rutgerscafe;

//import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * The Coffee object creates a coffee and stores the selected coffee combination
 * Extends MenuItem class
 * @author Steven Rodriguez, Michael McMahon
 */
public class Coffee extends MenuItem{

    private CoffeeAddin [] coffeeAddins;
    private CoffeeCupSize cupSize;
    private int quantity;
    private int index;
    private int ID;

    /**
     * Getter method that returns the Id of the coffee item
     * @return int
     */
    @Override
    public int getID() {
        return ID;
    }
    /**
     * Setter method that sets the Id of the coffee item
     * @param ID
     */
    @Override
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * Setter method that sets the quantity of the coffee item
     * @param quantity
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    /**
     * Setter method that sets the cup size of the coffee item
     * @param cupSize
     */
    public void setCupSize(CoffeeCupSize cupSize){
        this.cupSize = cupSize;
    }
    /**
     * Method that adds the selected add-ins to the coffee item
     * @param addins
     * @return true or false
     */
    public boolean addAddin(ArrayList<String> addins){

        this.index = 0;
        this.coffeeAddins = new CoffeeAddin[5];

        for (String add:addins){
            add = add.replaceAll("\\s+", "");
            coffeeAddins[index] = CoffeeAddin.valueOf(add.toUpperCase());
            index++;
            //System.out.println(add);
        }

        return true;
    }
    /**
     * Overrides toString to return the selected combination of the coffee item in a nice format
     * @return String
     */
    @Override
    public String toString(){
        String rstring = "Coffee cup size: " + this.cupSize.getCupSize() + " | Coffee Addins: ";
        for(int i = 0; i < this.index; i++){
            rstring += this.coffeeAddins[i].getCoffeeAddin();
            if (this.index - i != 1)
                rstring += ", ";
        }
        rstring += " | Quantity: " + this.quantity + " | Id #" + this.getID();
        return rstring;
    }
    /**
     * Method that calculates and return the price for the coffee item
     * @return double
     */
    @Override
    public double price() {

        double cupPriceMultiplier = this.cupSize.ordinal() * 0.50;
        double addinMultiplier = this.index * 0.30;

        double unrounded = (cupPriceMultiplier + addinMultiplier + 1.99) * quantity;
        System.out.println(unrounded);
        return (double) Math.round(unrounded * 100)/100;
    }
}