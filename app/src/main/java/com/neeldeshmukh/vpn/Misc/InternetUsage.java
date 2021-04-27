package com.neeldeshmukh.vpn.Misc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.net.TrafficStats;
import android.os.Bundle;
import android.widget.TextView;

import com.neeldeshmukh.vpn.R;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class InternetUsage extends AppCompatActivity {
    //TextView tv_usage_data_title, tv_usage_connection_details;
    TextView tv_usage_report_title, getTv_usage_report_description;
    TrafficStats tsx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_usage);
               tv_usage_report_title=  findViewById(R.id.tv_usage_report_title);
             getTv_usage_report_description= findViewById(R.id.tv_usage_report_decription);

        Typeface RobotoLight = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf");
        Typeface RobotoMedium = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        Typeface RobotoRegular = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");
        Typeface RobotoBold = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");

        //  TODAY
        TextView tv_usage_data_today_title = findViewById(R.id.tv_usage_data_today_title);
        TextView tv_usage_data_today_size = findViewById(R.id.tv_usage_data_today_size);
        TextView tv_usage_data_today_used = findViewById(R.id.tv_usage_data_today_used);
        tv_usage_data_today_title.setTypeface(RobotoRegular);
        tv_usage_data_today_size.setTypeface(RobotoLight);
        tv_usage_data_today_used.setTypeface(RobotoMedium);

        //  TOMMOROW
        TextView tv_usage_data_yesterday_title = findViewById(R.id.tv_usage_data_yesterday_title);
        TextView tv_usage_data_yesterday_size = findViewById(R.id.tv_usage_data_yesterday_size);
        TextView tv_usage_data_yesterday_used = findViewById(R.id.tv_usage_data_yesterday_used);
        tv_usage_data_yesterday_title.setTypeface(RobotoRegular);
        tv_usage_data_yesterday_size.setTypeface(RobotoLight);
        tv_usage_data_yesterday_used.setTypeface(RobotoMedium);

        //  3DAYS BEFORE
//       TextView tv_usage_data_daythree_title = findViewById(R.id.tv_usage_data_daythree_title);
//        TextView tv_usage_data_daythree_size = findViewById(R.id.tv_usage_data_daythree_size);
//        TextView tv_usage_data_daythree_used = findViewById(R.id.tv_usage_data_daythree_used);
//        tv_usage_data_daythree_title.setTypeface(RobotoRegular);
//        tv_usage_data_daythree_size.setTypeface(RobotoLight);
//        tv_usage_data_daythree_used.setTypeface(RobotoMedium);
//        tv_usage_data_daythree_title.setText("Three Days");

        //  THIS WEEK
        TextView tv_usage_data_thisweek_title = findViewById(R.id.tv_usage_data_thisweek_title);
        TextView tv_usage_data_thisweek_size = findViewById(R.id.tv_usage_data_thisweek_size);
        TextView tv_usage_data_thisweek_used = findViewById(R.id.tv_usage_data_thisweek_used);
        tv_usage_data_thisweek_title.setTypeface(RobotoRegular);
        tv_usage_data_thisweek_size.setTypeface(RobotoLight);
        tv_usage_data_thisweek_used.setTypeface(RobotoMedium);

        //  THIS MONTH
//        TextView tv_usage_data_thismonth_title = findViewById(R.id.tv_usage_data_thismonth_title);
//        TextView tv_usage_data_thismonth_size = findViewById(R.id.tv_usage_data_thismonth_size);
//        TextView tv_usage_data_thismonth_used = findViewById(R.id.tv_usage_data_thismonth_used);
//        tv_usage_data_thismonth_title.setTypeface(RobotoRegular);
//        tv_usage_data_thismonth_size.setTypeface(RobotoLight);
//        tv_usage_data_thismonth_used.setTypeface(RobotoMedium);
        int uid = android.os.Process.myUid();

        long mobile_ = TrafficStats.getMobileRxBytes();
        String str_mobile = humanReadableByteCountBin(mobile_);
        long total_receive_ = TrafficStats.getUidRxBytes(uid);
        String str_total_receive = humanReadableByteCountBin(total_receive_);
        long total_transmit_ = TrafficStats.getUidTxBytes(uid);
        String str_total_transmit = humanReadableByteCountBin(total_transmit_);
        long wifi_ = total_receive_ - mobile_ ;
        String str_wifi = humanReadableByteCountBin(wifi_);
        tv_usage_data_today_size.setText(str_mobile);
        tv_usage_data_yesterday_size.setText(str_wifi);
        tv_usage_data_thisweek_size.setText(str_total_receive);


    }
    public static String humanReadableByteCountBin(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %ciB", value / 1024.0, ci.current());
    }
}
