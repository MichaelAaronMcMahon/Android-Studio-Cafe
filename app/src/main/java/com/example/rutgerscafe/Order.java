package com.example.rutgerscafe;
import java.util.ArrayList;
/**
 * The Order Class creates an order object and stores the information of the current order
 * @author Steven Rodriguez, Michael McMahon
 */
public class Order {

    private int orderNumber;
    //private MenuItem [] menuList;
    private ArrayList<MenuItem> menuList;
    private int addIndex = 0;
    //an int which is assigned to each added menuitem, increasing with each addition
    //st each menu item has a unique ID
    private int menuItemId = 0;

    /**
     * Constructor which creates an Order object
     * @param orderNumber
     */
    public Order(int orderNumber){
        this.orderNumber = orderNumber;
        //this.menuList = new MenuItem[1];
        this.menuList = new ArrayList<MenuItem>();
    }
    public String getTotalPrice(){
        double totalPrice = 0.0;
        for(int i=0; i<this.addIndex; i++){
            totalPrice += this.menuList.get(i).price();
        }
        return String.format("%.2f", (totalPrice + (totalPrice * 0.06625)));
    }
    public String getSubtotalPrice(){
        double totalPrice = 0.0;
        for(int i=0; i<this.addIndex; i++){
            totalPrice += this.menuList.get(i).price();
        }
        return String.format("%.2f", totalPrice);
    }
    public String getSalesTaxPrice(){
        double totalPrice = 0.0;
        for(int i=0; i<this.addIndex; i++){
            totalPrice += this.menuList.get(i).price();
        }
        return String.format("%.2f", (totalPrice * 0.06625));
    }
    /**
     * Getter method that returns the current index
     * @return int
     */
    public int getAddIndex() {
        return addIndex;
    }
    /**
     * Getter method that returns the order number
     * @return int
     */
    public int getOrderNumber() {
        return orderNumber;
    }
    /**
     * Getter method that returns the list of menu items
     * @return MenuItem[]
     */
   /* public MenuItem[] getMenuList() {
        return menuList;
    }*/
    public ArrayList<MenuItem> getMenuList() {
        return menuList;
    }
    /**
     * Method that adds menu item to the array
     * @param menuItem
     */
    public void add(MenuItem menuItem){
        /*if (this.addIndex == this.menuList.length){
            grow();
        }
        menuItem.setID(this.menuItemId);
        this.menuItemId ++;
        menuList[this.addIndex] = menuItem;
        this.addIndex ++;*/
        menuItem.setID(this.menuItemId);
        this.menuItemId ++;
        this.menuList.add(menuItem);
        this.addIndex ++;

    }
    /**
     * Method that adds multiple menu items to the array
     * @param menuItems
     */
    public void add(MenuItem[] menuItems){
        for (MenuItem menuItem:menuItems)
            add(menuItem);
    }
    /**
     * Method that removes a menu item from the array
     * @param menuItem
     */
    public void remove(MenuItem menuItem){

        /*int removeID = menuItem.getID();
        for(int i = 0; i < this.addIndex; i++){

            if (this.menuList[i].getID() == removeID){
                this.menuList[i] = null;

                for(int j = (i + 1); j < this.addIndex; j ++){
                    this.menuList[j - 1] = this.menuList[j];
                    this.menuList[j] = null;
                }
                this.addIndex --;
            }
        }*/
        this.menuList.remove(menuItem);
        this.addIndex--;
    }
    /**
     * Creates a new array with a length 1 space longer and copies the original array to it.
     * It does this when the original array is full.
     */
    private void grow(){

        //MenuItem [] replacement = new MenuItem[menuList.length + 1];

       // int i = 0;
       // for(MenuItem item:menuList){
       //     replacement[i] = item;
      //      i ++;
      //  }
       // this.menuList = replacement;

    } //helper method to increase the capacity by 1
    /**
     * Overrides toString method to return order number as a string
     * @return String
     */
    @Override
    public String toString(){
        return String.valueOf(this.orderNumber);
    }
    /**
     * Setter method that sets the order number of the order object
     * @param orderNumber
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

}
