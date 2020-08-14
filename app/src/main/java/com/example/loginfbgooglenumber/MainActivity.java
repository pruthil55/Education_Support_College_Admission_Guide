package com.example.loginfbgooglenumber;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2100;
    //Hooks
    View first,second,third,fourth,fifth,sixth;
    TextView tv_application_name;
    ImageView iv_logo;
    //Animations
    Animation topAnimantion,bottomAnimation,middleAnimation,zoom_out,fade_in;

    public static int introScreenNext = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        LoadData();

        // Hooks
        first = findViewById(R.id.first_line);
        second = findViewById(R.id.second_line);
        third = findViewById(R.id.third_line);
        fourth = findViewById(R.id.fourth_line);
        fifth = findViewById(R.id.fifth_line);
        sixth = findViewById(R.id.sixth_line);
        iv_logo = findViewById(R.id.iv_logo);
        tv_application_name = findViewById(R.id.tv_application_name);

        //Animation Calls
        topAnimantion = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);
        fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        //-----------Setting Animations to the elements of SplashScreen--------

        first.setAnimation(topAnimantion);
        second.setAnimation(topAnimantion);
        third.setAnimation(topAnimantion);
        fourth.setAnimation(topAnimantion);
        fifth.setAnimation(topAnimantion);
        sixth.setAnimation(topAnimantion);
        iv_logo.setAnimation(fade_in);
        tv_application_name.setAnimation(bottomAnimation);

        //Splash Screen Code to call new Activity after some time
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (MainActivity.introScreenNext == 0) {
                    Intent intent = new Intent(MainActivity.this, animation.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, LogoutActivityDrawer.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);
    }


    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("introscreen",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("counterkey",MainActivity.introScreenNext);
        editor.apply();
    }
    public void LoadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("introscreen",MODE_PRIVATE);
        MainActivity.introScreenNext = sharedPreferences.getInt("counterkey",MODE_PRIVATE);

        // Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveData();
    }

}




