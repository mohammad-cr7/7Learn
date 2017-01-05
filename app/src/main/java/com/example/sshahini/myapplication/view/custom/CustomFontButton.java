package com.example.sshahini.myapplication.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.sshahini.myapplication.MyApplication;

/**
 * Created by Saeed shahini on 9/23/2016.
 */
public class CustomFontButton extends Button {
    public CustomFontButton(Context context) {
        super(context);
        if (!isInEditMode()){
            setupButton();
        }
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()){
            setupButton();
        }
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()){
            setupButton();
        }
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!isInEditMode()){
            setupButton();
        }
    }
    private void setupButton() {
        MyApplication myApplication = (MyApplication) getContext().getApplicationContext();
        setTypeface(myApplication.getIranianSansFont(getContext()));
    }
}
