package com.myxh.coolshopping.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by asus on 2016/10/4.
 */

public class DialogUtil {

    private static ProgressDialog mDialog;

    /**
     * 显示Dialog
     * @param context
     * @param message
     */
    public static void showDialog(Context context, String message) {
        try {
            if (mDialog == null) {
                mDialog = new ProgressDialog(context);
                mDialog.setCancelable(true);
            }
            mDialog.setMessage(message);
            mDialog.show();
        } catch (Exception e) {
            // 在其他线程调用mDialog会报错
        }
    }

    /**
     * 隐藏Dialog
     */
    public static void hideDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            try {
                mDialog.dismiss();
            } catch (Exception e) {
                //
            }
        }
    }
}
