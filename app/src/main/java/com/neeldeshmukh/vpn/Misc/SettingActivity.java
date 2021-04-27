package com.neeldeshmukh.vpn.Misc;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;

//import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.neeldeshmukh.vpn.Auth.EditProfile;
import com.neeldeshmukh.vpn.Auth.Logout;

import com.neeldeshmukh.vpn.Chatbot.Chatbot;
import com.neeldeshmukh.vpn.Contact.ContactActivity;
import com.neeldeshmukh.vpn.Contact.FAQActivity;
import com.neeldeshmukh.vpn.Contact.PrivacyPolicy;
import com.neeldeshmukh.vpn.Core.CloudStorage;
import com.neeldeshmukh.vpn.Core.ViewUploads;
import com.neeldeshmukh.vpn.Payment.Donation;
import com.neeldeshmukh.vpn.R;

import java.util.Random;



import de.blinkt.openvpn.core.App;

public class SettingActivity extends Activity {

   // private static final String APP_ID = "5433826ac6d1ff810bbc1556107be74e";

    TextView tv_usage_title;
    ImageView iv_go_forward;
    TextView tv_usage_data_title, tv_usage_connection_details, tv_usage_socialmedia_title, tv_usage_appstore_title;
    TextView tv_usage_cu_title, tv_usage_cu_version;
    TextView tv_usage_share_title, tv_usage_share_description;
    //My TextViews
    TextView tv_usage_login_title, getTv_usage_login_description;
    TextView tv_usage_chatbot_title, getTv_usage_chatbot_description;
    TextView tv_usage_donation_title, getTv_usage_donation_description;
    TextView tv_usage_report_title, getTv_usage_report_description;
    TextView tv_usage_editprof_title, getTv_usage_editprof_description;
    TextView tv_usage_passgen_title, getTv_usage_passgen_title;
    TextView tv_internet_speed_title, getTv_internet_speed_description;
    //TextView getTv_usage_finger_auth;
    Button uploadbtn, downloadbtn;

    TextView tv_usage_app_name, tv_usage_app_copyright;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //AppBrain.addTestDevice("12345");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage);
       // Kommunicate.init(getApplicationContext(), APP_ID);
       // mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // 50
        iv_go_forward = findViewById(R.id.iv_go_forward);
        tv_usage_title = findViewById(R.id.tv_usage_title);
//        tv_usage_cu_title = findViewById(R.id.tv_usage_cu_title);
//        tv_usage_cu_version = findViewById(R.id.tv_usage_cu_version);
//        tv_usage_app_name = findViewById(R.id.tv_usage_app_name);
//        tv_usage_app_copyright = findViewById(R.id.tv_usage_app_copyright);

        tv_usage_share_title = findViewById(R.id.tv_usage_share_title);
        tv_usage_share_description = findViewById(R.id.tv_usage_share_decription);

        //My ids

        tv_usage_login_title= findViewById(R.id.tv_usage_ls_title);
        getTv_usage_login_description= findViewById(R.id.tv_usage_ls_decription);

        tv_usage_chatbot_title= findViewById(R.id.tv_usage_chatbot_title);
        getTv_usage_chatbot_description= findViewById(R.id.tv_usage_chatbot_decription);
        tv_usage_donation_title= findViewById(R.id.tv_usage_donation_title);
        getTv_usage_donation_description= findViewById(R.id.tv_usage_donation_decription);
        tv_usage_passgen_title =findViewById(R.id.tv_usage_passgen_title);
        getTv_usage_passgen_title = findViewById(R.id.tv_usage_passgen_decription);
        tv_internet_speed_title =findViewById(R.id.tv_usage_speed_meter_title);
        getTv_internet_speed_description = findViewById(R.id.tv_usage_speed_meter_decription);
        tv_usage_report_title = findViewById(R.id.tv_usage_report_title);
        getTv_usage_report_description = findViewById(R.id.tv_usage_report_decription);
       // getTv_usage_finger_auth = findViewById(R.id.tv_usage_fin);

