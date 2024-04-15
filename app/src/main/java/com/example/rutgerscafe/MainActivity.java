package com.example.rutgerscafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onDonutsClick(View view) {
        Intent intent = new Intent(this, DonutsActivity.class);
        startActivity(intent);
    }
    public void onCoffeeClick(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }
    public void onSandwichClick(View view) {
        Intent intent = new Intent(this, SandwichActivity.class);
        startActivity(intent);
    }
    public void onAllOrdersClick(View view) {
        Intent intent = new Intent(this, AllOrdersActivity.class);
        startActivity(intent);
    }
    public void onCurrentOrderClick(View view) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

}