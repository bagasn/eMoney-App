package com.bagasn.demoemoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.bagasn.demoemoney.util.SPSession;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_splash);

        new Thread(() -> {
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                Log.e(TAG, "onCreate: ", e);
            } finally {
                startApp();
            }
        }).start();

        startAnimation();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Initiating..", Toast.LENGTH_SHORT)
                .show();
    }

    private void startAnimation() {
        Animation bannerAnimation = AnimationUtils
                .loadAnimation(this, R.anim.move_left_to_center);
        View bannerView = findViewById(R.id.text_banner);
        bannerView.startAnimation(bannerAnimation);
    }

    private void startApp() {
        if (isFinishing())
            return;
        
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