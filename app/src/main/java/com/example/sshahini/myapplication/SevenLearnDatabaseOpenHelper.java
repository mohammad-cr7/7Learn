package com.example.sshahini.myapplication;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sshahini.myapplication.datamodel.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saeed shahini on 8/26/2016.
 */
public class SevenLearnDatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseOpenHelper";

    private static final String DATABASE_NAME="db_7learn";
    private static final int DATABASE_VERSION=1;

    public static final String POST_TABLE_NAME="tbl_posts";


    public static final String COL_ID="col_id";
    public static final String COL_TITLE="col_title";
    public static final String COL_CONTENT="col_content";
    public static final String COL_POST_IMAGE_URL="col_post_image_url";
    public static final String COL_IS_VISITED="col_is_visited";
    public static final String COL_DATE="col_date";

    private static final String SQL_COMMAND_CREATE_POST_TABLE="CREATE TABLE IF NOT EXISTS "+POST_TABLE_NAME+"("+
            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_TITLE+" TEXT,"+
            COL_CONTENT+" TEXT, "+
            COL_POST_IMAGE_URL+" TEXT, "+
            COL_IS_VISITED+" INTEGER DEFAULT 0, "+
            COL_DATE+" TEXT);";

    Context context;
    public SevenLearnDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_COMMAND_CREATE_POST_TABLE);
        }catch (SQLException e){
            Log.e(TAG, "onCreate: "+e.toString() );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addPosts(List<Post> posts){

        AddPostsTask addPostsTask=new AddPostsTask(context,posts,this);
        addPostsTask.execute();

    }
    public List<Post> getPosts(){
        List<Post> posts=new ArrayList<>();

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+POST_TABLE_NAME,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            while (!cursor.isAfterLast()){
                Post post=new Post();
                post.setId(cursor.getInt(0));
                post.setTitle(cursor.getString(1));
                post.setContent(cursor.getString(2));
                post.setPostImageUrl(cursor.getString(3));
                post.setIsVisited(cursor.getInt(4));
                post.setDate(cursor.getString(5));
                posts.add(post);
                cursor.moveToNext();
            }
        }

        cursor.close();
        sqLiteDatabase.close();
        return posts;
    }

    public void setPostIsVisited(int postId,int isVisited){
        SQLiteDatabase sqliteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_IS_VISITED,isVisited);
        int rowAffected=sqliteDatabase.update(POST_TABLE_NAME,contentValues,COL_ID+" = ?",new String[]{String.valueOf(postId)});
        Log.i(TAG, "setPostIsVisited: rowAffected=> "+rowAffected);
        sqliteDatabase.close();
    }

    private boolean checkPostExists(int postId){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "
                +POST_TABLE_NAME
                +" WHERE "
                +COL_ID
                +" = ?",new String[]{String.valueOf(postId)});
        return cursor.moveToFirst();
    }

    private void deletePost(int postId){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(POST_TABLE_NAME,COL_ID+" = ?",new String[]{String.valueOf(postId)});
    }


}
