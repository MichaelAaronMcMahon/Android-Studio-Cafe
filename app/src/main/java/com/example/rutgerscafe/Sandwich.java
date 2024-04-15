package com.example.rutgerscafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.lang.Math;
/**
 * The Sandwich object creates a sandwich and stores the selected sandwich combination
 * Extends MenuItem Class
 * @author Michael McMahon, Steven Rodriguez
 */
public class Sandwich extends MenuItem{

    private SandwichOption option;
    private SandwichBread bread;
    private SandwichAddon [] addons;
    private int quantity;
    private int index;
    private int ID;
    private boolean containsCheese;

    /**
     * Getter method that returns the Id of the sandwich item
     * @return int
     */
    @Override
    public int getID() {
        return ID;
    }
    /**
     * Setter method that sets the Id of the sandwich item
     * @param ID
     */
    @Override
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * Setter method that sets the quantity of the sandwich item
     * @param quantity
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    /**
     * Setter method that sets the bread option of the sandwich item
     * @param bread
     */
    public void setBread(String bread){
        bread = bread.replaceAll("\\s+", "");
        this.bread = SandwichBread.valueOf(bread.toUpperCase());
    }

    /**
     * Setter method that sets the protein option of the sandwich item
     * @param option
     */
    public void setOption(String option){
        option = option.replaceAll("\\s+", "");
        this.option = SandwichOption.valueOf(option.toUpperCase());
    }
    /**
     * Method that adds addons to the sandwich item
     * @param addon
     * @return
     */
    public boolean addAddon(ObservableList<String> addon){

        this.containsCheese = false;
        this.index = 0;
        this.addons = new SandwichAddon[4];

        for (String add:addon){
            if (add.equalsIgnoreCase("Cheese")){
                containsCheese = true;
            }
            this.addons[index] = SandwichAddon.valueOf(add.toUpperCase());
            index++;
        }

        return true;
    }
    /**
     * Method that calculates and returns the price for the sandwich item
     * @return double
     */
    @Override
    public double price() {

        double addonMultiplier = 0;

        if (this.containsCheese){
            addonMultiplier ++;
            addonMultiplier += (index - 1) * 0.3;
        }
        else{
            addonMultiplier += index * 0.3;
        }
        if (this.option != null) {
            double unrounded = (8.99 + this.option.ordinal() + addonMultiplier) * quantity;
            return (double) Math.round(unrounded * 100)/100;
        }
        else return 0;
    }

    /**
     * Overrides toString method to return the selected sandwich combination in a nice format
     * @return String
     */
    @Override
    public String toString(){
        String rstring = "Sandwich Bread Type: " + this.bread.getBread() + " | Sandwich Protein Option: " + this.option.getSwOption() + " | Sandwich Addons: ";

        for(int i = 0; i < this.index; i++){
            rstring += this.addons[i].getSwAddon();
            if (this.index - i != 1)
                rstring += ", ";
        }
        rstring += " | Quantity: " + this.quantity + " | Id #" + this.getID();
        return rstring;
    }

}