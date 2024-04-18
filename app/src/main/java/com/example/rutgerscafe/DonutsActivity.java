package com.example.rutgerscafe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import java.util.ArrayList;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DonutsActivity extends AppCompatActivity {

    private RecyclerView donutRecycler;
    private ArrayList<Donut> donuts = new ArrayList<Donut>();
    private double subtotal;
    private TextView tv_donutSubtotal;
    OrderData orderData = OrderData.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.subtotal = 0.0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        RecyclerView donutRecycler = findViewById(R.id.donutRecycler);
        setUpDonuts();
        DonutAdapter donutAdapter = new DonutAdapter(this, donuts, this);
        donutRecycler.setAdapter(donutAdapter);
        donutRecycler.setLayoutManager(new LinearLayoutManager(this));
        //
        String sub = "sss";
        this.tv_donutSubtotal = (TextView) findViewById(R.id.tv_donutSubtotal);
        orderData.setDonutsActivity(this);
        //tv_donutSubtotal.setText("hh");

    }
    public void setTv_donutSubtotal(double st){
        this.subtotal += st;
        //tv_donutSubtotal.setText(String.valueOf(4.0));
        //.tv_donutSubtotal.setText("h");
        //int total = 0;

        this.tv_donutSubtotal.setText(String.valueOf(this.subtotal));
    }

    private void setUpDonuts(){
        donuts.add(new Donut(DonutType.YEASTDONUT, DonutFlavor.BOSTONCREAM, R.drawable.boston, 1));
        donuts.add(new Donut(DonutType.YEASTDONUT, DonutFlavor.JELLYFILLED, R.drawable.jelly, 1));
        donuts.add(new Donut(DonutType.YEASTDONUT, DonutFlavor.POWDERED, R.drawable.powdered, 1));
        donuts.add(new Donut(DonutType.YEASTDONUT, DonutFlavor.OLDFASHIONED, R.drawable.oldfashioned, 1));
        donuts.add(new Donut(DonutType.YEASTDONUT, DonutFlavor.CHOCOLATE, R.drawable.chocolate, 1));
        donuts.add(new Donut(DonutType.YEASTDONUT, DonutFlavor.BLUEBERRY, R.drawable.blueberry, 1));
        donuts.add(new Donut(DonutType.CAKEDONUT, DonutFlavor.PUMPKIN, R.drawable.pumpkin, 1));
        donuts.add(new Donut(DonutType.CAKEDONUT, DonutFlavor.STRAWBERRY, R.drawable.strawberry, 1));
        donuts.add(new Donut(DonutType.CAKEDONUT, DonutFlavor.REDVELVET, R.drawable.redvelvet, 1));
        donuts.add(new Donut(DonutType.DONUTHOLE, DonutFlavor.SPRINKLED, R.drawable.sprinkled, 1));
        donuts.add(new Donut(DonutType.DONUTHOLE, DonutFlavor.GLAZED, R.drawable.glazed, 1));
        donuts.add(new Donut(DonutType.DONUTHOLE, DonutFlavor.RASPBERRY, R.drawable.raspberry, 1));


    }

}
