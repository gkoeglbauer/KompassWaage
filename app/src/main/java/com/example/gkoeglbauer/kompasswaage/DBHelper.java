package com.example.gkoeglbauer.kompasswaage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by felixdeixler on 29.05.15.
 */
public class DBHelper extends SQLiteOpenHelper{

    final static String DB_NAME= "positions.db";
    final static int DB_VERSION= 1;

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