        //       tv_usage_report_title=  findViewById(R.id.tv_usage_report_title);
        //     getTv_usage_report_description= findViewById(R.id.tv_usage_report_decription);
        uploadbtn = findViewById(R.id.uploadfilebtn);
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CloudStorage.class));
            }
        });
        downloadbtn = findViewById(R.id.downloadfilebtn);
        downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewUploads.class));
            }
        });

        //Edit profile


        Typeface RobotoLight = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf");
        Typeface RobotoMedium = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        Typeface RobotoRegular = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");
        Typeface RobotoBold = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");

        SharedPreferences ConnectionDetails = getSharedPreferences("app_details", 0);
        String cuVersion = ConnectionDetails.getString("cu_version", "NULL");

        LinearLayout ll_about_forward = findViewById (R.id.ll_about_forward);
        ll_about_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                finish();
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                } catch (Exception e) {
//                    Bundle params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("exception", "UA2" + e.toString());
//                    mFirebaseAnalytics.logEvent("app_param_error", params);
                }
            }
        });
        /*
        * WIll Generate 10 digit long password on click
        * */
        tv_usage_passgen_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SecurePasswordGenrator();
                }catch (Exception e){
                    Log.d("Passgen", e.getLocalizedMessage());
                }
            }
        });



        LinearLayout linearLayoutFAQ = findViewById (R.id.linearLayoutFAQ);
        TextView tv_usage_faq_title = findViewById(R.id.tv_usage_faq_title);
        TextView tv_usage_faq_description = findViewById(R.id.tv_usage_faq_description);
        tv_usage_faq_title.setTypeface(RobotoMedium);
        tv_usage_faq_description.setTypeface(RobotoRegular);
        linearLayoutFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    Bundle params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("click", "faq");
//                    mFirebaseAnalytics.logEvent("app_param_click", params);

                    Intent Servers = new Intent(SettingActivity.this, FAQActivity.class);
                    startActivity(Servers);
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                } catch (Exception e) {
//                    Bundle params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("exception", "UA13" + e.toString());
//                    mFirebaseAnalytics.logEvent("app_param_error", params);
                }
            }

        });

        LinearLayout linearLayoutEditprof = findViewById (R.id.linearLayouteditprof);
        tv_usage_editprof_title = findViewById(R.id.tv_usage_editprof);
        getTv_usage_editprof_description= findViewById(R.id.tv_usage_editprof_decription);
        tv_usage_editprof_title.setTypeface(RobotoMedium);
        getTv_usage_editprof_description.setTypeface(RobotoRegular);

        linearLayoutEditprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(getApplicationContext(), EditProfile.class));
                } catch (Exception e) {

                }
            }
        });

        LinearLayout linearLayoutPrivacyPolicy = findViewById (R.id.linearLayoutPrivacyPolicy);
        TextView tv_usage_privacy_title = findViewById(R.id.tv_usage_privacy_title);
        TextView tv_usage_privacy_decription = findViewById(R.id.tv_usage_privacy_decription);
        tv_usage_privacy_title.setTypeface(RobotoMedium);
        tv_usage_privacy_decription.setTypeface(RobotoRegular);
        linearLayoutPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    Bundle params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("click", "privacy");
//                    mFirebaseAnalytics.logEvent("app_param_click", params);
//
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    //Privacy policy page edit
//                    intent.setData(Uri.parse("https://neeldeshmukh.com"));
startActivity(new Intent(getApplicationContext(), PrivacyPolicy.class));
              } catch (Exception e) {
//                    Bundle params = new Bundle();

//                    params.putString("device_id", App.device_id);

//                    params.putString("exception", "UA17" + e.toString());
//                    mFirebaseAnalytics.logEvent("app_param_error", params);
                }
            }
        });

        LinearLayout ll_open_contact_dialog = findViewById (R.id.linearLayoutContact);
        TextView tv_usage_contact_title = findViewById(R.id.tv_usage_contact_title);
        TextView tv_usage_contact_description = findViewById(R.id.tv_usage_contact_description);
        //TextView tv_usage_connections_title = findViewById(R.id.tv_usage_connections_title);

        //tv_usage_connections_title.setTypeface(RobotoBold);
        tv_usage_contact_title.setTypeface(RobotoMedium);
        tv_usage_contact_description.setTypeface(RobotoRegular);
        tv_usage_contact_description.setTypeface(RobotoRegular);
        ll_open_contact_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
