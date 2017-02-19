package com.salemwika.andrade.salemwika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        ImageView ball = (ImageView) findViewById(R.id.imageView);
        ImageView cap = (ImageView) findViewById(R.id.imageView6);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_down_in);
        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);

        ball.setAnimation(anim);
        cap.setAnimation(anim1);

        Thread myTh = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        myTh.start();
    }


}
