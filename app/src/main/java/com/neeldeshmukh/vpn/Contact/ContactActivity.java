package com.neeldeshmukh.vpn.Contact;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.neeldeshmukh.vpn.R;


public class ContactActivity extends Activity {
    Button btn_about_contact_submit;
    TextView tv_contact_title, tv_about_about_contact_problem, tv_about_contact_other_problems;
    //tv_about_contact_email;
    CheckBox checkbox_about_contact_advertising, checkbox_about_contact_speed, checkbox_about_contact_connecting, checkbox_about_contact_servers, checkbox_about_contact_crashed;
    EditText et_about_contact_other_problems;
    //, et_about_contact_email;
    ProgressDialog dialog;

    String advertise, speed, connecting, working, crashed, other, email;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        tv_contact_title = findViewById(R.id.tv_contact_title);
        tv_about_about_contact_problem = findViewById(R.id.tv_about_contact_problem);
        tv_about_contact_other_problems = findViewById(R.id.tv_about_contact_other_problems);
        //tv_about_contact_email = findViewById(R.id.tv_about_contact_email);

        checkbox_about_contact_advertising = findViewById(R.id.checkbox_about_contact_advertising);
        checkbox_about_contact_speed = findViewById(R.id.checkbox_about_contact_speed);
        checkbox_about_contact_connecting = findViewById(R.id.checkbox_about_contact_connecting);
        checkbox_about_contact_servers = findViewById(R.id.checkbox_about_contact_servers);
        checkbox_about_contact_crashed = findViewById(R.id.checkbox_about_contact_crashed);

        et_about_contact_other_problems = findViewById(R.id.et_about_contact_other_problems);
        //et_about_contact_email = findViewById(R.id.et_about_contact_email);

        btn_about_contact_submit = findViewById(R.id.btn_about_contact_submit);

        Typeface RobotoMedium = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        Typeface RobotoRegular = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");
        Typeface RobotoBold = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");


        tv_contact_title.setTypeface(RobotoMedium);
        tv_about_about_contact_problem.setTypeface(RobotoMedium);
        tv_about_contact_other_problems.setTypeface(RobotoMedium);
       // tv_about_contact_email.setTypeface(RobotoMedium);

        checkbox_about_contact_advertising.setTypeface(RobotoRegular);
        checkbox_about_contact_speed.setTypeface(RobotoRegular);
        checkbox_about_contact_connecting.setTypeface(RobotoRegular);
        checkbox_about_contact_servers.setTypeface(RobotoRegular);
        checkbox_about_contact_crashed.setTypeface(RobotoRegular);

        et_about_contact_other_problems.setTypeface(RobotoRegular);
        //et_about_contact_email.setTypeface(RobotoRegular);
        btn_about_contact_submit.setTypeface(RobotoBold);

        btn_about_contact_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //basic intent email sender
                String emailbody = "" ;

                   if(checkbox_about_contact_advertising.isChecked()){
                        emailbody += "Advertising";
                    }

                    // speed
                    if(checkbox_about_contact_speed.isChecked()){
                        emailbody += "\nslow speed";
                    }

                    // connecting
                    if(checkbox_about_contact_connecting.isChecked()){
                        emailbody += "\nconnecting issues";
                    }
                    // working
                    if(checkbox_about_contact_servers.isChecked()){
                        emailbody += "\nnot working properly";
                    }
                    // crashed
                    if(checkbox_about_contact_crashed.isChecked()){
                        emailbody += "\nkeeps crashing";
                    }
                    emailbody += "\n" + et_about_contact_other_problems.getText().toString();
                    sendMessage(emailbody);

            }
        });

        LinearLayout ll_about_contact_close = findViewById(R.id.ll_contact_back);
        ll_about_contact_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            }
        });




    }

private void sendMessage(String body) {

    Thread sender = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String useremail = user.getEmail();
                GMailSender sender = new GMailSender("contact@neeldeshmukh.com", "contactme@pass");
                sender.sendMail("ThunderVPN Issues/Suggestions",
                        body,
                        ""+useremail,
                        "deshmukhneel31@gmail.com");
                ContactActivity.this.runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        //dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Email Sent Successfully...", Toast.LENGTH_LONG).show();
                    }
                });
                //mDialog.dismiss();
            } catch (Exception e) {
                ContactActivity.this.runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        Toast.makeText(getApplicationContext(), "Unable to Send Report", Toast.LENGTH_LONG).show();
                    }
                });
                Log.e("User", "Error: " + e.getMessage());
            }
        }
    });
    sender.start();
}
    }


