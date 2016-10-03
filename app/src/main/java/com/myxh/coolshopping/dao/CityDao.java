package com.myxh.coolshopping.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by asus on 2016/9/30.
 */

public class CityDao {

    private CityDbHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    public CityDao(Context context) {
        mDbHelper = new CityDbHelper(context);
    }

    public void queryCity() {

    }
}
