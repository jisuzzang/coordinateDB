package com.example.user.projectcoordinatedb4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 2016-04-08.
 */
public class myDB extends SQLiteOpenHelper {
    public myDB(Context context){
        super(context, "CoordinateDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table memberDB" + "(id char(30) primary key, latitude double, longitude double)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS memberDB");
        onCreate(db);
    }
}