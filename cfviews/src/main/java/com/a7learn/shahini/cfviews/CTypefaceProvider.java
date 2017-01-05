package com.a7learn.shahini.cfviews;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Saeed shahini on 10/21/2016.
 */
public class CTypefaceProvider {
    public static final int IRANIAN_SANS_NORMAL=0;
    public static final int IRANIAN_SANS_LIGHT=1;
    public static final int IRANIAN_SANS_BOLD=2;

    private static Typeface iranianSans;
    private static Typeface iranianSansLight;
    private static Typeface iranianSansBold;

    public static Typeface getIranianSans(Context context){
        if (iranianSans==null){
            iranianSans= Typeface.createFromAsset(context.getAssets(),"fonts/iranian_sans.ttf");
        }
        return iranianSans;
    }

    public static Typeface getIranianSansLight(Context context){
        if (iranianSansLight==null){
            iranianSansLight= Typeface.createFromAsset(context.getAssets(),"fonts/iranian_sans_light.ttf");
        }
        return iranianSansLight;
    }

    public static Typeface getIranianSansBold(Context context){
        if (iranianSansBold==null){
            iranianSansBold= Typeface.createFromAsset(context.getAssets(),"fonts/iranian_sans_bold.ttf");
        }
        return iranianSansBold;
    }
}
