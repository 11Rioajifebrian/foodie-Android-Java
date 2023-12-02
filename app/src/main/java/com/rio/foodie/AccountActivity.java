package com.rio.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AccountActivity extends AppCompatActivity {

    private TextView usernameTextView, emailTextView, passwordTextView,fullNameTextView, genderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar
        );

        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById(R.id.confirmPasswordTextView);
        fullNameTextView = findViewById(R.id.fullNameTextView);
        genderTextView = findViewById(R.id.genderTextView);

        // Ambil data dari Intent
        Intent intent = getIntent();
        if (intent != null) {
            String savedUsername = intent.getStringExtra("USERNAME");
            String savedEmail = intent.getStringExtra("EMAIL");
            String savedPassword = intent.getStringExtra("PASSWORD");
            String savedFullName = intent.getStringExtra("FULLNAME");
            String savedGender = intent.getStringExtra("GENDER");

            // Tampilkan data ke dalam TextView
            usernameTextView.setText(savedUsername);
            emailTextView.setText(savedEmail);
            passwordTextView.setText(savedPassword);
            fullNameTextView.setText(savedFullName);
            genderTextView.setText(savedGender);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_account) {
            // Buka MainActivity
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
