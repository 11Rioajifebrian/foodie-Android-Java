package com.rio.foodie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, passwordEditText, confirmPasswordEditText, fullNameEditText;
    private RadioGroup genderRadioGroup;
    private CheckBox agreeCheckBox;
    private TextView confirmPasswordErrorTextView;
    private TextInputLayout confirmPasswordInputLayout, fullNameInputLayout;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        fullNameEditText = findViewById(R.id.fullNameEditText);
        confirmPasswordErrorTextView =  findViewById(R.id.confirmPasswordErrorTextView);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        agreeCheckBox = findViewById(R.id.agreeCheckBox);
        registerButton = findViewById(R.id.registerButton);
        confirmPasswordInputLayout = findViewById(R.id.confirmPasswordInputLayout);
        fullNameInputLayout = findViewById(R.id.fullNameInputLayout);


        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (agreeCheckBox.isChecked()) {
                    register();
                } else {
                    Toast.makeText(RegisterActivity.this, "Please agree to the terms.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void register() {
        String typedUsername = usernameEditText.getText().toString();
        String typedEmail = emailEditText.getText().toString();
        String typedPassword = passwordEditText.getText().toString();
        String typedFullName = fullNameEditText.getText().toString();
        String typedConfirmPassword = confirmPasswordEditText.getText().toString();

        // Mendapatkan id RadioButton yang dipilih
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
        String typedGender = selectedGenderRadioButton.getText().toString();

        // Cek apakah password dan konfirmasi password cocok
        if (TextUtils.isEmpty(typedPassword) || TextUtils.isEmpty(typedConfirmPassword) || !typedPassword.equals(typedConfirmPassword)) {
            confirmPasswordErrorTextView.setVisibility(View.VISIBLE);
            return;
        } else {
            confirmPasswordErrorTextView.setVisibility(View.GONE);
        }

        // Simpan data ke SharedPreferences
        SharedPreferences sharedPreferences =
                getApplicationContext().getSharedPreferences("FOODIE_SP",
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SAVED_USERNAME", typedUsername);
        editor.putString("SAVED_EMAIL", typedEmail);
        editor.putString("SAVED_PASSWORD", typedPassword);
        editor.putString("SAVED_FULLNAME", typedFullName);
        editor.putString("SAVED_GENDER", typedGender);
        editor.apply();

        // Pindah ke AccountActivity dan kirim data
        Intent intent = new Intent(RegisterActivity.this, AccountActivity.class);
        intent.putExtra("USERNAME", typedUsername);
        intent.putExtra("EMAIL", typedEmail);
        intent.putExtra("PASSWORD", typedPassword);
        intent.putExtra("FULLNAME", typedFullName);
        intent.putExtra("GENDER", typedGender);
        startActivity(intent);
    }
}
