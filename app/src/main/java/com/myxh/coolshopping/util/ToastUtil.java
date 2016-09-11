package com.myxh.coolshopping.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by asus on 2016/8/30.
 */
public class ToastUtil {

    private static Toast toast;
    private static Toast longToast;

    public static void show(Context context, CharSequence text) {
        if (toast == null) {
            toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }

    public static void show(Context context, @StringRes int textRes) {
        if (toast == null) {
            toast = Toast.makeText(context,textRes,Toast.LENGTH_SHORT);
        }
        toast.setText(textRes);
        toast.show();
    }

    public static void showLong(Context context, CharSequence text) {
        if (longToast == null) {
            longToast = Toast.makeText(context,text,Toast.LENGTH_LONG);
        }
        longToast.setText(text);
        longToast.show();
    }

    public static void showLong(Context context, @StringRes int textRes) {
        if (longToast == null) {
            longToast = Toast.makeText(context,textRes,Toast.LENGTH_LONG);
        }
        longToast.setText(textRes);
        longToast.show();
    }
}
