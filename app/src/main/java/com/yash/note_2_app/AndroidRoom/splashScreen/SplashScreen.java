package com.yash.note_2_app.AndroidRoom.splashScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.yash.note_2_app.MainActivity;
import com.yash.note_2_app.R;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ConstraintLayout constraintLayout=(ConstraintLayout) findViewById(R.id.splash);
        AnimationDrawable animationDrawable2;
        animationDrawable2=(AnimationDrawable) constraintLayout.getBackground();
        animationDrawable2.setEnterFadeDuration(900);
        animationDrawable2.setExitFadeDuration(900);
        animationDrawable2.start();
        Handler handler=new Handler();
        final Intent intent;
        intent=new Intent(SplashScreen.this,MainActivity.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },4000);



    }
}