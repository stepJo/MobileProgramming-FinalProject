package com.example.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.R;
import com.example.activity.OrderActivity;
import com.example.model.Drink;
import com.example.utility.Utility;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {
    private ArrayList<Drink> drinks;

    public DrinkAdapter(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public DrinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list__drink, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TextView drinkName = holder.drinkName;
        final TextView drinkPrice = holder.drinkPrice;
        final TextView drinkPriceWithoutFormat = holder.drinkPrice;
        final CircleImageView drinkImage = holder.drinkImage;

        drinkName.setText(drinks.get(position).getName());
        drinkPrice.setText(Utility.RupiahFormat(drinks.get(position).getPrice()));
        drinkPriceWithoutFormat.setText(String.valueOf(drinks.get(position).getPrice()));
        //Using glide library to increase perfomance
        Glide.with(drinkImage.getContext()).load(drinks.get(position).getImage()).into(drinkImage);

        //click handler for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), OrderActivity.class);
                i.putExtra("drinkName", drinkName.getText().toString());
                i.putExtra("drinkPrice", drinkPrice.getText().toString());
                i.putExtra("drinkPriceWithoutFormat", drinkPrice.getText().toString());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinks.size() > 0 ? drinks.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView drinkName;
        public TextView drinkPrice;
        public CircleImageView drinkImage;

        ViewHolder(View v) {
            super(v);

            drinkName = v.findViewById(R.id.drinkName);
            drinkPrice = v.findViewById(R.id.drinkPrice);
            drinkImage = v.findViewById(R.id.drinkImage);
        }
    }
}