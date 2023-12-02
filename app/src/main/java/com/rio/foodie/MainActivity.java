package com.rio.foodie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private byte quantity = 0;
    private TextView quantityTextView;
    private String foodName = "Fried Rice";
    public static final int ORDER_REQUEST = 1;
    public static final String EXTRA_FOOD_NAME = "NAME";
    public static final String EXTRA_FOOD_QUANTITY = "QUANTITY";
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    private String chefNote;
    private EditText chefNoteEditText;
    public static final String EXTRA_CHEF_NOTE = "CHEFNOTE";
    private boolean isTakeAway;
    public static final String EXTRA_DELIVERY_OPT = "DELIVERYOPT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        quantityTextView = findViewById(R.id.quantityTextView);
        chefNoteEditText = findViewById(R.id.chefNote_editText);
        if (savedInstanceState != null) {
            quantity = savedInstanceState.getByte("SAVED_QUANTITY");
            quantityTextView.setText(Byte.toString(quantity));
        }
        Log.d(LOG_TAG, "onCreate");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putByte("SAVED_QUANTITY", quantity);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
    public void launchAboutActivity(MenuItem item) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Launch About...", Toast.LENGTH_SHORT).show();
    }
    public void addOne(View view) {
        quantity++;
        quantityTextView.setText(Byte.toString(quantity));

    }
    public void substractOne(View view) {
        quantity --;
        quantityTextView.setText(Byte.toString(quantity));
    }
    public void launchDetailActivity(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_FOOD_NAME, foodName);
        startActivityForResult(intent, ORDER_REQUEST);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            @Nullable Intent data
    ) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ORDER_REQUEST) {
            if (resultCode == RESULT_OK) {
                int orderRequest = data.getIntExtra(DetailActivity.EXTRA_ORDER,
                        0);
                quantityTextView.setText(String.valueOf(orderRequest));
            }
        }
    }
    public void launchAboutActivity(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void launchCartActivity(View view) {
        chefNote = chefNoteEditText.getText().toString();
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra(EXTRA_FOOD_NAME, foodName);
        intent.putExtra(EXTRA_FOOD_QUANTITY, quantity);
        intent.putExtra(EXTRA_CHEF_NOTE, chefNote);
        startActivityForResult(intent, ORDER_REQUEST);
        Toast.makeText(this, "Launch Cart", Toast.LENGTH_SHORT).show();
    }
    public void showCart(View view) {
        isTakeAway = true;
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra(EXTRA_DELIVERY_OPT, isTakeAway);
        startActivity(intent);
    }
    public void setDiningOption(View view) {
        boolean radioChecked = ((RadioButton) view).isChecked();
        if (view.getId() == R.id.dine_radio) {
            if (radioChecked) {
                isTakeAway = false;
            }
        } else if (view.getId() == R.id.take_radio) {
            if (radioChecked) {
                isTakeAway = true;
            }
        }
    }
}
