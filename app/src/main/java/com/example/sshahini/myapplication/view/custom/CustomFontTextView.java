package com.example.sshahini.myapplication.view.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.sshahini.myapplication.MyApplication;

import java.lang.reflect.Type;

/**
 * Created by Saeed shahini on 9/23/2016.
 */
public class CustomFontTextView extends TextView {
    public CustomFontTextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            setupTextView();
        }
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            setupTextView();
        }
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            setupTextView();
        }
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!isInEditMode()) {
            setupTextView();
        }
    }

    private void setupTextView() {
        MyApplication myApplication = (MyApplication) getContext().getApplicationContext();
        setTypeface(myApplication.getIranianSansFont(getContext()));
    }
}
