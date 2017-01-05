/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sshahini.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.sshahini.myapplication.view.activity.MainActivity;
import com.example.sshahini.myapplication.view.activity.PostActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    private static final String ACTIVITY_MAIN="activity_main";
    private static final String ACTIVITY_POST="activity_post";

    private static final String DESTINATION_TYPE_URL="url";
    private static final String DESTINATION_TYPE_ACTIVITY="activity";

    private static final String DESTINATION_TYPE="destination_type";
    private static final String DESTINATION="destination";

    private static final String EXTRA_POST_TITLE="post_title";
    private static final String EXTRA_POST_CONTENT="post_content";
    private static final String EXTRA_POST_DATE="post_date";
    private static final String EXTRA_POST_IMAGE_URL="post_image_url";
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Map<String,String> data=remoteMessage.getData();
            switch (data.get(DESTINATION_TYPE)){
                case DESTINATION_TYPE_ACTIVITY:
                    String activity=data.get(DESTINATION);
                    switch (activity){
                        case ACTIVITY_MAIN:
                            Intent showMainActivity=new Intent(this,MainActivity.class);
                            showMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            sendNotification(remoteMessage.getNotification().getBody(),showMainActivity);
                            break;
                        case ACTIVITY_POST:
                            Intent showPostActivityIntent=new Intent(this, PostActivity.class);
                            String postTitle=data.get(EXTRA_POST_TITLE);
                            String postContent=data.get(EXTRA_POST_CONTENT);
                            String postImageUrl=data.get(EXTRA_POST_IMAGE_URL);
                            String postDate=data.get(EXTRA_POST_DATE);
                            showPostActivityIntent.putExtra(SevenLearnDatabaseOpenHelper.COL_TITLE,postTitle);
                            showPostActivityIntent.putExtra(SevenLearnDatabaseOpenHelper.COL_CONTENT,postContent);
                            showPostActivityIntent.putExtra(SevenLearnDatabaseOpenHelper.COL_POST_IMAGE_URL,postImageUrl);
                            showPostActivityIntent.putExtra(SevenLearnDatabaseOpenHelper.COL_DATE,postDate);
                            showPostActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            showPostActivityIntent.putExtra(PostActivity.EXTRA_KEY_IS_FROM_NOTIFICATIONS,true);
                            sendNotification(remoteMessage.getNotification().getBody(),showPostActivityIntent);
                    }
                    break;
                case DESTINATION_TYPE_URL:
                    String url=data.get(DESTINATION);
                    Intent showWebPageIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                    showWebPageIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    sendNotification(remoteMessage.getNotification().getBody(),Intent.createChooser(showWebPageIntent,
                            "انتخاب مرورگر..."));
                    break;
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody,Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_notification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentTitle("7Learn")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}