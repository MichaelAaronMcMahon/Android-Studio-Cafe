package com.example.rutgerscafe;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class CurrentOrderActivity extends AppCompatActivity {

    private ListView lv_currentOrder;
    private Button bt_removeItem;
    private Button bt_placeOrder;
    private ArrayList<MenuItem> listItems;
    private ArrayAdapter<MenuItem> currentAdapter;
    private MenuItem removeItem;
    OrderData orderData = OrderData.getInstance();
    public void setLv_currentOrder(){
        //listItems = new ArrayList<MenuItem>(Arrays.asList(orderData.getCurrentOrder().getMenuList()));
        listItems = orderData.getCurrentOrder().getMenuList();
        currentAdapter = new ArrayAdapter<MenuItem>(this,
                android.R.layout.simple_list_item_single_choice, listItems);
        lv_currentOrder.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lv_currentOrder.setAdapter(currentAdapter);
    }

    public void makeToast(){
        Toast.makeText(this,
                removeItem.toString(),
                Toast.LENGTH_LONG).show();
    }
    /*public void placeToast(){
        Toast.makeText(this,
                orderData.getAllOrders().get(0).toString(),
                Toast.LENGTH_LONG).show();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        lv_currentOrder = findViewById(R.id.lv_currentOrder);
        bt_placeOrder = findViewById(R.id.bt_placeOrder);
        bt_removeItem = findViewById(R.id.bt_removeItem);
        setLv_currentOrder();
        lv_currentOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                removeItem = (MenuItem) parent.getItemAtPosition(position);
                makeToast();
            }
        });
        bt_removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderData.getCurrentOrder().remove(removeItem);
                setLv_currentOrder();
            }
        });
        bt_placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderData.getAllOrders().add(orderData.getCurrentOrder());
                orderData.incrementOrderNumber();
                orderData.setCurrentOrder(new Order(orderData.getOrderNumber()));
                setLv_currentOrder();
                //placeToast();
            }
        });

    }
}
