package com.example.model;

import com.example.R;

import java.util.ArrayList;

public class Drink {
    private String name;
    private int price;
    private int image;

    public Drink(String name, int price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getImage() {
        return this.image;
    }

    public static ArrayList<Drink> drinksSeeder() {
        ArrayList<Drink> drinks = new ArrayList<>();

        drinks.add(new Drink("Apple Juice", 17000, R.drawable.img__apple_juice));
        drinks.add(new Drink("Avocado Juice", 25400, R.drawable.img__avocado_juice));
        drinks.add(new Drink("Mango Juice", 19000, R.drawable.img__mango_juice));
        drinks.add(new Drink("Strawberry Juice", 22500, R.drawable.img__strawberry_juice));
        drinks.add(new Drink("Mineral Water", 4500, R.drawable.img__mineral_water));
        drinks.add(new Drink("Coca Cola", 8000, R.drawable.img__coca_cola));

        return drinks;
    }
}
