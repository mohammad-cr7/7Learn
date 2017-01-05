package com.example.sshahini.myapplication.view;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.sshahini.myapplication.MyApplication;

/**
 * Created by Saeed shahini on 11/25/2016.
 */
public class Util {
    public static void setTypefaceToolbar(Context context,Toolbar toolbar){
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            if (toolbar.getChildAt(i) instanceof TextView){
                ((TextView)toolbar.getChildAt(i)).setTypeface(MyApplication.getIranianSansFont(context));
            }
        }
    }
}
