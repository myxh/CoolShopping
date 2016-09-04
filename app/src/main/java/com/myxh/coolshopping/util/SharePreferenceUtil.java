package com.myxh.coolshopping.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.myxh.coolshopping.common.AppConstant;

/**
 * Created by asus on 2016/8/28.
 */
public class SharePreferenceUtil {

    public static final String PREFERENCE_NAME = AppConstant.SHARE_PATH;

    /**
     * put String to SharePreference
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        return editor.commit();
    }

    /**
     * get String from SharedPreferences
     */
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return preferences.getString(key,defaultValue);
    }

    /**
     * get String from SharedPreferences
     */
    public static String getString(Context context, String key) {
        return getString(context,key,null);
    }

    /**
     * put int to SharedPreferences
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key,value);
        return editor.commit();
    }

    /**
     * get int from SharedPreferences
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return preferences.getInt(key,defaultValue);
    }

    /**
     * get int from SharedPreferences
     */
    public static int getInt(Context context, String key) {
        return getInt(context,key,-1);
    }

    /**
     * put long to SharedPreferences
     */
    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key,value);
        return editor.commit();
    }

    /**
     * get long from SharedPreferences
     */
    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return preferences.getLong(key,defaultValue);
    }

    /**
     * get long from SharedPreferences
     */
    public static long getLong(Context context, String key) {
        return getLong(context,key,-1);
    }

    /**
     * put float to SharedPreferences
     */
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key,value);
        return editor.commit();
    }

    /**
     * get float from SharedPreferences
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return preferences.getFloat(key,defaultValue);
    }

    /**
     * get float from SharedPreferences
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context,key,-1);
    }

    /**
     * put boolean to SharedPreferences
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key,value);
        return editor.commit();
    }

    /**
     * get boolean from SharedPreferences
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return preferences.getBoolean(key,defaultValue);
    }

    /**
     * get boolean from SharedPreferences
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context,key,false);
    }

    /**
     * if SharedPreferences have the key
     */
    public static boolean haveKey(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return preferences.contains(key);
    }

}
