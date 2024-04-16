package com.example.rutgerscafe;

import android.media.Image;

/**
 * The Donut object creates a donut and stores the selected donut combination
 * Extends MenuItem Class
 * @author Michael McMahon, Steven Rodriguez
 */
public class Donut extends MenuItem{
    private DonutFlavor donutFlavor;
    private DonutType donutType;
    private int quantity = 1;
    private int ID;
    private int image;

    public Donut(DonutType donutType, DonutFlavor donutFlavor, int image, int quantity){
        this.donutType = donutType;
        this.donutFlavor = donutFlavor;
        this.image = image;
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    /**
     * Setter method that sets the Id of the donut item
     * @param ID
     */
    @Override
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * Getter method that returns the Id of the donut item
     * @return int
     */
    @Override
    public int getID() {
        return ID;
    }
    /**
     * Method that calculates and return the price for the donut item
     * @return double
     */
    @Override
    public double price() {
        double unrounded = this.quantity * this.donutType.getTypePrice();
        return (double) Math.round(unrounded * 100)/100;
    }
    /**
     * Overrides toString to return the selected combination of the donut item in a nice format
     * @return String
     */
    @Override
    public String toString(){
        String rstring = "Donut Type: " + this.donutType.getDonutType() + " | Donut Flavor: " + this.donutFlavor.getDonutFlavor() + " | Quantity: " + String.valueOf(this.quantity)+ " | Id #" + String.valueOf(this.ID);
        return rstring;
    }
    /**
     * Getter method that returns the flavor of the donut item
     * @return DonutFlavor
     */
    public DonutFlavor getDonutFlavor() {
        return donutFlavor;
    }
    /**
     * Getter method that returns the type of the donut item
     * @return DonutType
     */
    public DonutType getDonutType() {
        return donutType;
    }
    /**
     * Setter method that sets the flavor of the donut item
     * @param donutFlavor
     */
    public void setDonutFlavor(DonutFlavor donutFlavor) {

        this.donutFlavor = donutFlavor;
        if(donutFlavor.getDonutFlavor().equalsIgnoreCase("Boston Cream")){
            this.image = R.drawable.boston;
        }

    }
    /**
     * Setter method that sets the type of the donut item
     * @param  donutType
     */
    public void setDonutType(DonutType donutType) {
        this.donutType = donutType;
    }
    /**
     * Setter method that sets the quantity of the donut item
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
