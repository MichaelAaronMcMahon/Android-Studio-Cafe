package com.example.rutgerscafe;

import java.util.ArrayList;

/**
 * Global singleton class to keep track of current order being
 * made and all placed orders
 */


public final class OrderData {
    private static OrderData orderData;

    private Order currentOrder;
    private ArrayList<Order> allOrders;
    private OrderData(){}
    public static synchronized OrderData getInstance(){
        if(orderData == null){
            orderData = new OrderData();
        }
        return orderData;
    }
    public void setCurrentOrder(Order currentOrder){
        this.currentOrder = currentOrder;
    }
    public void setAllOrders(ArrayList<Order> allOrders){
        this.allOrders = allOrders;
    }
}

