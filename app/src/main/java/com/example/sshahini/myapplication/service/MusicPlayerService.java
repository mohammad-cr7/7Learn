package com.example.sshahini.myapplication.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.Toast;

import com.example.sshahini.myapplication.R;
import com.example.sshahini.myapplication.view.activity.MusicPlayerActivity;

import java.io.File;
import java.io.IOException;

/**
 * Created by Saeed shahini on 11/11/2016.
 */
public class MusicPlayerService extends Service {
    private static final String ACTION_PLAY="com.example.sshahini.myapplication.PLAY_MUSIC";
    private static final String ACTION_FORWARD="com.example.sshahini.myapplication.FORWARD_MUSIC";
    private static final String ACTION_REWIND="com.example.sshahini.myapplication.REWIND_MUSIC";

    private MediaPlayer mediaPlayer;
    private MusicPlayerBinder musicPlayerBinder=new MusicPlayerBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        setupMediaPlayer();
        return musicPlayerBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction()==null){
            intent.setAction("");
        }
        switch (intent.getAction()){
            case ACTION_PLAY:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }else {
                    mediaPlayer.start();
                }
                break;
            case ACTION_FORWARD:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
                break;
            case ACTION_REWIND:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
                break;
            default:
                Intent showMusicPlayerActivityIntent=new Intent(this, MusicPlayerActivity.class);
                showMusicPlayerActivityIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                Intent playIntent=new Intent(this,MusicPlayerService.class);
                playIntent.setAction(ACTION_PLAY);
                PendingIntent playPendingIntent= PendingIntent.getService(this,0,playIntent,0);

                Intent forwardIntent=new Intent(this,MusicPlayerService.class);
                forwardIntent.setAction(ACTION_FORWARD);
                PendingIntent forwardPendingIntent=PendingIntent.getService(this,0,forwardIntent,0);

                Intent rewindIntent=new Intent(this,MusicPlayerService.class);
                rewindIntent.setAction(ACTION_REWIND);
                PendingIntent rewindPendingIntent= PendingIntent.getService(this,0,rewindIntent,0);

                Notification notification=new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_melody_white)
                        .setContentTitle("7Learn Music Player")
                        .setContentText("KakoBand-Invite.mp3")
                        .setOngoing(true)
                        .setContentIntent(PendingIntent.getActivity(this,0,showMusicPlayerActivityIntent,0))
                        .addAction(R.drawable.ic_fast_rewind_black_48dp,"Rewind",rewindPendingIntent)
                        .addAction(R.drawable.ic_action_play_black,"play",playPendingIntent)
                        .addAction(R.drawable.ic_fast_forward_black_48dp,"Forward",forwardPendingIntent)
                        .build();
                startForeground(101,notification);
                break;
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        super.onDestroy();
    }

    private void setupMediaPlayer() {
        /*
        File musicDirectory = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File music = new File(musicDirectory, "invite_kakoband.mp3");
        if (music.exists()) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(this, Uri.fromFile(music));
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {

                    }
                });

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stopForeground(true);
                        stopSelf();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Music not found", Toast.LENGTH_LONG).show();
        }
         */
        mediaPlayer=MediaPlayer.create(this,R.raw.invite_kakoband);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopForeground(true);
                stopSelf();
            }
        });
    }

    public MediaPlayer getMediaPlayer(){
        return this.mediaPlayer;
    }

    public class MusicPlayerBinder extends Binder{
        public MusicPlayerService getService(){
            return MusicPlayerService.this;
        }
    }
}
