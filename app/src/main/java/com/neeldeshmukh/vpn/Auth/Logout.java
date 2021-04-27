package com.neeldeshmukh.vpn.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;


public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_logout);
        //This will logout the user and redirect to the Login Activity
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Logout.this, Login.class));
        finish();
    }
}
