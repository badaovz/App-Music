package com.example.datvl.testcn.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datvl.testcn.R;

public class SplashActivity extends AppCompatActivity {

    ImageView imageload;
    TextView tvload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageload = findViewById(R.id.ivload);
        tvload    = findViewById(R.id.tvload);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.my_transaction);
        tvload.startAnimation(animation);
        imageload.startAnimation(animation);

        final Intent  intent = new Intent(this,MainActivity.class);
        Thread thread = new Thread(){
            public void run() {
                   try {
                       sleep(5000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }finally {
                       startActivity(intent);
                       finish();
                   }

            }
        };
            thread.start();

    }
}
