package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.R;
import com.example.utility.Utility;

public class OrderActivity extends AppCompatActivity {
    private TextView drinkNameDetail, drinkPriceDetail, drinkPriceDetailWithoutFormat;
    private EditText itemQuantity;
    private Button orderMoreBtn, myOrderMenuBtn, addToCartBtn;
    public Toast cartToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //Set back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set title
        setTitle("Order");

        Intent i = getIntent();

        drinkNameDetail = findViewById(R.id.drinkNameDetail);
        drinkNameDetail.setText(i.getStringExtra("drinkName"));

        drinkPriceDetail = findViewById(R.id.drinkPriceDetail);
        drinkPriceDetail.setText(i.getStringExtra("drinkPrice"));

        drinkPriceDetailWithoutFormat = findViewById(R.id.drinkPriceDetail);
        drinkPriceDetailWithoutFormat.setText(i.getStringExtra("drinkPriceWithoutFormat"));

        //Add item quantity to cart
        itemQuantity = findViewById(R.id.qty);
        addToCartBtn = findViewById(R.id.addToCartBtn);
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.displayToastNotification(view, cartToast, drinkNameDetail.getText().toString() + " added to cart");

                Intent i = new Intent(OrderActivity.this, MyOrderActivity.class);
                i.putExtra("itemName", drinkNameDetail.getText().toString());
                i.putExtra("itemPrice", drinkPriceDetail.getText().toString());
                i.putExtra("itemPriceWithoutFormat", drinkPriceDetailWithoutFormat.getText().toString());
                i.putExtra("itemQuantity", itemQuantity.getText().toString());

                startActivity(i);
            }
        });

        //Back to drink page
        orderMoreBtn = findViewById(R.id.orderMoreBtn);
        orderMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderActivity.this, DrinkActivity.class);
                startActivity(i);
            }
        });

        //Go to my order page
        myOrderMenuBtn = findViewById(R.id.payMenuBtn);
        myOrderMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderActivity.this, MyOrderActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
