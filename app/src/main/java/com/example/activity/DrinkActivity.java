package com.example.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.example.R;
import com.example.adapter.DrinkAdapter;
import com.example.model.Cart;
import com.example.model.Drink;
import java.util.ArrayList;

public class DrinkActivity extends AppCompatActivity {
    private ArrayList<Drink> drinks = new ArrayList<>();
    private RecyclerView drinkView;
    private DrinkAdapter drinkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Set actionbar background color and back button
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F44336")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set title
        setTitle("Drink Menu");

        //Get the recycle view
        drinkView = findViewById(R.id.drinkView);

        //Set layout for recycle view
        drinkView.setLayoutManager(new LinearLayoutManager(this));
        drinkView.setHasFixedSize(true);
        RecyclerView.LayoutManager layout = new GridLayoutManager(this, 2);
        drinkView.setLayoutManager(layout);
        //layoutManager = new LinearLayoutManager(this);
        //drinkView.setLayoutManager(layoutManager);

        //Show data with adapter
        drinks = Drink.drinksSeeder();
        drinkAdapter = new DrinkAdapter(drinks);
        drinkView.setAdapter(drinkAdapter);
    }

    @Override
    public boolean onSupportNavigateUp(){
        if (!Cart.getInstance().carts.isEmpty()) {
            finish();
            return true;
        }
        else {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
    }
}
