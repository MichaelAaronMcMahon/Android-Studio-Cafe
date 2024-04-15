package com.example.rutgerscafe;
/**
 * An enum class for coffee addins Sweet Cream, French Vanilla, Irish Cream, Caramel, and Mocha
 * Includes a method to return the Coffee addin as a String.
 * @author Steven Rodriguez
 */
public enum CoffeeAddin {

    SWEETCREAM("Sweet Cream"),
    FRENCHVANILLA("French Vanilla"),
    IRISHCREAM("Irish Cream"),
    CARAMEL("Caramel"),
    MOCHA("Mocha");

    private final String coffeeAddin;

    /**
     * Constructor which creates a CoffeeAddin object from a string
     * @param coffeeAddin
     */
    CoffeeAddin(String coffeeAddin) {
        this.coffeeAddin = coffeeAddin;
    }
    /**
     * Return coffee addin as a String
     * @return String
     */
    public String getCoffeeAddin(){
        return coffeeAddin;
    }
}
