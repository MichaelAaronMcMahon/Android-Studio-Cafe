package com.example.rutgerscafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;

import java.util.ArrayList;
import java.util.Collections;

public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private TextView subtotal;
    private ListView coffeeAddons;
    private Spinner cupSize;
    private ObservableArrayList<String> list;
    private String [] addons = {"Sweet Cream", "French Vanilla", "Irish Cream", "Caramel", "Mocha"};
    private String [] cupSizes = {"Short", "Tall", "Grande", "Venti"};
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
        coffee.setQuantity(1);
        orderCoffeeButton = findViewById(R.id.orderCoffeeButton);

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

        orderCoffeeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                OrderData orderData = OrderData.getInstance();
                orderData.getCurrentOrder().add(coffee);
                addToast();
            }
        });

        list = new ObservableArrayList<>();
        Collections.addAll(list, addons); //add objects to the ObservableList
        coffeeAddons.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, list);
        coffeeAddons.setAdapter(items); //set the adapter of the ListView to the source
        coffeeAddons.setOnItemClickListener(this); //add a listener to the ListView
        //LayoutInflater inflater = LayoutInflater.from(this);
        //View view = inflater.inflate(R.layout.activity_coffee, parent, false);
        //setAddButtonOnClick(new View(this));
    }

    @Override
    protected void onStart(){
        super.onStart();
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

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        //String cup = (String)parent.getItemAtPosition(position);
//        //TextView textView = (TextView)cupSize.getSelectedView();
//        String text = (String)parent.getItemAtPosition(position);
//        //String cup = textView.getText().toString();
//        System.out.println(text);
//        //coffee.setCupSize(CoffeeCupSize.valueOf(cup.toUpperCase()));
//        //System.out.println(coffee.toString());
//        //String newSubtotal = "Subtotal: $" + coffee.price();
//        //subtotal.setText(newSubtotal);
//        items.notifyDataSetChanged(); //notify the attached observer the underlying data has been changed.
//
//    }
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//
//    } //can leave it empty
//    protected String [] getAddons() {
//        ArrayList<String> addons = new ArrayList<String>();
//
//        //String [] addons = new String[5];
//        //int index = 0;
//        for (int i = 0; i < coffeeAddons.getCount(); i++) {
//            if (coffeeAddons.isItemChecked(i)) {
//                // Do whatever you need to in here to get data from
//                // the item at index i in the ListView
//                //addons[index] ==
//                addons.add(coffeeAddons.getc);
//            }
//        }
//
//        return names;
//    }
//    public ItemClicked getItem(int position){
//        return items.get(position);
//    }
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }
}
