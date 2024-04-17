package com.example.rutgerscafe;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class CurrentOrderActivity extends AppCompatActivity {

    private ListView lv_currentOrder;
    private Button bt_removeItem;
    private Button bt_placeOrder;
    private ArrayList<MenuItem> listItems;
    private ArrayAdapter<MenuItem> currentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        OrderData orderData = OrderData.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        lv_currentOrder = findViewById(R.id.lv_currentOrder);
        bt_placeOrder = findViewById(R.id.bt_placeOrder);
        bt_removeItem = findViewById(R.id.bt_removeItem);
        listItems = new ArrayList<MenuItem>(Arrays.asList(orderData.getCurrentOrder().getMenuList()));
        currentAdapter = new ArrayAdapter<MenuItem>(this,
                android.R.layout.simple_list_item_multiple_choice, listItems);
        lv_currentOrder.setAdapter(currentAdapter);

    }
}
