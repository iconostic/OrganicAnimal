package com.icono.organicanimal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.pnikosis.materialishprogress.ProgressWheel;

public class ProgressActivity extends AppCompatActivity {

    ProgressWheel wheel;
    RelativeLayout layout;
    ProgressBar progressBar;

    static boolean isProgress = true;
    static boolean isrun = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        //wheel = findViewById(R.id.progress_wheel);
        layout = findViewById(R.id.wheel_layout);
        progressBar = findViewById(R.id.progressbar);

        layout.setVisibility(View.VISIBLE);
        while(isProgress){
            if(isrun){
                layout.setVisibility(View.GONE);
                finish();
                break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

//        Log.i("myerror","progress : " + isProgress);
//
//        if (isProgress){
//            layout.setVisibility(View.GONE);
//            wheel.stopSpinning();
//            finish();
//            return;
//        }else{
//            layout.setVisibility(View.VISIBLE);
//            wheel.spin();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    public static void workProgress(){
        isProgress = true;
        isrun = false;
    }

    public static void stopProgress(){
        isrun = true;
        isProgress = false;
    }
}
