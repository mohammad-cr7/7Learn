package com.example.sshahini.myapplication;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.example.sshahini.myapplication.datamodel.Post;

import java.util.List;

/**
 * Created by Saeed shahini on 9/8/2016.
 */
public class AddPostsTask extends AsyncTask<Void,Integer,String> {
    private static final String TAG = "AddPostsTask";

    private Context context;
    private List<Post> posts;
    private SevenLearnDatabaseOpenHelper sevenLearnDatabaseOpenHelper;
    private ProgressDialog progressDialog;

    public AddPostsTask(Context context, List<Post> posts,SevenLearnDatabaseOpenHelper openHelper){

        this.context = context;
        this.posts = posts;
        this.sevenLearnDatabaseOpenHelper=openHelper;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("در حال ذخیره سازی");
        progressDialog.setMessage("در حال ذخیره سازی پست ها، لطفاً منتظر بمانید...");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {

        for (int i = 0; i < posts.size(); i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ContentValues cv=new ContentValues();
            Post post=posts.get(i);
            cv.put(SevenLearnDatabaseOpenHelper.COL_ID,post.getId());
            cv.put(SevenLearnDatabaseOpenHelper.COL_TITLE,post.getTitle());
            cv.put(SevenLearnDatabaseOpenHelper.COL_CONTENT,post.getContent());
            cv.put(SevenLearnDatabaseOpenHelper.COL_POST_IMAGE_URL,post.getPostImageUrl());
            cv.put(SevenLearnDatabaseOpenHelper.COL_IS_VISITED,post.getIsVisited());
            cv.put(SevenLearnDatabaseOpenHelper.COL_DATE,post.getDate());

            SQLiteDatabase sqLiteDatabase=sevenLearnDatabaseOpenHelper.getWritableDatabase();
            long isInserted=sqLiteDatabase.insert(SevenLearnDatabaseOpenHelper.POST_TABLE_NAME,null,cv);

            publishProgress((i*100)/posts.size());
            Log.i(TAG, "addPost: "+isInserted);
        }
        return "";
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }
}
