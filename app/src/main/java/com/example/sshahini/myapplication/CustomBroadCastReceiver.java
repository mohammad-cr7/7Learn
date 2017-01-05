package com.example.sshahini.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Saeed shahini on 10/27/2016.
 */
public class CustomBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String messageContent=intent.getStringExtra("message_content");
        Log.d("Broadcast", "onReceive() called with: "+messageContent);
    }
}
