package com.example.rutgerscafe;

import java.util.ArrayList;

/**
 * Global singleton class to keep track of current order being
 * made and all placed orders
 */


public final class OrderData {
    private static OrderData orderData;
    private int orderNumber = 1;
    private Order currentOrder;
    private ArrayList<Order> allOrders;
    private OrderData(){}
    public static synchronized OrderData getInstance(){
        if(orderData == null){
            orderData = new OrderData();
            orderData.setCurrentOrder(new Order(orderData.orderNumber));
            orderData.setAllOrders(new ArrayList<Order>());
            //orderData.orderNumber++;
        }
        return orderData;
    }
    //public void startNewOrder(){
    //    this.currentOrder = new Order(orderNumber);
    //    orderNumber++;
    //}
    public void setCurrentOrder(Order currentOrder){
        this.currentOrder = currentOrder;
    }
    public void setAllOrders(ArrayList<Order> allOrders){
        this.allOrders = allOrders;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }

    public void incrementOrderNumber() {
        this.orderNumber = this.orderNumber + 1;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}

