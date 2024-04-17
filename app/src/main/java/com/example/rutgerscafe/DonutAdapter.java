package com.example.rutgerscafe;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//Temporary system import for testing
import java.lang.System.*;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutHolder> {
    private Context context;
    private ArrayList<Donut> donuts;

    public DonutAdapter(Context context, ArrayList<Donut> donuts) {
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
        holder.donut = donuts.get(position);
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
        private Donut donut;
        private int selectedQuantity;

        public DonutHolder(@NonNull View itemView) {

            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            sp_quantity = itemView.findViewById(R.id.sp_quantity);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            setAddButtonOnClick(itemView);
        }
        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         *
         * @param itemView
         */
        private void setAddButtonOnClick(@NonNull View itemView) {
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Add to order");
                    alert.setMessage(tv_name.getText().toString());
                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            OrderData orderData = OrderData.getInstance();
                            //if(orderData.getCurrentOrder() == null){
                            //    orderData.startNewOrder();
                            //}
                            donut.setQuantity(Integer.parseInt(String.valueOf(sp_quantity.getSelectedItem())));
                            orderData.getCurrentOrder().add(donut);
                            Toast.makeText(itemView.getContext(),
                                    orderData.getCurrentOrder().getMenuList()[orderData.getCurrentOrder().getAddIndex()-1].toString()
                                    +" added",
                                    Toast.LENGTH_LONG).show();
                        }
                        //handle the "NO" click
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    tv_name.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }
    }
}

        /*
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }*/
