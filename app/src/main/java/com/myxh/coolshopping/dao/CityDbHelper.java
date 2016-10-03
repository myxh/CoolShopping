package com.myxh.coolshopping.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus on 2016/9/30.
 */

public class CityDbHelper extends SQLiteOpenHelper {

    public static final String DB_CITY_NAME="city.db";
    public static final int version=1;

    public CityDbHelper(Context context) {
        this(context, DB_CITY_NAME, null, version);
    }

    public CityDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_CITY_NAME, null, version);
    }

    public CityDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, DB_CITY_NAME, null, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS recentcity (" +
                "id integer primary key autoincrement," +
                " name varchar(40)," +
                " date INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
