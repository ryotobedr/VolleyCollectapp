package com.example.ryoma.volleycollectapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ryoma on 2015/12/09.
 */
public class UserOpenHelper extends SQLiteOpenHelper{

    /*
    public UserOpenHelper(Context context){
        super(context, "UniNumberNameDB", null, 1);
    }*/

    /*
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table person(" + " number text," + "name text not null" + ");");
    }
    */

//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }


    public static final String DB_NAME = "player.db";
    public static final int DB_VERSION = 1;
    public static final String CREATE_TABLE =
            "create table " + UserContract.Users.TABLE_NAME + "(" +
            UserContract.Users._ID + " integer primary key autoincrement, " +
            UserContract.Users.COL_PLNUM + " integer," +
            UserContract.Users.COL_NAME + " text)";
//    public static final String INIT_TABLE =
//            "insert into users (name, score) values " +
//            "(1, 'aburano'), " +
//            "(2, 'iwata'), " +
//            "(3, 'takahasi') ";
    public static final String DROP_TABLE =
            "drop table if exists users";

    public UserOpenHelper(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create table
        sqLiteDatabase.execSQL(CREATE_TABLE);
        // init table
//        sqLiteDatabase.execSQL(INIT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // drop table
        sqLiteDatabase.execSQL(DROP_TABLE);
        // onCreate
        onCreate(sqLiteDatabase);

    }
}
