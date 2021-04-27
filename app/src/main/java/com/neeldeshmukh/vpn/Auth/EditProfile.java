package com.neeldeshmukh.vpn.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.neeldeshmukh.vpn.R;

public class EditProfile extends AppCompatActivity {
    /*
     * Varaible Definiton
     */
    EditText email;
    TextView save_btn;
    FirebaseAuth firebaseAuth;
    ProgressDialog pd;
    private static String TAG = "Edit Profile Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        /*
         * Variable Initilization
         */

        email = findViewById(R.id.email);

        save_btn = findViewById(R.id.save_btn);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        email.setText(""+firebaseUser.getEmail());

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(getApplicationContext());
                pd.setTitle("Updating email address");
                pd.setMessage("Updating email....");
                pd.show();
                if(firebaseUser.getEmail().equalsIgnoreCase(email.getText().toString())){
                    Toast.makeText(EditProfile.this, "Same Email", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }else{

                    firebaseUser.updateEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            firebaseUser.sendEmailVerification();
                            pd.dismiss();
                        }
                    });
                }


            }
        });


    }
}
