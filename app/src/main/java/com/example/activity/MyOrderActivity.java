package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.R;
import com.example.adapter.CartAdapter;
import com.example.model.Cart;
import com.example.utility.Utility;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyOrderActivity extends AppCompatActivity {
    private ArrayList<Cart> carts = Cart.getInstance().carts;;
    private RecyclerView cartView;
    private CartAdapter cartAdapter;
    private TextView totalPlaceholder, emptyPlaceholder, totalLable;
    private Button payMenuBtn, orderMoreBtn;
    public Toast payToast;
    private ImageView emptyCartimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        //Set title
        setTitle("My Order");

        //Set back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        if(i.hasExtra("itemName") && i.hasExtra("itemPriceWithoutFormat") && i.hasExtra("itemQuantity")) {
            itemName = i.getStringExtra("itemName");
            itemPrice = Integer.parseInt(i.getStringExtra("itemPriceWithoutFormat"));
            itemQuantity = Integer.parseInt(i.getStringExtra("itemQuantity"));
        }

        if(itemName != "" && itemPrice != 0 && itemQuantity != 0) {
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
        cartAdapter = new CartAdapter(carts);
        cartView.setAdapter(cartAdapter);

        totalLable = findViewById(R.id.totalLable);
        totalPlaceholder = findViewById(R.id.totalPlaceholder);
        emptyPlaceholder = findViewById(R.id.emptyPlaceholder);
        payMenuBtn = findViewById(R.id.payMenuBtn);
        orderMoreBtn = findViewById(R.id.orderMoreBtn);
        emptyCartimage =findViewById(R.id.emptyCartImage);
        emptyCartimage.setImageResource(R.drawable.custom__ic_empty_cart);

        //Check if data is empty
        if(carts.isEmpty()) {
            totalLable.setVisibility(View.GONE);
            cartView.setVisibility(View.GONE);
            totalPlaceholder.setVisibility(View.GONE);
            payMenuBtn.setVisibility(View.GONE);
            orderMoreBtn.setVisibility(View.GONE);
            emptyPlaceholder.setVisibility(View.VISIBLE);
            emptyCartimage.setVisibility(View.VISIBLE);
        }
        else {
            int total = Utility.sumTotalCarts(carts);

            //Rupiah formatter
            totalPlaceholder.setText(Utility.RupiahFormat(total));

            totalLable.setVisibility(View.VISIBLE);
            cartView.setVisibility(View.VISIBLE);
            totalPlaceholder.setVisibility(View.VISIBLE);
            payMenuBtn.setVisibility(View.VISIBLE);
            orderMoreBtn.setVisibility(View.VISIBLE);
            emptyPlaceholder.setVisibility(View.GONE);
            emptyCartimage.setVisibility(View.GONE);
        }

        //Go to pay page
        payMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!carts.isEmpty()) {
                    Utility.displayToastNotification(view, payToast, "Success making order");

                    Intent i = new Intent(MyOrderActivity.this, PayActivity.class);
                    startActivity(i);
                }
                else {
                    Utility.displayToastNotification(view, payToast, "Your cart currently empty");

                    Intent i = new Intent(MyOrderActivity.this, DrinkActivity.class);
                    startActivity(i);
                }
            }
        });

        //Back to drink page
        orderMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MyOrderActivity.this, DrinkActivity.class);
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
