package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.R;
import com.example.model.Cart;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.ViewHolder> {
    private ArrayList<Cart> carts;

    public PayAdapter(ArrayList<Cart> carts) {
        this.carts = carts;
    }

    @NonNull
    @Override
    public PayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list__pay, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TextView itemName = holder.itemName;
        final TextView itemPrice = holder.itemPrice;
        final TextView itemQuantity = holder.itemQuantity;
        final TextView itemTotalPrice = holder.itemTotalPrice;
        final CircleImageView itemImage = holder.itemImage;

        //Rupiah formatter
        Locale localeID = new Locale("in", "ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(localeID);

        itemName.setText(carts.get(position).getItemName());
        itemPrice.setText(rupiah.format(carts.get(position).getItemPrice()));
        itemQuantity.setText("Qty : " + carts.get(position).getItemQuantity());
        itemTotalPrice.setText(rupiah.format(carts.get(position).getItemPrice() * carts.get(position).getItemQuantity()));
        //Using glide library to increase perfomance
        Glide.with(itemImage.getContext()).load(carts.get(position).getItemImage()).into(itemImage);

        //click handler for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return carts != null ? carts.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemPrice;
        public TextView itemQuantity;
        public TextView itemTotalPrice;
        public CircleImageView itemImage;

        ViewHolder(View v) {
            super(v);

            itemName = v.findViewById(R.id.itemName);
            itemPrice = v.findViewById(R.id.itemPrice);
            itemQuantity = v.findViewById(R.id.itemQuantity);
            itemTotalPrice = v.findViewById(R.id.itemTotalPrice);
            itemImage = v.findViewById(R.id.itemImage);
        }
    }

}