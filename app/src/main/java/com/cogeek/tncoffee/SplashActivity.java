package com.cogeek.tncoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView iv = findViewById(R.id.img_splash);

        AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
        animation1.setDuration(2000);
//        animation1.setStartOffset(0000);
        animation1.setFillAfter(true);
        iv.startAnimation(animation1);

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(3*1000);
                    onLaunch();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }

    private void onLaunch() {
        startActivity(new Intent(this, LaunchActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}