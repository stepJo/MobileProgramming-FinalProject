package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.R;
import com.example.adapter.PayAdapter;
import com.example.model.Cart;
import com.example.utility.Utility;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {
    private ArrayList<Cart> carts = Cart.getInstance().carts;
    ;
    private RecyclerView cartView;
    private PayAdapter payAdapter;
    private TextView totalPlaceholder, emptyPlaceholder, totalLable, successMsg;
    private Button homeMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        //Set title
        setTitle("Order Complete");

        //Get the recycle view
        cartView = findViewById(R.id.cartView);

        //Set layout for recycle view
        cartView.setLayoutManager(new LinearLayoutManager(this));
        cartView.setHasFixedSize(true);

        //Show data with adapter
        Intent i = getIntent();

        String itemName = "";
        int itemPrice = 0;
        int itemQuantity = 0;

        //Validate cart
        if (i.hasExtra("itemName") && i.hasExtra("itemPriceWithoutFormat") && i.hasExtra("itemQuantity")) {
            itemName = i.getStringExtra("itemName");
            itemPrice = Integer.parseInt(i.getStringExtra("itemPriceWithoutFormat"));
            itemQuantity = Integer.parseInt(i.getStringExtra("itemQuantity"));
        }

        if (itemName != "" && itemPrice != 0 && itemQuantity != 0) {
            Cart cart = new Cart();
            cart.setItemName(itemName);
            cart.setItemPrice(itemPrice);
            cart.setItemQuantity(itemQuantity);
            if(itemName.equals("Apple Juice")) {
                cart.setItemImage(R.drawable.img__apple_juice);
            }
            else if(itemName.equals("Avocado Juice")) {
                cart.setItemImage(R.drawable.img__avocado_juice);
            }
            else if(itemName.equals("Mango Juice")) {
                cart.setItemImage(R.drawable.img__mango_juice);
            }
            else if(itemName.equals("Strawberry Juice")) {
                cart.setItemImage(R.drawable.img__strawberry_juice);
            }
            else if(itemName.equals("Mineral Water")) {
                cart.setItemImage(R.drawable.img__mineral_water);
            }
            else {
                cart.setItemImage(R.drawable.img__coca_cola);
            }
            carts.add(cart);
        }

        //Attaching data
        payAdapter = new PayAdapter(carts);
        cartView.setAdapter(payAdapter);

        //Check if data is empty
        successMsg = findViewById(R.id.successMsg);
        totalLable = findViewById(R.id.totalLable);
        totalPlaceholder = findViewById(R.id.totalPlaceholder);
        emptyPlaceholder = findViewById(R.id.emptyPlaceholder);

        if (carts.isEmpty()) {
            successMsg.setVisibility(View.GONE);
            totalLable.setVisibility(View.GONE);
            cartView.setVisibility(View.GONE);
            totalPlaceholder.setVisibility(View.GONE);
            emptyPlaceholder.setVisibility(View.VISIBLE);
        } else {
            int total = Utility.sumTotalCarts(carts);

            totalPlaceholder.setText(Utility.RupiahFormat(total));

            successMsg.setVisibility(View.VISIBLE);
            totalLable.setVisibility(View.VISIBLE);
            cartView.setVisibility(View.VISIBLE);
            totalPlaceholder.setVisibility(View.VISIBLE);
            emptyPlaceholder.setVisibility(View.GONE);
        }

        //Go to home page
        homeMenuBtn = findViewById(R.id.homeMenuBtn);
        homeMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart.getInstance().carts.clear();

                Intent i = new Intent(PayActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
