package com.example.rutgerscafe;

/**
 * An enum class for donut types 'Yeast Donut', 'Cake Donut', 'Donut Hole'.
 * Includes a method to return the donut type as a String.
 * @author Steven Rodriguez, Michael McMahon
 */
public enum DonutType {

    YEASTDONUT("Yeast Donut", 1.79),
    CAKEDONUT("Cake Donut", 1.89),
    DONUTHOLE("Donut Hole", 0.39);

    private final String donutType;
    private final double typePrice;

    /**
     * Constructor which creates a DonutType object from a string
     *
     * @param donutType
     * @param typePrice
     */
    DonutType(String donutType, double typePrice) {

        this.donutType = donutType;
        this.typePrice = typePrice;
    }
    /**
     * Return donut type as a String
     * @return String
     */
    public String getDonutType(){
        return donutType;
    }
    /**
     * Return donut type price as a double
     * @return double
     */
    public double getTypePrice(){
        return typePrice;
    }


}


