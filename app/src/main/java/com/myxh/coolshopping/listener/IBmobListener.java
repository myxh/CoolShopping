package com.myxh.coolshopping.listener;

/**
 * Created by asus on 2016/9/9.
 */
public interface IBmobListener {

    void onMsgSendSuccess();
    void onMsgSendFailure();
    void onLoginSuccess();
    void onLoginFailure();
    void onQuerySuccess();
    void onQueryFailure();
    void onQueryAllSuccess();
    void onQueryAllFailure();
}