//                    Bundle params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("click", "contact");
//                    mFirebaseAnalytics.logEvent("app_param_click", params);

                    Intent Servers = new Intent(SettingActivity.this, ContactActivity.class);
                    startActivity(Servers);
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                } catch (Exception e) {
//                    Bundle params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("exception", "UA18" + e.toString());
//                    mFirebaseAnalytics.logEvent("app_param_error", params);
                }
            }
        });

        LinearLayout ll_usage_share = findViewById (R.id.linearLayoutShare);
        tv_usage_share_title.setTypeface(RobotoBold);
        tv_usage_share_description.setTypeface(RobotoRegular);
        ll_usage_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Bundle params = new Bundle();
//                params.putString("device_id", App.device_id);
//                params.putString("click", "share");
//                mFirebaseAnalytics.logEvent("app_param_click", params);

                try{
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "This is the best VPN app made! made with love by neel");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                } catch (Exception e) {
//                    params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("exception", "UA19" + e.toString());
//                    mFirebaseAnalytics.logEvent("app_param_error", params);
                }
            }
        });

//My intents
        LinearLayout ll_usage_login = findViewById (R.id.linearLayoutLS);
        tv_usage_login_title.setTypeface(RobotoBold);
        getTv_usage_login_description.setTypeface(RobotoRegular);
        ll_usage_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Bundle params = new Bundle();
//                params.putString("device_id", App.device_id);
//                params.putString("click", "share");
//                mFirebaseAnalytics.logEvent("app_param_click", params);

                try{
                    //Toast.makeText(getApplicationContext(), "Login/Signup", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Logout.class));
                    finish();
//                    Intent sendIntent = new Intent();
//                    sendIntent.setAction(Intent.ACTION_SEND);
//                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Wow! Change to dark mode, check VPN data usage, +10 countries, and it's only 5MB! Download the App NOW! From Google Play http://bit.ly/gotogoogleplay | Get the APK from UpToDown http://bit.ly/gotouptodown Aptoid http://bit.ly/gotoaptoide");
//                    sendIntent.setType("text/plain");
//                    startActivity(sendIntent);
                } catch (Exception e) {
//                    params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("exception", "UA19" + e.toString());
//                    mFirebaseAnalytics.logEvent("app_param_error", params);
                }
            }
        });
//LoginEND


        LinearLayout ll_usage_donation = findViewById (R.id.linearLayoutDonation);
        tv_usage_donation_title.setTypeface(RobotoBold);
        getTv_usage_donation_description.setTypeface(RobotoRegular);
        ll_usage_donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Bundle params = new Bundle();
//                params.putString("device_id", App.device_id);
//                params.putString("click", "share");
//                mFirebaseAnalytics.logEvent("app_param_click", params);

                try{
                    //Toast.makeText(getApplicationContext(), "Save my ass", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Donation.class));

//                    Intent sendIntent = new Intent();
//                    sendIntent.setAction(Intent.ACTION_SEND);
//                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Wow! Change to dark mode, check VPN data usage, +10 countries, and it's only 5MB! Download the App NOW! From Google Play http://bit.ly/gotogoogleplay | Get the APK from UpToDown http://bit.ly/gotouptodown Aptoid http://bit.ly/gotoaptoide");
//                    sendIntent.setType("text/plain");
//                    startActivity(sendIntent);
                } catch (Exception e) {
//                    params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("exception", "UA19" + e.toString());
//                    mFirebaseAnalytics.logEvent("app_param_error", params);
                }
            }
        });
//DonationEND
        LinearLayout ll_usage_chatbot = findViewById (R.id.linearLayoutChatbot);
        tv_usage_chatbot_title.setTypeface(RobotoBold);
        getTv_usage_chatbot_description.setTypeface(RobotoRegular);
        ll_usage_chatbot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                Bundle params = new Bundle();
//                params.putString("device_id", App.device_id);
//                params.putString("click", "share");
//                mFirebaseAnalytics.logEvent("app_param_click", params);

                try{
                    Toast.makeText(getApplicationContext(), "Loading Chatbot.....", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SettingActivity.this, Chatbot.class));
