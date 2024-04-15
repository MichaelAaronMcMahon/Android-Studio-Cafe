package com.example.rutgerscafe;
/**
 * An enum class for coffee cup Sizes Short, Tall, Grande, Venti
 * Includes a method to return the Coffee size as a String.
 * @author Steven Rodriguez
 */
public enum CoffeeCupSize {

    SHORT("Short"),
    TALL("Tall"),
    GRANDE("Grande"),
    VENTI("Venti");

    private final String cupSize;

    /**
     * Constructor which creates a CoffeeCupSize object from a string
     * @param cupSize
     */
    CoffeeCupSize(String cupSize) {
        this.cupSize = cupSize;
    }
    /**
     * Return coffee cup size as a String
     * @return String
     */
    public String getCupSize(){
        return cupSize;
    }
}
