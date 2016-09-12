package com.myxh.coolshopping.listener;

import com.myxh.coolshopping.model.User;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by asus on 2016/9/10.
 */
public abstract class BmobQueryCallback implements IBmobListener {
    @Override
    public void onMsgSendSuccess() {

    }

    @Override
    public void onMsgSendFailure() {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure() {

    }

    @Override
    public void onSignUpSuccess(User user) {

    }

    @Override
    public void onSignUpFailure(BmobException e) {

    }
}
