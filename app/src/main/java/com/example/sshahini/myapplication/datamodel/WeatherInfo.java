package com.example.sshahini.myapplication.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Saeed shahini on 8/11/2016.
 */
public class WeatherInfo implements Parcelable {
    private int weatherId;
    private String weatherName;
    private String weatherDescription;
    private float windSpeed;
    private float windDegree;
    private int humidity;
    private float weatherTemprature;
    private float minTemprature;
    private float maxTemprature;
    private int pressure;

    public String getWeatherName() {
        return weatherName;
    }

    public void setWeatherName(String weatherName) {
        this.weatherName = weatherName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(float windDegree) {
        this.windDegree = windDegree;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getWeatherTemprature() {
        return weatherTemprature;
    }

    public void setWeatherTemprature(float weatherTemprature) {
        this.weatherTemprature = weatherTemprature;
    }

    public float getMinTemprature() {
        return minTemprature;
    }

    public void setMinTemprature(float minTemprature) {
        this.minTemprature = minTemprature;
    }

    public float getMaxTemprature() {
        return maxTemprature;
    }

    public void setMaxTemprature(float maxTemprature) {
        this.maxTemprature = maxTemprature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public WeatherInfo(){

    }

    protected WeatherInfo(Parcel in) {
        weatherId = in.readInt();
        weatherName = in.readString();
        weatherDescription = in.readString();
        windSpeed = in.readFloat();
        windDegree = in.readFloat();
        humidity = in.readInt();
        weatherTemprature = in.readFloat();
        minTemprature = in.readFloat();
        maxTemprature = in.readFloat();
        pressure = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(weatherId);
        dest.writeString(weatherName);
        dest.writeString(weatherDescription);
        dest.writeFloat(windSpeed);
        dest.writeFloat(windDegree);
        dest.writeInt(humidity);
        dest.writeFloat(weatherTemprature);
        dest.writeFloat(minTemprature);
        dest.writeFloat(maxTemprature);
        dest.writeInt(pressure);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WeatherInfo> CREATOR = new Parcelable.Creator<WeatherInfo>() {
        @Override
        public WeatherInfo createFromParcel(Parcel in) {
            return new WeatherInfo(in);
        }

        @Override
        public WeatherInfo[] newArray(int size) {
            return new WeatherInfo[size];
        }
    };
}