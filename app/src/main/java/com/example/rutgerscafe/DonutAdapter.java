package com.example.rutgerscafe;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutHolder>{
    private Context context;
    private ArrayList<Donut> donuts;

    public DonutAdapter(Context context, ArrayList<Donut> donuts){
        this.context = context;
        this.donuts = donuts;
    }
    @NonNull
    @Override
    public DonutAdapter.DonutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new DonutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonutAdapter.DonutHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DonutHolder extends RecyclerView.ViewHolder {

        public DonutHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
