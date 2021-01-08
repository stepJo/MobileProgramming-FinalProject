package com.example.utility;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import com.example.model.Cart;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Utility {
    public static int sumTotalCarts(ArrayList<Cart> carts) {
        int total = 0;

        for(Cart cart : carts) {
            total += (cart.getItemPrice() * cart.getItemQuantity());
        }

        return total;
    }

    public static String RupiahFormat(int price) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(localeID);

        return rupiah.format(price);
    }

    public static void displayToastNotification(View view, Toast toast, String message) {
        toast = Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM| Gravity.RIGHT, 70, 50);
        toast.show();
    }
}
