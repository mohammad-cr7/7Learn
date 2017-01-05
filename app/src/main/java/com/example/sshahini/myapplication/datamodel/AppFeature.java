package com.example.sshahini.myapplication.datamodel;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * Created by Saeed shahini on 9/9/2016.
 */
public class AppFeature {

    public static final int ID_POSTS_ACTIVITY = 0;
    public static final int ID_USER_PROFILE = 1;
    public static final int ID_FASHION = 2;
    public static final int ID_MUSIC = 3;
    public static final int ID_VIDEO = 4;
    public static final int ID_LOGIN = 5;
    public static final int ID_ANIMATIONS=6;
    public static final int ID_MAP=7;

    private int id;
    private String title;
    @DrawableRes
    private int featureImage;
    private Class DestinationActivity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(int featureImage) {
        this.featureImage = featureImage;
    }

    public Class getDestinationActivity() {
        return DestinationActivity;
    }

    public void setDestinationActivity(Class destinationActivity) {
        DestinationActivity = destinationActivity;
    }
}
