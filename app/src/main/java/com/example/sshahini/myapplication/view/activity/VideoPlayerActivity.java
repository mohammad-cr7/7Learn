package com.example.sshahini.myapplication.view.activity;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.sshahini.myapplication.R;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    private Timer timer;
    private TextView videoCurrentDurationTextView;
    private SeekBar seekBar;

    private RelativeLayout.LayoutParams portraitLayoutParams;
    private RelativeLayout.LayoutParams landscapeLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        setupLayoutParams();
        setupVideo();
    }

    private void setupVideo() {
        videoView=(VideoView)findViewById(R.id.video_view);
        videoView.setVideoURI(Uri.parse("http://clips.vorwaerts-gmbh.de/VfE_html5.mp4"));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                setupViews();
            }
        });
    }

    private void setupViews() {
        final ImageView playButton=(ImageView)findViewById(R.id.button_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoView.isPlaying()){
                    videoView.pause();
                    playButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_play,null));
                }else {
                    videoView.start();
                    playButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_pause,null));

                }
            }
        });
        ImageView forwardButton=(ImageView)findViewById(R.id.button_forward);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.seekTo(videoView.getCurrentPosition()+5000);
            }
        });
        ImageView rewindButton=(ImageView)findViewById(R.id.button_rewind);
        rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.seekTo(videoView.getCurrentPosition()-5000);
            }
        });

        TextView videoDurationTextView=(TextView)findViewById(R.id.text_video_duration);
        videoDurationTextView.setText(formatDuration(videoView.getDuration()));

        videoCurrentDurationTextView=(TextView)findViewById(R.id.text_video_current_duration);
        videoCurrentDurationTextView.setText(formatDuration(0));

        seekBar=(SeekBar)findViewById(R.id.seek_bar);
        seekBar.setMax(videoView.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    videoView.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       videoCurrentDurationTextView.setText(formatDuration(videoView.getCurrentPosition()));
                        seekBar.setProgress(videoView.getCurrentPosition());
                        seekBar.setSecondaryProgress((videoView.getBufferPercentage()*videoView.getDuration())/100);
                    }
                });
            }
        },0,1000);
    }

    private String formatDuration(long duration) {
        int seconds = (int) (duration / 1000);
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format(Locale.ENGLISH, "%02d", minutes) + ":" + String.format(Locale.ENGLISH, "%02d", seconds);
    }

    @Override
    protected void onDestroy() {
        if (timer!=null){
            timer.purge();
            timer.cancel();
        }
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        FrameLayout frameLayout=(FrameLayout)findViewById(R.id.frame_root);
        if (newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            frameLayout.setLayoutParams(portraitLayoutParams);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            frameLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        }else {
            frameLayout.setLayoutParams(landscapeLayoutParams);
            frameLayout.bringToFront();
            frameLayout.setBackgroundColor(ContextCompat.getColor(this,android.R.color.black));
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void setupLayoutParams(){
        View toolbar=findViewById(R.id.toolbar);
        View mediaController=findViewById(R.id.layout_media_controller);

        landscapeLayoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        portraitLayoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        portraitLayoutParams.addRule(RelativeLayout.BELOW,toolbar.getId());
        portraitLayoutParams.addRule(RelativeLayout.ABOVE,mediaController.getId());

    }
}
