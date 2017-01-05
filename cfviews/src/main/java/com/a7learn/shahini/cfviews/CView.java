package com.a7learn.shahini.cfviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by Saeed shahini on 10/21/2016.
 */
public class CView extends LinearLayout {
    public CView(Context context) {
        super(context);
        setupViews();

    }

    public CView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupViews();

    }

    public CView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupViews();
    }

    public CView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupViews();
    }

    private void setupViews() {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        inflater.inflate(R.layout.library_root_layout,this,true);
    }
}
