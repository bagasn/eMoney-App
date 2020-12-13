package com.bagasn.demoemoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bagasn.demoemoney.util.SPSession;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(() -> {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                Log.e(TAG, "onCreate: ", e);
            } finally {
                startApp();
            }
        }).start();
    }

    private void startApp() {
        String userLogged = SPSession.init(this).getString(SPSession.keyUserLoggedIn);

        Intent intent;
        if (userLogged.equals("True"))
            intent = new Intent(this, HomeActivity.class);
        else
            intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
        finish();
    }
}