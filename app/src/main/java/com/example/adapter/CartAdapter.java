package com.example.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.R;
import com.example.activity.DrinkActivity;
import com.example.model.Cart;
import com.example.utility.Utility;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<Cart> carts;

    public CartAdapter(ArrayList<Cart> carts) {
        this.carts = carts;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list__cart, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TextView itemName = holder.itemName;
        final TextView itemPrice = holder.itemPrice;
        final TextView itemQuantity = holder.itemQuantity;
        final TextView itemTotalPrice = holder.itemTotalPrice;
        final CircleImageView itemImage = holder.itemImage;

        itemName.setText(carts.get(position).getItemName());
        itemPrice.setText(Utility.RupiahFormat(carts.get(position).getItemPrice()));
        itemQuantity.setText("Qty : " + carts.get(position).getItemQuantity());
        itemTotalPrice.setText(Utility.RupiahFormat(carts.get(position).getItemPrice() * carts.get(position).getItemQuantity()));
        //Using glide library to increase perfomance
        Glide.with(itemImage.getContext()).load(carts.get(position).getItemImage()).into(itemImage);
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
        public Button deleteButton;
        public Toast emptyToast;
        public CircleImageView itemImage;

        ViewHolder(View v) {
            super(v);

            itemName = v.findViewById(R.id.itemName);
            itemPrice = v.findViewById(R.id.itemPrice);
            itemQuantity = v.findViewById(R.id.itemQuantity);
            itemTotalPrice = v.findViewById(R.id.itemTotalPrice);
            itemImage = v.findViewById(R.id.itemImage);
            deleteButton = v.findViewById(R.id.deleteBtn);

            deleteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    removeItem(getAdapterPosition());

                    if(carts.isEmpty()) {
                        Utility.displayToastNotification(view, emptyToast, "Cart currently empty");

                        Intent i = new Intent(view.getContext(), DrinkActivity.class);
                        view.getContext().startActivity(i);
                    }
                    else {
                        Utility.displayToastNotification(view, emptyToast, "Item remove from cart");

                        int total = Utility.sumTotalCarts(carts);

                        TextView totalPlaceholder = ((Activity) view.getContext()).findViewById(R.id.totalPlaceholder);

                        totalPlaceholder.setText(Utility.RupiahFormat(total));
                    }
                }
            });
        }
    }

    public void removeItem(int item) {
        carts.remove(item);
        notifyItemRemoved(item);
        notifyItemRangeChanged(item, carts.size());
    }
}