//
//                    new KmConversationBuilder(SettingActivity.this)
//                            .launchConversation(new KmCallback() {
//                                @Override
//                                public void onSuccess(Object message) {
//                                    Log.d("Conversation", "Success : " + message);
//                                }
//
//                                @Override
//                                public void onFailure(Object error) {
//                                    Log.d("Conversation", "Failure : " + error);
//                                }
//                            });
//                    Intent sendIntent = new Intent();
//                    sendIntent.setAction(Intent.ACTION_SEND);
//                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Wow! Change to dark mode, check VPN data usage, +10 countries, and it's only 5MB! Download the App NOW! From Google Play http://bit.ly/gotogoogleplay | Get the APK from UpToDown http://bit.ly/gotouptodown Aptoid http://bit.ly/gotoaptoide");
//                    sendIntent.setType("text/plain");
//                    startActivity(sendIntent);
                } catch (Exception e) {
                    e.getLocalizedMessage();
//                    params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("exception", "UA19" + e.toString());
//                    mFirebaseAnalytics.logEvent("app_param_error", params);
                }
            }
        });
//ChatbotEND
//        LinearLayout ll_usage_report = findViewById (R.id.linearLayoutUsagereport);
//        tv_usage_report_title.setTypeface(RobotoBold);
//        getTv_usage_report_description.setTypeface(RobotoRegular);
//        ll_usage_report.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////
////                Bundle params = new Bundle();
////                params.putString("device_id", App.device_id);
////                params.putString("click", "share");
////                mFirebaseAnalytics.logEvent("app_param_click", params);
//
//                try{
//                    Toast.makeText(getApplicationContext(), "Save my ass", Toast.LENGTH_SHORT).show();
//                    //startActivity(new Intent(getApplicationContext(),EditProfile.class));
//
////                    Intent sendIntent = new Intent();
////                    sendIntent.setAction(Intent.ACTION_SEND);
////                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Wow! Change to dark mode, check VPN data usage, +10 countries, and it's only 5MB! Download the App NOW! From Google Play http://bit.ly/gotogoogleplay | Get the APK from UpToDown http://bit.ly/gotouptodown Aptoid http://bit.ly/gotoaptoide");
////                    sendIntent.setType("text/plain");
////                    startActivity(sendIntent);
//                } catch (Exception e) {
////                    params = new Bundle();
////                    params.putString("device_id", App.device_id);
////                    params.putString("exception", "UA19" + e.toString());
////                    mFirebaseAnalytics.logEvent("app_param_error", params);
//                }
//            }
//        });
//UsageReportEND


//        LinearLayout ll_usage_editprofile = findViewById (R.id.linearLayouteditprofile);
//        tv_usage_editprofile_title.setTypeface(RobotoBold);
//        getTv_usage_editprofile_description.setTypeface(RobotoRegular);
//        ll_usage_report.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////
////                Bundle params = new Bundle();
////                params.putString("device_id", App.device_id);
////                params.putString("click", "share");
////                mFirebaseAnalytics.logEvent("app_param_click", params);
//
//                try{
//                    Toast.makeText(getApplicationContext(), "Save my ass", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(),EditProfile.class));
//
////                    Intent sendIntent = new Intent();
////                    sendIntent.setAction(Intent.ACTION_SEND);
////                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Wow! Change to dark mode, check VPN data usage, +10 countries, and it's only 5MB! Download the App NOW! From Google Play http://bit.ly/gotogoogleplay | Get the APK from UpToDown http://bit.ly/gotouptodown Aptoid http://bit.ly/gotoaptoide");
////                    sendIntent.setType("text/plain");
////                    startActivity(sendIntent);
//                } catch (Exception e) {
////                    params = new Bundle();
////                    params.putString("device_id", App.device_id);
////                    params.putString("exception", "UA19" + e.toString());
////                    mFirebaseAnalytics.logEvent("app_param_error", params);
//                }
//            }
//        });
////EditProfileEND

//Internet SpeedMeter
        LinearLayout ll_internet_speed = findViewById (R.id.linearLayoutSpeedMeter);
        tv_internet_speed_title.setTypeface(RobotoBold);
        getTv_internet_speed_description.setTypeface(RobotoRegular);
        ll_internet_speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                Bundle params = new Bundle();
//                params.putString("device_id", App.device_id);
//                params.putString("click", "share");
//                mFirebaseAnalytics.logEvent("app_param_click", params);

                try{
                    Toast.makeText(getApplicationContext(), "Speed Meter", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),SpeedTest.class));

