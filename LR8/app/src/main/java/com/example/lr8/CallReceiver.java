package com.example.lr8;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("call", intent.getStringExtra(TelephonyManager.EXTRA_STATE));

        if (TelephonyManager.EXTRA_STATE.equals(intent.getStringExtra(TelephonyManager.EXTRA_STATE)))
        {
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.d("call", "Calling number" + incomingNumber);
        }
    }
}
