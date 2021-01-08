package com.example.model;

import java.util.ArrayList;

public class Cart {
    public final ArrayList<Cart> carts = new ArrayList<>();
    private static Cart instance;
    private String itemName;
    private int itemPrice;
    private int itemQuantity;
    private int itemImage;

    public static Cart getInstance() {
        if( instance == null ) {
            instance = new Cart();
        }
        return instance;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getItemPrice() {
        return this.itemPrice;
    }

    public int getItemQuantity() { return this.itemQuantity; }

    public int getItemImage() {
        return this.itemImage;
    }
}
