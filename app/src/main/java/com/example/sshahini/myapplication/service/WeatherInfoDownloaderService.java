package com.example.sshahini.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.sshahini.myapplication.ApiService;
import com.example.sshahini.myapplication.WeatherWidget;
import com.example.sshahini.myapplication.datamodel.WeatherInfo;

/**
 * Created by Saeed shahini on 11/4/2016.
 */
public class WeatherInfoDownloaderService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ApiService apiService=new ApiService(this);
        apiService.getCurrentWeather(new ApiService.OnWeatherInfoRecieved() {
            @Override
            public void onReceived(WeatherInfo weatherInfo) {
                if (weatherInfo!=null){
                    Intent sendDataIntent=new Intent(WeatherWidget.INTENT_ACTION_UPDATE_DATA);
                    sendDataIntent.putExtra("data",weatherInfo);
                    sendBroadcast(sendDataIntent);
                }
                stopSelf();
            }
        });
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
