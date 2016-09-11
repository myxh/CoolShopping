package com.myxh.coolshopping.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by asus on 2016/9/10.
 */
public class LoginHelperUtil {

    private static final String TAG = LoginHelperUtil.class.getSimpleName();

    /**
     * 手机号输入是否正确
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return false;
        }
        if (!phoneNumber.matches("^[0-9]*$")) {
            Log.i(TAG, "isPhoneNumber: match error--"+phoneNumber);
            return false;
        }
        if (phoneNumber.length() != 11) {
            Log.i(TAG, "isPhoneNumber: length error--"+phoneNumber);
            return false;
        }
        if (phoneNumber.indexOf(0) == '1') {
            Log.i(TAG, "isPhoneNumber: start error--"+phoneNumber);
            return false;
        }
        return true;
    }

    /**
     * 验证码输入是否正确
     * @param code
     * @return
     */
    public static boolean isCodeCorrect(String code) {
        if (TextUtils.isEmpty(code)) {
            return false;
        }
        if (!code.matches("^[0-9]*$")) {
            return false;
        }
        return true;
    }
}