//                    Intent sendIntent = new Intent();
//                    sendIntent.setAction(Intent.ACTION_SEND);
//                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Wow! Change to dark mode, check VPN data usage, +10 countries, and it's only 5MB! Download the App NOW! From Google Play http://bit.ly/gotogoogleplay | Get the APK from UpToDown http://bit.ly/gotouptodown Aptoid http://bit.ly/gotoaptoide");
//                    sendIntent.setType("text/plain");
//                    startActivity(sendIntent);
                } catch (Exception e) {
//                    params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("exception", "UA19" + e.toString());
//                    mFirebaseAnalytics.logEvent("app_param_error", params);
                }
            }
        });

//Usage Intent
        LinearLayout linearlaoutUsage = findViewById (R.id.linearLayoutUsagereport);
        TextView tv_usage_report_title = findViewById(R.id.tv_usage_report_title);
        TextView tv_usage_report_description = findViewById(R.id.tv_usage_report_decription);
        tv_usage_report_title.setTypeface(RobotoMedium);
        tv_usage_report_description.setTypeface(RobotoRegular);
        linearlaoutUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Toast.makeText(getApplicationContext(), "Usage report", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),InternetUsage.class));
                }catch(Exception e){

                }
            }
        });

        //FingerPrintAuthentication switch
        TextView tv_usage_finger_auth_title = findViewById(R.id.tv_usage_finger_auth_title);

        Switch switch_usage_finger_auth = findViewById(R.id.switch_usage_finger_auth);
        switch_usage_finger_auth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    SharedPreferences FingerAuthPref = getSharedPreferences("finger_authpref", 0);
                    SharedPreferences.Editor Editor1 = FingerAuthPref.edit();
                    if (isChecked) {
                        try {
                            Editor1.putString("finger_auth", "true");
                        } catch (Exception e) {
                            Editor1.putString("finger_auth", "false");
                        }
                    } else {
                        Editor1.putString("finger_auth", "false");
                    }
                    Editor1.apply();
                } catch (Exception e) {
//                    Bundle params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("exception", "UA20" + e.toString());
//                    mFirebaseAnalytics.logEvent("app_param_error", params);
                }
            }
        });
        tv_usage_finger_auth_title.setTypeface(RobotoMedium);

