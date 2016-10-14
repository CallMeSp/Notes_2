package com.example.notes_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by my on 2016/10/11.
 */
public class DBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME="my.db";
    private final static int DB_VERSION=1;
    private final  static String TABLE_NAME="info";
    private final static String CONTENT="content";
    private final static String ID="_id";
    SQLiteDatabase database=getWritableDatabase();
    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL("create table " + TABLE_NAME + "(" + ID + " integer primary key autoincrement," + CONTENT + " text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public long insert(String text){
        ContentValues contentValues=new ContentValues();
        contentValues.put("content",text);
        long row=database.insert(TABLE_NAME,null,contentValues);
        return row;
    }
    public void update(int _id,String text){
        ContentValues contentValues=new ContentValues();
        contentValues.put("content",text);
        database.update(TABLE_NAME,contentValues,ID+"=?",new String[]{Integer.toString(_id)});
    }
    public void delete(int _id){
        database.delete(TABLE_NAME, ID + "=?", new String[]{Integer.toString(_id)});
    }
    public Cursor select(){
        Cursor cursor=database.query(TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }
}
