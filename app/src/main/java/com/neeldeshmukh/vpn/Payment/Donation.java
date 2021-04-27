package com.neeldeshmukh.vpn.Payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.neeldeshmukh.vpn.R;

public class Donation extends AppCompatActivity {

    private RadioGroup radio_donation_group;
    private RadioButton radio_amount_selected;
    private Button btnDonate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        //Finding buttons
        radio_donation_group = findViewById(R.id.donation_group);
        btnDonate = findViewById(R.id.btn_donate);

        //onclick listener
        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_amount = radio_donation_group.getCheckedRadioButtonId();

                radio_amount_selected = findViewById(selected_amount);

                Intent intent = new Intent(Donation.this, PaymentActivity.class);
                intent.putExtra("amount", radio_amount_selected.getText().toString());
                startActivity(intent);

            }
        });

    }
}
