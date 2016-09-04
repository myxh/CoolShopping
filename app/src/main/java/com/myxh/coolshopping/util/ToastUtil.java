package com.myxh.coolshopping.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by asus on 2016/8/30.
 */
public class ToastUtil {

    private static Toast toast;

    public static void show(Context context, CharSequence text) {
        if (toast != null) {
            toast.makeText(context,text,Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }

    public static void show(Context context, @StringRes int text) {
        if (toast != null) {
            toast.makeText(context,text,Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }
}
