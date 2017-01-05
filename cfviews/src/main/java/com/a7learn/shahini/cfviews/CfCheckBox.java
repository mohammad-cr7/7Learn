package com.a7learn.shahini.cfviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Saeed shahini on 10/21/2016.
 */
public class CfCheckBox extends CheckBox {

    public CfCheckBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupView(attrs);
    }

    public CfCheckBox(Context context) {
        super(context);
        setupView(null);
    }

    public CfCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(attrs);
    }

    public CfCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
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
