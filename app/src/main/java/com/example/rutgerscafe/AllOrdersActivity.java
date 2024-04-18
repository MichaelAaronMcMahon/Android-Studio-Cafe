package com.example.rutgerscafe;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AllOrdersActivity extends AppCompatActivity {
    private ListView orderListAO;
    private ListView menuItemsAO;
    private Button cancelOrderAO;
    OrderData orderData = OrderData.getInstance();
    private ArrayList<Order> orderNumbers;
    private ArrayAdapter<Order> orderAdapter;
    private ArrayList<MenuItem> menuList;
    private ArrayAdapter<MenuItem> menuItemAdapter;
    private Order removeOrder;
    private TextView tv_orderTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);
        this.tv_orderTotal = (TextView) findViewById(R.id.tv_orderTotal);
        orderListAO = findViewById(R.id.orderListAO);
        menuItemsAO = findViewById(R.id.menuItemsAO);
        cancelOrderAO = findViewById(R.id.cancelOrderAO);

        setOrderListAO();
        orderListAO.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order selectedOrder = (Order) parent.getItemAtPosition(position);
                setMenuItemsAO(selectedOrder);
                tv_orderTotal.setText(selectedOrder.getTotalPrice());
                removeOrder = selectedOrder;
            }
        });
        cancelOrderAO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderData.getAllOrders().remove(removeOrder);
                setOrderListAO();
                setMenuItemsAO(new Order(-1));
                tv_orderTotal.setText(" ");
            }
        });
    }

    public void setMenuItemsAO(Order selectedOrder){
        menuList = selectedOrder.getMenuList();
        menuItemAdapter = new ArrayAdapter<MenuItem>(this,
                android.R.layout.simple_list_item_1, menuList);
        menuItemsAO.setAdapter(menuItemAdapter);
    }

    public void setOrderListAO(){
        orderNumbers = orderData.getAllOrders();
        orderAdapter = new ArrayAdapter<Order>(this,
                android.R.layout.simple_list_item_single_choice, orderNumbers);
        orderListAO.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        orderListAO.setAdapter(orderAdapter);
    }
}
