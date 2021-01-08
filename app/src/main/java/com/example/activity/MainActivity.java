package com.example.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private Button drinkMenuBtn, myOrderMenuBtn;
    private CircleImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set actionbar background to gradient
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom__gradient_background));

        //Set title
        setTitle("EzyFoody");

        //Set user image
        userImage = findViewById(R.id.userImage);
        Glide.with(userImage.getContext()).load(R.drawable.img__user).into(userImage);

        drinkMenuBtn = findViewById(R.id.drinkMenuBtn);
        drinkMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePage(DrinkActivity.class);
            }
        });

        myOrderMenuBtn = findViewById(R.id.payMenuBtn);
        myOrderMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePage(MyOrderActivity.class);
            }
        });
    }

    public void changePage(Class<?> to) {
        Intent intent = new Intent(this, to);
        startActivity(intent);
    }
}
