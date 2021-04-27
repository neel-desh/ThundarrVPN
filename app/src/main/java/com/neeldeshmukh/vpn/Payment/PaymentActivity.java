package com.neeldeshmukh.vpn.Payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import com.neeldeshmukh.vpn.Utils.JsonParse;
import com.neeldeshmukh.vpn.R;
import com.neeldeshmukh.vpn.Core.MainActivity;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PaymentActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback {

    String customerId = "";
    String orderId = "";
    //Merchant ID
    String mid = "WBdoFL82478475715701";
    String amount = "";
    LottieAnimationView lv;
    TextView thanks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        lv = findViewById(R.id.thanksanim);
        thanks = findViewById(R.id.thanks_tv);

        customerId = generateString();
        orderId = UUID.randomUUID().toString().replaceAll("-", "");
        amount = getIntent().getStringExtra("amount");

        getCheckSum cs = new getCheckSum();
        cs.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public class getCheckSum extends AsyncTask<ArrayList<String>, Void, String> {

        private ProgressDialog dialog = new ProgressDialog(PaymentActivity.this);

        String url ="https://vpn.neeldeshmukh.com/checksumgen/generateChecksum.php";

        String varifyurl = "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";

        String CHECKSUMHASH ="";

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }

        protected String doInBackground(ArrayList<String>... alldata) {
            JsonParse jsonParser = new JsonParse(PaymentActivity.this);
            String param=
                    "MID="+mid+
                            "&ORDER_ID=" + orderId+
                            "&CUST_ID="+customerId+
                            "&CHANNEL_ID=WAP&TXN_AMOUNT="+amount+"&WEBSITE=DEFAULT"+
                            "&CALLBACK_URL="+ varifyurl+"&INDUSTRY_TYPE_ID=Retail";

            Log.e("PostData",param);

            JSONObject jsonObject = jsonParser.makeHttpRequest(url,"POST",param);
            Log.e("CheckSum result >>",jsonObject.toString());
            if(jsonObject != null){
                Log.e("CheckSum result >>",jsonObject.toString());
                try {

                    CHECKSUMHASH=jsonObject.has("CHECKSUMHASH")?jsonObject.getString("CHECKSUMHASH"):"";
                    Log.e("CheckSum result >>",CHECKSUMHASH);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return CHECKSUMHASH;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e(" setup acc ","  signup result  " + result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            PaytmPGService Service = PaytmPGService.getStagingService();
            //PaytmPGService Service = PaytmPGService.getProductionService();
            HashMap<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("MID", mid);
            paramMap.put("ORDER_ID", orderId);
            paramMap.put("CUST_ID", customerId);
            paramMap.put("CHANNEL_ID", "WAP");
            paramMap.put("TXN_AMOUNT", amount);
            paramMap.put("WEBSITE", "DEFAULT");
            paramMap.put("CALLBACK_URL" ,varifyurl);
            paramMap.put("CHECKSUMHASH" ,CHECKSUMHASH);
            paramMap.put("INDUSTRY_TYPE_ID", "Retail");
            PaytmOrder Order = new PaytmOrder(paramMap);
            Log.e("checksum ", "param "+ paramMap.toString());
            Service.initialize(Order,null);
            Service.startPaymentTransaction(PaymentActivity.this, true,
                    true,
                    PaymentActivity.this  );
        }
    }

    @Override
    public void onTransactionResponse(Bundle bundle) {

        Log.e("SuccessT", " respon true " + bundle.toString());

        String tranStatus = bundle.getString("STATUS");
        String transamount = bundle.getString("TXNAMOUNT");
        Toast.makeText(this, ""+tranStatus +": "+transamount, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
//
//        SharedPreferences paymentdata = getSharedPreferences("paymentdata", 0);
//        SharedPreferences.Editor Editor1 = paymentdata.edit();
//
//
//        String amount, txtstatus;
//        txtstatus = bundle.getString("STATUS");
//        amount = bundle.getString("TXNAMOUNT");
//        Editor1.putString("amt", amount);
//        Editor1.putString("txstatus", txtstatus);
//
//        Editor1.apply();

       /*
         Bundle[{STATUS=TXN_SUCCESS, CHECKSUMHASH=uFwM18wuGA85AZGpkC8X5tzT/NSvpKFL13Sv2lvGW6PZBri2PR4VPrUvV+ISbLJwWEeO2aLoqi1bYN4zvGjptJxSgHMmFkaepl8dey5OM8c=,
         ORDERID=15820b0e00de4d76b8597a6f88f836d4,
         TXNAMOUNT=1.00,
         TXNDATE=2019-07-11 20:32:33.0,
         MID=aVZOfL98465894658946,
         TXNID=20190711111212800110168227578295333,
         RESPCODE=01,
         PAYMENTMODE=UPI,
         BANKTXNID=919244872866,
         CURRENCY=INR,
         GATEWAYNAME=PPBLC,
         RESPMSG=Txn Success}]
         */

    }

    @Override
    public void networkNotAvailable() {
        Log.e("Trans ", "Network Not Available" );
        Toast.makeText(this, "Network Not Available", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clientAuthenticationFailed(String s) {
        Log.e("Trans ", " Authentication Failed  "+ s );
        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void someUIErrorOccurred(String s) {
        Log.e("Trans ", " ui fail respon  "+ s );
        Toast.makeText(this, "UI Error Occurred", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        Log.e("Trans ", " error loading pagerespon true "+ s + "  s1 " + s1);
        Toast.makeText(this, "onErrorLoadingWebPage", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressedCancelTransaction() {
        Toast.makeText(this, "Transaction Cancel", Toast.LENGTH_LONG).show();
        Log.e("Trans ", " cancel call back respon  " );
    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        Toast.makeText(this, "Transaction Cancel", Toast.LENGTH_LONG).show();
        Log.e("Trans ", "  transaction cancel " );

    }

    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    @Override
    protected void onResume() {
        super.onResume();
//
//        SharedPreferences pddetails= getSharedPreferences("paymentdata", 0);
//        String amount = pddetails.getString("amt", "00.00");
//        String txttstatus = pddetails.getString("txstatus","NA");
//
//        if(txttstatus != null){
//            Toast.makeText(this, ""+txttstatus, Toast.LENGTH_SHORT).show();
//            if(txttstatus == "TX_SUCCESS"){
//                lv.setVisibility(View.VISIBLE);
//                thanks.setVisibility(View.VISIBLE);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent i = new Intent(PaymentActivity.this, MainActivity.class);
//                        startActivity(i);
//                        finish();
//                    }
//                }, 3000);
//            } else if(txttstatus == "TX_FAILED"){
//                thanks.setText("Donation Failed...");
//                lv.setVisibility(View.VISIBLE);
//                thanks.setVisibility(View.VISIBLE);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent i = new Intent(PaymentActivity.this, MainActivity.class);
//                        startActivity(i);
//                        finish();
//                    }
//                }, 3000);
//            }
//        }

    }
}

