package com.example.sshahini.myapplication.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sshahini.myapplication.R;
import com.example.sshahini.myapplication.SevenLearnDatabaseOpenHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.picasso.Picasso;

public class PostActivity extends AppCompatActivity {
    private static final String TAG = "PostActivity";
    public static final String EXTRA_KEY_IS_FROM_NOTIFICATIONS="is_from_notifications";
    private FirebaseAnalytics firebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        firebaseAnalytics=FirebaseAnalytics.getInstance(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/iranian_sans.ttf");
        Intent intent = getIntent();

        int id = intent.getIntExtra(SevenLearnDatabaseOpenHelper.COL_ID, 0);
        setPostVisited(id);

        String title = intent.getStringExtra(SevenLearnDatabaseOpenHelper.COL_TITLE);
        String content = intent.getStringExtra(SevenLearnDatabaseOpenHelper.COL_CONTENT);
        String postImageUrl = intent.getStringExtra(SevenLearnDatabaseOpenHelper.COL_POST_IMAGE_URL);
        String date = intent.getStringExtra(SevenLearnDatabaseOpenHelper.COL_DATE);
        int postImageResourceId=intent.getIntExtra("image_res_id",0);
        boolean isFromNotification=intent.getBooleanExtra(EXTRA_KEY_IS_FROM_NOTIFICATIONS,false);

        Bundle bundle=new Bundle();
        bundle.putString(EXTRA_KEY_IS_FROM_NOTIFICATIONS,String.valueOf(isFromNotification));
        firebaseAnalytics.logEvent("ViewPost",bundle);

        ImageView postImageView = (ImageView) findViewById(R.id.item_image);
        TextView titleTextView = (TextView) findViewById(R.id.news_title);
        TextView contentTextView = (TextView) findViewById(R.id.news_content);
        TextView dateTextView = (TextView) findViewById(R.id.news_date);

        if (postImageUrl!=null && !postImageUrl.isEmpty()) {
            Picasso.with(this).load(postImageUrl.replace("localhost", "192.168.1.104")).into(postImageView);
        }else {
            Picasso.with(this).load(postImageResourceId).into(postImageView);
        }


        titleTextView.setText(title);
        contentTextView.setText(content);
        dateTextView.setText(date);

        titleTextView.setTypeface(typeface);
        contentTextView.setTypeface(typeface);
        dateTextView.setTypeface(typeface);
    }

    private void setPostVisited(int postId){
        SevenLearnDatabaseOpenHelper databaseOpenHelper=new SevenLearnDatabaseOpenHelper(this);
        databaseOpenHelper.setPostIsVisited(postId,1);
    }



}
