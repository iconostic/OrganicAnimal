package com.icono.organicanimal;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoadingActivity extends AppCompatActivity {

    TextView txt_loading1, txt_loading2, txt_loading3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        txt_loading1 = findViewById(R.id.txt_loading1);
        txt_loading2 = findViewById(R.id.txt_loading2);
        txt_loading3 = findViewById(R.id.txt_loading3);

        txt_loading1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/nanumpen.ttf"));
        txt_loading2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/nanumpen.ttf"));
        txt_loading3.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/nanumpen.ttf"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
}
