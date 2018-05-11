package com.icono.organicanimal;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class LoadingActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorDarkMint));
        }

        iv = findViewById(R.id.iv);
        Animation ani = AnimationUtils.loadAnimation(this, R.anim.loding);
        iv.setAnimation(ani);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation ani2 = AnimationUtils.loadAnimation(LoadingActivity.this, R.anim.loding2);
                iv.setAnimation(ani2);
                Glide.with(LoadingActivity.this).load(R.drawable.logo2).into(iv);
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 7000);
    }
}
