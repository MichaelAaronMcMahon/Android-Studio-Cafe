package com.example.rutgerscafe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;

import java.util.ArrayList;
import java.util.Collections;

public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private TextView subtotal;
    private ListView coffeeAddons;
    private Spinner cupSize;
    private Spinner cofQty;
    private ObservableArrayList<String> list;
    private String [] addons = {"Sweet Cream", "French Vanilla", "Irish Cream", "Caramel", "Mocha"};
    private String [] cupSizes = {"Short", "Tall", "Grande", "Venti"};
    private String [] quantities = {"1", "2", "3", "4", "5"};
    private ArrayAdapter<String> items;
    private Coffee coffee;
    private ArrayList<String> addonList;
    private Button orderCoffeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        coffee = new Coffee();
        addonList = new ArrayList<>();
        subtotal = (TextView) findViewById(R.id.subtotalCoffee);
        coffeeAddons = findViewById(R.id.coffeeAddons);
        cupSize = findViewById(R.id.cupSize);
        cofQty = findViewById(R.id.coffeeQty);
        coffee.setQuantity(1);
        orderCoffeeButton = findViewById(R.id.orderCoffeeButton);

        coffeeSizeSetup();
        coffeeQtySetup();
        setOrderCoffeeButton();

        list = new ObservableArrayList<>();
        Collections.addAll(list, addons); //add objects to the ObservableList
        coffeeAddons.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, list);
        coffeeAddons.setAdapter(items); //set the adapter of the ListView to the source
        coffeeAddons.setOnItemClickListener(this); //add a listener to the ListView

    }
    protected void setOrderCoffeeButton(){
        orderCoffeeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                OrderData orderData = OrderData.getInstance();
                orderData.getCurrentOrder().add(coffee);
                addToast();
                coffee = new Coffee();
                coffee.setQuantity(1);
                coffee.setCupSize(CoffeeCupSize.SHORT);
                addonList = new ArrayList<>();
                String newSubTotal = "Subtotal: $" + coffee.price();
                subtotal.setText(newSubTotal);
                cupSize.setSelection(0);
                cofQty.setSelection(0);
                coffeeAddons.clearChoices();
                items.notifyDataSetChanged(); //notify the attached observer the underlying data has been changed.

            }
        });
    }
    protected void coffeeSizeSetup(){
        ArrayAdapter<String> coffeeSizeAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                cupSizes
        );
        coffeeSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cupSize.setAdapter(coffeeSizeAdapter);
        cupSize.setPrompt("Cup Size");

        cupSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)parent.getItemAtPosition(position);
                System.out.println(item);

                coffee.setCupSize(CoffeeCupSize.valueOf(item.toUpperCase()));
                String newSubTotal = "Subtotal: $" + coffee.price();
                subtotal.setText(newSubTotal);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    protected void coffeeQtySetup(){
        ArrayAdapter<String> coffeeQuantityAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                quantities
        );
        coffeeQuantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cofQty.setAdapter(coffeeQuantityAdapter);
        cofQty.setPrompt("Coffee Quantity");

        cofQty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = (String)parent.getItemAtPosition(position);
                System.out.println(item);

                coffee.setQuantity(Integer.parseInt(item));
                String newSubTotal = "Subtotal: $" + coffee.price();
                subtotal.setText(newSubTotal);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addToast(){
        OrderData orderData = OrderData.getInstance();
        Toast.makeText(this,
                        orderData.getCurrentOrder().getMenuList()[orderData.getCurrentOrder().getAddIndex()-1].toString()
                                +" added",
                        Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {

        CheckedTextView check = (CheckedTextView) view;
        check.setChecked(!check.isChecked());
        boolean click = check.isChecked();

        if (click) {
            Toast.makeText(this, "De-Selected: " + (String)adapterView.getItemAtPosition(index), Toast.LENGTH_SHORT).show();
            addonList.remove((String)adapterView.getItemAtPosition(index));
            coffee.addAddin(addonList);
            String newSubtotal = "Subtotal: $" + coffee.price();
            subtotal.setText(newSubtotal);
        } else {
            Toast.makeText(this, "Selected: " + (String)adapterView.getItemAtPosition(index), Toast.LENGTH_SHORT).show();
            System.out.println((String)adapterView.getItemAtPosition(index));
            addonList.add((String)adapterView.getItemAtPosition(index));
            coffee.addAddin(addonList);
            String newSubtotal = "Subtotal: $" + coffee.price();
            subtotal.setText(newSubtotal);
        }
        items.notifyDataSetChanged(); //notify the attached observer the underlying data has been changed.
    }

}
