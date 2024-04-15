package com.example.rutgerscafe;

/**
 * The Menu Item Class creates a menu item and is meant to be inherited from
 * @author Steven Rodriguez, Michael McMahon
 */
abstract class MenuItem {
    /**
     * Setter method that sets the Id of the menu item
     * @param ID
     */
    public abstract void setID(int ID);
    /**
     * Getter method that returns the Id of the menu item
     * @return int
     */
    public abstract int getID();

    /**
     * Method that calculates and returns the price of the menu item
     * @return double
     */
    public abstract double price();

    /**
     * Overrides toString, but is meant to force children objects to override it as well
     * @return String
     */
    @Override
    public abstract String toString();

}
