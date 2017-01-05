package com.example.sshahini.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;

/**
 * Created by Saeed shahini on 9/23/2016.
 */
public class MyApplication extends Application {
    private static Typeface iranianSansFont;
    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent=new Intent("com.example.sshahini.myapplication.test");
        intent.putExtra("message_content","Hello");
        sendBroadcast(intent);
    }

    public static Typeface getIranianSansFont(Context context){
        if (iranianSansFont==null){
            iranianSansFont=Typeface.createFromAsset(context.getAssets(),"fonts/iranian_sans.ttf");
        }
        return iranianSansFont;
    }
}
