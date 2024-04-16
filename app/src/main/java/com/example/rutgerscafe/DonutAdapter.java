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
import android.widget.Spinner;
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
        View view = inflater.inflate(R.layout.donut_row_view, parent, false);
        return new DonutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonutAdapter.DonutHolder holder, int position) {
        holder.tv_name.setText(String.valueOf(donuts.get(position).getDonutFlavor()));
        holder.tv_price.setText(String.valueOf(donuts.get(position).price()));
        holder.im_item.setImageResource(donuts.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return donuts.size();
    }

    public static class DonutHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        private Button btn_add;
        private ConstraintLayout parentLayout; //this is the row layout
        private Spinner sp_quantity;

        public DonutHolder(@NonNull View itemView) {

            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            sp_quantity = itemView.findViewById(R.id.sp_quantity);
            parentLayout = itemView.findViewById(R.id.rowLayout);

        }
    }
}
