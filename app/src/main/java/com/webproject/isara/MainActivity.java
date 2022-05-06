package com.webproject.isara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.webproject.isara.View.HomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ImageView biglogo_imageview = findViewById(R.id.biglogo_imageview);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.logoanim);
        biglogo_imageview.startAnimation(anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, 2000);
    }
}