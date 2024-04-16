package com.example.rutgerscafe;

/**
 * Global singleton class to keep track of current order being
 * made and all placed orders
 */


public final class OrderData {
    private static OrderData orderData;
    private Order currentOrder;
    private Order[] allOrders;
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
    public void setAllOrders(Order[] allOrders){
        this.allOrders = allOrders;
    }
}