//My Intents END

        //View viewUsageDark_1 = findViewById(R.id.viewUsageDark_1);
        //View viewUsageDark_2 = findViewById(R.id.viewUsageDark_2);
        View viewUsageDark_3 = findViewById(R.id.viewUsageDark_3);
        View viewUsageDark_4 = findViewById(R.id.viewUsageDark_4);
        View viewUsageDark_5 = findViewById(R.id.viewUsageDark_5);
        View viewUsageDark_6 = findViewById(R.id.viewUsageDark_6);
        View viewUsageDark_7 = findViewById(R.id.viewUsageDark_7);
        View viewUsageDark_8 = findViewById(R.id.viewUsageDark_8);
        View viewUsageDark_9 = findViewById(R.id.viewUsageDark_9);
        View viewUsageDark_10 = findViewById(R.id.viewUsageDark_10);
        View viewUsageDark_11 = findViewById(R.id.viewUsageDark_11);
        View viewUsageDark_12 = findViewById(R.id.viewUsageDark_12);
        View viewUsageDark_13 = findViewById(R.id.viewUsageDark_13);
        View viewUsageDark_14 = findViewById(R.id.viewUsageDark_14);
        View viewUsageDark_15 = findViewById(R.id.viewUsageDark_15);
        View viewUsageDark_16 = findViewById(R.id.viewUsageDark_16);

       // View viewUsageLight_1 = findViewById(R.id.viewUsageLight_1);
        //View viewUsageLight_2 = findViewById(R.id.viewUsageLight_2);
        View viewUsageLight_3 = findViewById(R.id.viewUsageLight_3);
        View viewUsageLight_4 = findViewById(R.id.viewUsageLight_4);
        View viewUsageLight_5 = findViewById(R.id.viewUsageLight_5);
        View viewUsageLight_6 = findViewById(R.id.viewUsageLight_6);
        View viewUsageLight_7 = findViewById(R.id.viewUsageLight_7);
        View viewUsageLight_8 = findViewById(R.id.viewUsageLight_8);
        View viewUsageLight_9 = findViewById(R.id.viewUsageLight_9);
        View viewUsageLight_10 = findViewById(R.id.viewUsageLight_10);
        View viewUsageLight_11 = findViewById(R.id.viewUsageLight_11);
        View viewUsageLight_12 = findViewById(R.id.viewUsageLight_12);
        View viewUsageLight_13 = findViewById(R.id.viewUsageLight_13);
        View viewUsageLight_14 = findViewById(R.id.viewUsageLight_14);
        View viewUsageLight_15 = findViewById(R.id.viewUsageLight_15);
        View viewUsageLight_16 = findViewById(R.id.viewUsageLight_16);

        LinearLayout linearLayoutUsage = findViewById(R.id.linearLayoutUsage);
        TextView tv_usage_dark_mode_title = findViewById(R.id.tv_usage_dark_mode_title);

        Switch switch_usage_dark_mode = findViewById(R.id.switch_usage_dark_mode);
        switch_usage_dark_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    SharedPreferences SharedAppDetails = getSharedPreferences("settings_data", 0);
                    SharedPreferences.Editor Editor = SharedAppDetails.edit();
                    if (isChecked) {
                        try {
                            Editor.putString("dark_mode", "true");
                        } catch (Exception e) {
                            Editor.putString("dark_mode", "false");
                        }
                    } else {
                        Editor.putString("dark_mode", "false");
                    }
                    Editor.apply();
                } catch (Exception e) {
//                    Bundle params = new Bundle();
//                    params.putString("device_id", App.device_id);
//                    params.putString("exception", "UA20" + e.toString());
//                    mFirebaseAnalytics.logEvent("app_param_error", params);
                }
            }


        });

        tv_usage_dark_mode_title.setTypeface(RobotoMedium);
        switch_usage_dark_mode.setTypeface(RobotoRegular);

        SharedPreferences SettingsDetails = getSharedPreferences("settings_data", 0);
        String DarkMode = SettingsDetails.getString("dark_mode", "false");
        if (DarkMode.equals("true")) {
            linearLayoutUsage.setBackgroundColor(getResources().getColor(R.color.colorDarkBackground));
            tv_usage_title.setTextColor(getResources().getColor(R.color.colorDarkText));

            //LoginSignup, Donation and Chatbot Darkmode and Light mode
            tv_usage_login_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_chatbot_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_donation_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            getTv_usage_chatbot_description.setTextColor(getResources().getColor(R.color.colorDarkText));
            getTv_usage_donation_description.setTextColor(getResources().getColor(R.color.colorDarkText));
            getTv_usage_login_description.setTextColor(getResources().getColor(R.color.colorDarkText));
            //tv_usage_data_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            //tv_usage_time_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            //tv_usage_connection_details.setTextColor(getResources().getColor(R.color.colorDarkText));
            //tv_usage_socialmedia_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            //tv_usage_appstore_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_faq_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_faq_description.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_dark_mode_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            switch_usage_dark_mode.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_editprof_title.setTextColor(getResources().getColor(R.color.colorDarkText));
           getTv_usage_editprof_description.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_privacy_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_privacy_decription.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_contact_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_contact_description.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_share_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_share_description.setTextColor(getResources().getColor(R.color.colorDarkText));
            //new added on 29sept
            tv_usage_finger_auth_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            switch_usage_finger_auth.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_internet_speed_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            getTv_internet_speed_description.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_passgen_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            getTv_usage_passgen_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            tv_usage_report_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            getTv_usage_report_description.setTextColor(getResources().getColor(R.color.colorDarkText));
            //tv_usage_cu_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            //tv_usage_cu_version.setTextColor(getResources().getColor(R.color.colorDarkText));
            //tv_usage_app_name.setTextColor(getResources().getColor(R.color.colorDarkText));
            //tv_usage_app_copyright.setTextColor(getResources().getColor(R.color.colorDarkText));
            //tv_usage_connections_title.setTextColor(getResources().getColor(R.color.colorDarkText));
            iv_go_forward.setImageResource(R.drawable.ic_go_forward_white);
//
//            try {
//                if ("huawei".equalsIgnoreCase(android.os.Build.MANUFACTURER)) {
//                    viewUsageLight_6.setVisibility(View.GONE);
//                    viewUsageDark_6.setVisibility(View.VISIBLE);
//                } else {
//                    viewUsageLight_6.setVisibility(View.GONE);
//                    viewUsageDark_6.setVisibility(View.GONE);
//                }
//            } catch (Exception e) {
//                Bundle params = new Bundle();
//                params.putString("device_id", App.device_id);
//                params.putString("exception", "UA21" + e.toString());
//                mFirebaseAnalytics.logEvent("app_param_error", params);
//            }

            //viewUsageLight_1.setVisibility(View.GONE);
            //viewUsageLight_2.setVisibility(View.GONE);
            viewUsageLight_3.setVisibility(View.GONE);
            viewUsageLight_4.setVisibility(View.GONE);
            viewUsageLight_5.setVisibility(View.GONE);
            viewUsageLight_7.setVisibility(View.GONE);
            viewUsageLight_8.setVisibility(View.GONE);
            viewUsageLight_9.setVisibility(View.GONE);
            viewUsageLight_10.setVisibility(View.GONE);
            viewUsageLight_11.setVisibility(View.GONE);
            viewUsageLight_12.setVisibility(View.GONE);
            viewUsageLight_13.setVisibility(View.GONE);
            viewUsageLight_14.setVisibility(View.GONE);
            viewUsageLight_15.setVisibility(View.GONE);
            viewUsageLight_16.setVisibility(View.GONE);

            //viewUsageDark_1.setVisibility(View.VISIBLE);
            //viewUsageDark_2.setVisibility(View.VISIBLE);
            viewUsageDark_3.setVisibility(View.VISIBLE);
            viewUsageDark_4.setVisibility(View.VISIBLE);
            viewUsageDark_5.setVisibility(View.VISIBLE);
            viewUsageDark_7.setVisibility(View.VISIBLE);
            viewUsageDark_8.setVisibility(View.VISIBLE);
            viewUsageDark_9.setVisibility(View.VISIBLE);
            viewUsageDark_10.setVisibility(View.VISIBLE);
            viewUsageDark_11.setVisibility(View.VISIBLE);
            viewUsageDark_12.setVisibility(View.VISIBLE);
            viewUsageDark_13.setVisibility(View.VISIBLE);
            viewUsageDark_14.setVisibility(View.VISIBLE);
            viewUsageDark_15.setVisibility(View.VISIBLE);
            viewUsageDark_16.setVisibility(View.VISIBLE);


        }
        else {
            linearLayoutUsage.setBackgroundColor(getResources().getColor(R.color.colorLightBackground));
            tv_usage_title.setTextColor(getResources().getColor(R.color.colorLightText));
            //LoginSignup, Donation and Chatbot Darkmode and Light mode
            tv_usage_login_title.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_chatbot_title.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_donation_title.setTextColor(getResources().getColor(R.color.colorLightText));
            getTv_usage_chatbot_description.setTextColor(getResources().getColor(R.color.colorLightText));
            getTv_usage_donation_description.setTextColor(getResources().getColor(R.color.colorLightText));
            getTv_usage_login_description.setTextColor(getResources().getColor(R.color.colorLightText));

            //tv_usage_data_title.setTextColor(getResources().getColor(R.color.colorLightText));
            //tv_usage_time_title.setTextColor(getResources().getColor(R.color.colorLightText));
            //tv_usage_connection_details.setTextColor(getResources().getColor(R.color.colorLightText));
            //tv_usage_socialmedia_title.setTextColor(getResources().getColor(R.color.colorLightText));
            //tv_usage_appstore_title.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_faq_title.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_faq_description.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_dark_mode_title.setTextColor(getResources().getColor(R.color.colorLightText));
            switch_usage_dark_mode.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_editprof_title.setTextColor(getResources().getColor(R.color.colorLightText));
            getTv_usage_editprof_description.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_privacy_title.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_privacy_decription.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_contact_title.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_contact_description.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_share_title.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_share_description.setTextColor(getResources().getColor(R.color.colorLightText));
            //new added on 29sept
            tv_usage_finger_auth_title.setTextColor(getResources().getColor(R.color.colorLightText));
            switch_usage_finger_auth.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_internet_speed_title.setTextColor(getResources().getColor(R.color.colorLightText));
            getTv_internet_speed_description.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_passgen_title.setTextColor(getResources().getColor(R.color.colorLightText));
            getTv_usage_passgen_title.setTextColor(getResources().getColor(R.color.colorLightText));
            tv_usage_report_title.setTextColor(getResources().getColor(R.color.colorLightText));
            getTv_usage_report_description.setTextColor(getResources().getColor(R.color.colorLightText));
            //tv_usage_cu_title.setTextColor(getResources().getColor(R.color.colorLightText));
            //tv_usage_cu_version.setTextColor(getResources().getColor(R.color.colorLightText));
            //tv_usage_connections_title.setTextColor(getResources().getColor(R.color.colorLightText));
            //tv_usage_app_name.setTextColor(getResources().getColor(R.color.colorLightText));
            //tv_usage_app_copyright.setTextColor(getResources().getColor(R.color.colorLightText));
            iv_go_forward.setImageResource(R.drawable.ic_go_forward);

            try {
                if ("huawei".equalsIgnoreCase(android.os.Build.MANUFACTURER)) {
                    viewUsageDark_6.setVisibility(View.GONE);
                    viewUsageLight_6.setVisibility(View.VISIBLE);
                } else {
                    viewUsageDark_6.setVisibility(View.GONE);
                    viewUsageLight_6.setVisibility(View.GONE);
                }
            } catch (Exception e) {
//                Bundle params = new Bundle();
//                params.putString("device_id", App.device_id);
//                params.putString("exception", "UA22" + e.toString());
//                mFirebaseAnalytics.logEvent("app_param_error", params);
            }

            //viewUsageDark_1.setVisibility(View.GONE);
            //viewUsageDark_2.setVisibility(View.GONE);
            viewUsageDark_3.setVisibility(View.GONE);
            viewUsageDark_4.setVisibility(View.GONE);
            viewUsageDark_5.setVisibility(View.GONE);
            viewUsageDark_6.setVisibility(View.GONE);
            viewUsageDark_7.setVisibility(View.GONE);
            viewUsageDark_8.setVisibility(View.GONE);
            viewUsageDark_9.setVisibility(View.GONE);
            viewUsageDark_10.setVisibility(View.GONE);
            viewUsageDark_11.setVisibility(View.GONE);
            viewUsageDark_12.setVisibility(View.GONE);
            viewUsageDark_13.setVisibility(View.GONE);
            viewUsageDark_14.setVisibility(View.GONE);
            viewUsageDark_15.setVisibility(View.GONE);
            viewUsageDark_16.setVisibility(View.GONE);

            //viewUsageLight_1.setVisibility(View.VISIBLE);
            //viewUsageLight_2.setVisibility(View.VISIBLE);
            viewUsageLight_3.setVisibility(View.VISIBLE);
            viewUsageLight_4.setVisibility(View.VISIBLE);
            viewUsageLight_5.setVisibility(View.VISIBLE);
            viewUsageLight_6.setVisibility(View.VISIBLE);
            viewUsageLight_7.setVisibility(View.VISIBLE);
            viewUsageLight_8.setVisibility(View.VISIBLE);
            viewUsageLight_9.setVisibility(View.VISIBLE);
            viewUsageLight_10.setVisibility(View.VISIBLE);
            viewUsageLight_11.setVisibility(View.VISIBLE);
            viewUsageLight_12.setVisibility(View.VISIBLE);
            viewUsageLight_13.setVisibility(View.VISIBLE);
            viewUsageLight_14.setVisibility(View.VISIBLE);
            viewUsageLight_15.setVisibility(View.VISIBLE);
            viewUsageLight_16.setVisibility(View.VISIBLE);
       }

        if(DarkMode.equals("true")){
            switch_usage_dark_mode.setChecked(true);
        } else {
            switch_usage_dark_mode.setChecked(false);
        }
        SharedPreferences AuthDetails= getSharedPreferences("finger_authpref", 0);
        String fingerauth = AuthDetails.getString("finger_auth", "false");
        if(fingerauth.equals("true")){
            switch_usage_finger_auth.setChecked(true);
        } else {
            switch_usage_finger_auth.setChecked(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

/*
* This Function will generate a secure password and copy it to the clipboard
* */
    protected void SecurePasswordGenrator(){
        int length = 10;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[{]}<.>/?";
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(length);
        for(int i=0;i<length;++i) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        String password = sb.toString();
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("password",password);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(this,"Secured Password Copied", Toast.LENGTH_SHORT).show();
    }

}
