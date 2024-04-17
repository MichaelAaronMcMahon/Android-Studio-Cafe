package com.example.rutgerscafe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;

import java.util.ArrayList;
import java.util.Collections;

public class SandwichActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ObservableArrayList<String> list;
    private ArrayAdapter<String> items;
    private ListView sandwichAddons;
    private TextView subtotal;
    private ArrayList<String> addonList;
    Sandwich sandwich;
    private Spinner sandwichBread;
    private Spinner sandwichOption;
    private Spinner sandwichQty;
    private Button orderSandwichButton;
    private String [] breads = {"Bagel", "Wheat Bread", "Sour Dough"};
    private String [] quantities = {"1", "2", "3", "4", "5"};
    private String [] proteins = {"Beef", "Chicken", "Fish"};
    private String [] addons = {"Cheese", "Lettuce", "Tomato", "Onion"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich);

        sandwich = new Sandwich();
        addonList = new ArrayList<>();
        subtotal = (TextView) findViewById(R.id.subtotalSandwich);

        sandwichAddons = findViewById(R.id.sandwichAddons);
        sandwichBread = findViewById(R.id.sandwichBread);
        sandwichOption = findViewById(R.id.sandwichProtein);
        sandwichQty = findViewById(R.id.sandwichQty);
        sandwich.setQuantity(1);
        orderSandwichButton = findViewById(R.id.orderSandwichButton);

        sandwichBreadSetup();
        sandwichQtySetup();
        sandwichOptionSetup();
        setOrderSandwichButton();

        list = new ObservableArrayList<>();
        Collections.addAll(list, addons); //add objects to the ObservableList
        sandwichAddons.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, list);
        sandwichAddons.setAdapter(items); //set the adapter of the ListView to the source
        sandwichAddons.setOnItemClickListener(this); //add a listener to the ListView

    }
    protected void setOrderSandwichButton(){
        orderSandwichButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                OrderData orderData = OrderData.getInstance();
                orderData.getCurrentOrder().add(sandwich);
                addToast();
                sandwich = new Sandwich();
                sandwich.setQuantity(1);
                sandwich.setOption("Beef");
                sandwich.setBread("Bagel");
                addonList = new ArrayList<>();
                String newSubTotal = "Subtotal: $" + sandwich.price();
                subtotal.setText(newSubTotal);

                sandwichBread.setSelection(0);
                sandwichQty.setSelection(0);
                sandwichOption.setSelection(0);
                sandwichAddons.clearChoices();
                items.notifyDataSetChanged(); //notify the attached observer the underlying data has been changed.

            }
        });
    }
    protected void sandwichOptionSetup(){
        ArrayAdapter<String> sandwichProteinAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                proteins
        );
        sandwichProteinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sandwichOption.setAdapter(sandwichProteinAdapter);
        sandwichOption.setPrompt("Sandwich Protein");

        sandwichOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = (String)parent.getItemAtPosition(position);
                System.out.println(item);

                sandwich.setOption(item);
                String newSubTotal = "Subtotal: $" + sandwich.price();
                subtotal.setText(newSubTotal);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    protected void sandwichQtySetup(){
        ArrayAdapter<String> sandwichQuantityAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                quantities
        );
        sandwichQuantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sandwichQty.setAdapter(sandwichQuantityAdapter);
        sandwichQty.setPrompt("Sandwich Quantity");

        sandwichQty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = (String)parent.getItemAtPosition(position);
                System.out.println(item);

                sandwich.setQuantity(Integer.parseInt(item));
                String newSubTotal = "Subtotal: $" + sandwich.price();
                subtotal.setText(newSubTotal);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    protected void sandwichBreadSetup(){
        ArrayAdapter<String> sandwichBreadAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                breads
        );
        sandwichBreadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sandwichBread.setAdapter(sandwichBreadAdapter);
        sandwichBread.setPrompt("Sandwich Bread");

        sandwichBread.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)parent.getItemAtPosition(position);
                System.out.println(item);

                sandwich.setBread(item);
                String newSubTotal = "Subtotal: $" + sandwich.price();
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
            sandwich.addAddon(addonList);
            String newSubtotal = "Subtotal: $" + sandwich.price();
            subtotal.setText(newSubtotal);
        } else {
            Toast.makeText(this, "Selected: " + (String)adapterView.getItemAtPosition(index), Toast.LENGTH_SHORT).show();
            addonList.add((String)adapterView.getItemAtPosition(index));
            sandwich.addAddon(addonList);
            String newSubtotal = "Subtotal: $" + sandwich.price();
            subtotal.setText(newSubtotal);
        }
        items.notifyDataSetChanged(); //notify the attached observer the underlying data has been changed.
    }
}
