package com.cengizhanyagiz.hammalmobile.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.cengizhanyagiz.hammalmobile.R;

public class MainActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Handler h =new Handler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivitySplash.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        },3000);
    }
}