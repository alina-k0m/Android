package com.example.lr8;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("SMS", "SMS test");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if(pdus != null){
                for (Object pdu:pdus){
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])pdu);
                    String sender = smsMessage.getDisplayOriginatingAddress();
                    String message = smsMessage.getDisplayMessageBody();
                    Log.d("sms", "sms from" + sender + ": " + message);
                }
            }
        }
    }
}
