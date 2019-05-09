package com.example.datvl.testcn.Activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.datvl.testcn.R;

public class SplashActivity extends AppCompatActivity {

    ImageView imageload;
    TextView tvload;
    SeekBar sk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageload = findViewById(R.id.ivload);
        tvload    = findViewById(R.id.tvload);
        sk        = findViewById(R.id.sbload);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.my_transaction);
        tvload.startAnimation(animation);
        imageload.startAnimation(animation);

        final CountDownTimer countDownTimer = new CountDownTimer(3000,500) {
            @Override
            public void onTick(long l) {
                sk.setProgress(sk.getProgress()+15);
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
        final Intent  intent = new Intent(this,MainActivity.class);
        Thread thread = new Thread(){
            public void run() {
                   try {

                       sleep(3000);
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
