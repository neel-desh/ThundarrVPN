package com.neeldeshmukh.vpn.Startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;

import com.neeldeshmukh.vpn.Auth.Login;

import com.neeldeshmukh.vpn.R;



public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(WelcomeActivity.this, Login.class);
                startActivity(i);
                finish();
            }
        }, 4800);

    }
}
