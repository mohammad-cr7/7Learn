package com.a7learn.shahini.cfviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by Saeed shahini on 10/21/2016.
 */
public class CfSwitchCompat extends SwitchCompat {

    public CfSwitchCompat(Context context) {
        super(context);
        setupView(null);
    }

    public CfSwitchCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(attrs);
    }

    public CfSwitchCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(attrs);
    }

    private void setupView(AttributeSet attrs) {
        if (attrs!=null){
            TypedArray attributes=getContext().obtainStyledAttributes(attrs,R.styleable.CfViewsCustomAttributes);

            try {
                int font=attributes.getInteger(R.styleable.CfViewsCustomAttributes_font,CTypefaceProvider.IRANIAN_SANS_NORMAL);
                switch (font){
                    case CTypefaceProvider.IRANIAN_SANS_NORMAL:
                        setTypeface(CTypefaceProvider.getIranianSans(getContext()));
                        break;
                    case CTypefaceProvider.IRANIAN_SANS_BOLD:
                        setTypeface(CTypefaceProvider.getIranianSansBold(getContext()));
                        break;
                    case CTypefaceProvider.IRANIAN_SANS_LIGHT:
                        setTypeface(CTypefaceProvider.getIranianSansLight(getContext()));
                        break;
                }
            }finally {
                invalidate();
                requestLayout();
                attributes.recycle();
            }
        }
    }
}
