package com.bagasn.demoemoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.bagasn.demoemoney.util.SPSession;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText textEmail;
    private EditText textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_login);

        textEmail = findViewById(R.id.text_email);
        textPassword = findViewById(R.id.text_password);

        findViewById(R.id.btn_login)
                .setOnClickListener((view -> onClickLogin()));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void onClickLogin() {
        String email = textEmail.getText().toString();
        String pass = textPassword.getText().toString();

        if (!Pattern.matches("[A-Za-z0-9._-]+@[A-Za-z0-9]+\\.[a-z.]+", email)) {
            textEmail.setError("Invalid email format.");
            textEmail.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            textPassword.setError("Password cannot be empty.");
            textPassword.requestFocus();
            return;
        }

        if (email.equals("user@mail.com")) {
            if (pass.equals("123")) {
                setSPSession();

                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
                finish();
                return;
            }
        }

        Toast.makeText(this, "Your email hasn't registered.", Toast.LENGTH_SHORT)
                .show();
    }

    private void setSPSession() {
        SPSession.init(this)
                .putString(SPSession.keyUserLoggedIn, "True")
                .putString(SPSession.keyUserName, "Bagas Nasution")
                .putString(SPSession.keyUserEmail, "user@mail.com")
                .putString(SPSession.keyUserNoId, "202012140001")
                .putString(SPSession.keyUserRank, "Gold");
    }
}