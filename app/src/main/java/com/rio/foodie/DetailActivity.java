package com.rio.foodie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView foodNameTextView;
    public static final String EXTRA_ORDER = "ORDER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String foodName =
                intent.getStringExtra(MainActivity.EXTRA_FOOD_NAME);
        foodNameTextView = findViewById(R.id.foodName_textView);
        foodNameTextView.setText(foodName);
    }
    public void orderOne(View view) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ORDER, 1);
        setResult(RESULT_OK, intent);
        finish();
    }
    public void share(View view) {
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Share this with ")
                .setText("Fried Rice")
                .startChooser();
    }
}