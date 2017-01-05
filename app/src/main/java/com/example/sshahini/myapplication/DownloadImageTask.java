package com.example.sshahini.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Permission;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saeed shahini on 9/8/2016.
 */
public class DownloadImageTask extends AsyncTask<Void,Integer,Void>{
    private Context context;
    private List<String> urls=new ArrayList<>();
    private ProgressDialog progressDialog;

    public DownloadImageTask(Context context,String url){
        urls.add(url);
        this.context=context;
    }
    public DownloadImageTask(Context context,List<String> urls){
        this.urls=urls;
        this.context=context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("ذخیره سازی عکس ها");
        progressDialog.setMessage("در حال ذخیره سازی عکس ها، لطفاً منتظر بمانید.");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for (int i = 0; i < urls.size(); i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Bitmap bitmap= Picasso.with(context).load(urls.get(i)).get();
                String url=urls.get(i);

                File extImageDir=context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                String imageName=url.substring(url.lastIndexOf("/")+1,url.length());
                File image=new File(extImageDir,imageName);
                FileOutputStream fos=new FileOutputStream(image);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
                fos.flush();
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            publishProgress((i*100)/urls.size());

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

}
