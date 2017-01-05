package com.example.sshahini.myapplication.datamodel;

/**
 * Created by Saeed shahini on 8/23/2016.
 */
public class User {
    private String firstName="";
    private String lastName="";

    private boolean isHtmlExpert=false;
    private boolean isCssExpert=false;
    private boolean isJavaExpert=false;

    public static final byte MALE=0;
    public static final byte FEMALE=1;

    private byte gender=MALE;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isHtmlExpert() {
        return isHtmlExpert;
    }

    public void setHtmlExpert(boolean htmlExpert) {
        isHtmlExpert = htmlExpert;
    }

    public boolean isCssExpert() {
        return isCssExpert;
    }

    public void setCssExpert(boolean cssExpert) {
        isCssExpert = cssExpert;
    }

    public boolean isJavaExpert() {
        return isJavaExpert;
    }

    public void setJavaExpert(boolean javaExpert) {
        isJavaExpert = javaExpert;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }
}
