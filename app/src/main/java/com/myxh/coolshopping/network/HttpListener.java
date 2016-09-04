package com.myxh.coolshopping.network;

import com.yolanda.nohttp.rest.Response;

/**
 * Created by asus on 2016/8/30.
 */
public interface HttpListener<T> {
    public void onSucceed(int what, Response<T> response);
    public void onFailed(int what, Response<T> response);
}
