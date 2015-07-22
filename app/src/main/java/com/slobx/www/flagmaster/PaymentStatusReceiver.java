package com.slobx.www.flagmaster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import mp.MpUtils;

/**
 * Created by BCA on 12/23/2014.
 */
public class PaymentStatusReceiver extends BroadcastReceiver {
    private static String TAG = "PaymentStatusReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        Log.d(TAG, "- billing_status:  " + extras.getInt("billing_status"));
        Log.d(TAG, "- credit_amount:   " + extras.getString("credit_amount"));
        Log.d(TAG, "- credit_name:     " + extras.getString("credit_name"));
        Log.d(TAG, "- message_id:      " + extras.getString("message_id") );
        Log.d(TAG, "- payment_code:    " + extras.getString("payment_code"));
        Log.d(TAG, "- price_amount:    " + extras.getString("price_amount"));
        Log.d(TAG, "- price_currency:  " + extras.getString("price_currency"));
        Log.d(TAG, "- product_name:    " + extras.getString("product_name"));
        Log.d(TAG, "- service_id:      " + extras.getString("service_id"));
        Log.d(TAG, "- user_id:         " + extras.getString("user_id"));

        int billingStatus = extras.getInt("billing_status");
        if(billingStatus == MpUtils.MESSAGE_STATUS_BILLED && intent.getStringExtra("product_name").contains("Sta")) {
            int coins = Integer.parseInt(intent.getStringExtra("credit_amount"));
            Wallet.addCredits(context, coins);
        }

        if(billingStatus == MpUtils.MESSAGE_STATUS_BILLED && intent.getStringExtra("product_name").contains("Remove") ){
            SharedPreferences preferences = context.getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();

            editor.putBoolean("REMOVEADS", true);
            editor.commit();
        }

    }
}
