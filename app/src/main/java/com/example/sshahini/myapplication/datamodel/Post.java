package com.example.sshahini.myapplication.datamodel;

import android.graphics.drawable.Drawable;

/**
 * Created by Saeed shahini on 7/21/2016.
 */
public class Post {

    private int id;
    private String postImageUrl;
    private String title;
    private String content;
    private String date;
    private int isVisited=0;
    private int postImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(int isVisited) {
        this.isVisited = isVisited;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }
}
