package com.rio.foodie;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    TextView foodNameTextView;
    TextView quantityTextView;
    TextView chefNoteTextView;
    TextView deliveryOptTextView;
    Button dateButton;
    TextView orderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dateButton = findViewById(R.id.date_button);
        dateButton.setText(setInitialDate());

        quantityTextView = findViewById(R.id.quantity_textView_cart);

        Intent intent = getIntent();
        String foodName = intent.getStringExtra(MainActivity.EXTRA_FOOD_NAME);
        boolean deliveryOpt = intent.getBooleanExtra(MainActivity.EXTRA_DELIVERY_OPT, false);
        byte quantity = intent.getByteExtra(MainActivity.EXTRA_FOOD_QUANTITY, (byte) 0);
        String chefNote = intent.getStringExtra(MainActivity.EXTRA_CHEF_NOTE);
        deliveryOptTextView = findViewById(R.id.deliveryOpt_textView_cart);
        ArrayList<Dish> dishes = intent.getParcelableArrayListExtra(MainActivity.EXTRA_DISHES);
        orderTextView = findViewById(R.id.order_textView);

        if (deliveryOpt) {
            deliveryOptTextView.setText("Take Away");
        } else {
            deliveryOptTextView.setText("Dine In");
        }
        if (dishes != null) {
            String orders = "";
            for (Dish dish : dishes) {
                orders += dish.getName() + " " + dish.getQuantity().toString() + ", ";
            }

            // Hapus koma di akhir string
            if (orders.length() > 0) {
                orders = orders.substring(0, orders.length() - 2);
            }


            foodNameTextView = findViewById(R.id.foodName_textView_cart);
            quantityTextView = findViewById(R.id.quantity_textView_cart);
            chefNoteTextView = findViewById(R.id.chefNote_TextView_cart);
            orderTextView = findViewById(R.id.order_textView);

            foodNameTextView.setText(foodName);
            quantityTextView.setText(String.valueOf(quantity));
            chefNoteTextView.setText(chefNote);
            orderTextView.setText(orders);
        }
    }

    public void processDatePickerResult(Calendar calendar) {
        int date = calendar.get(Calendar.DATE);
        String month = new SimpleDateFormat("MMM").format(calendar.getTime());
        int year = calendar.get(Calendar.YEAR);
        dateButton.setText(date + " " + month + " " + year);
    }

    private String setInitialDate() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day + " " + month + " " + year;
    }

    public void showDatePicker(View view) {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void cookNow(View view) {
        AlertDialog.Builder alertDialog = new
                AlertDialog.Builder(CartActivity.this);
        alertDialog.setTitle("Cook Now");
        alertDialog.setMessage("The kitchen will start cooking the order.");
                alertDialog.show();
    }
}
