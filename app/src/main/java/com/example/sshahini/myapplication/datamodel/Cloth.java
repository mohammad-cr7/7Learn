package com.example.sshahini.myapplication.datamodel;

import android.graphics.drawable.Drawable;

/**
 * Created by Saeed shahini on 7/30/2016.
 */
public class Cloth {
    private int id;
    private Drawable image;
    private String title;
    private int viewCount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
