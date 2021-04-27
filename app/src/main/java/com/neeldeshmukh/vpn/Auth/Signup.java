package com.neeldeshmukh.vpn.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.neeldeshmukh.vpn.R;
import com.neeldeshmukh.vpn.Model.User;

public class Signup extends AppCompatActivity {
    /*
     * Varaible Definiton
     */
    TextInputEditText name,email,password;
    Button signup_btn;
    TextView login_btn;
    FirebaseAuth firebaseAuth;
    DatabaseReference mDatabaseReference;

    private static String TAG = "Signup Error";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /*
         * Variable Initilization
         */
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);
        signup_btn = findViewById(R.id.signup_btn);
        firebaseAuth = FirebaseAuth.getInstance();

        /*
         * If User is Already Logged in then Skips the Login
         */
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        /*
         * Check if name, email and password are not empty
         */
        if(name.getText().toString() == null){
            name.setError("Enter name");
        }else if(email.getText().toString() == null){
            email.setError("Enter email");
        }else if(password.getText().toString() == null){
            email.setError("Enter password");
        }

        /*
         * Send Data to Database and Forward to Main activity
         */
        //Signup button login
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signupfun(email.getText().toString(),password.getText().toString(),firebaseAuth);
            }
        });

        /*
         * Redirect to Login Activity
         */
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }
    /*
     * Common Login Function
     */
    public void signupfun(String email, String password, final FirebaseAuth mAuth){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //Sends verification Email to the User
                            user.sendEmailVerification();

                            User appuser = new User(user.getUid(),user.getDisplayName(),user.getEmail(),password);
                            mDatabaseReference.child("Users").child(appuser.getUuid()).setValue(appuser);
                            startActivity(new Intent(Signup.this,Login.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });

    }
}






