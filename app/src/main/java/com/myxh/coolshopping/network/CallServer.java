package com.myxh.coolshopping.network;

import android.content.Context;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;

/**
 * Created by asus on 2016/8/30.
 */
public class CallServer {

    private RequestQueue mQueue;

    private static CallServer ourInstance = new CallServer();

    public static CallServer getInstance() {
        return ourInstance;
    }

    private CallServer() {
        mQueue = NoHttp.newRequestQueue();
    }

    public<T> void add(Context context, int what, Request<T> request, HttpListener<T> httpListener,
                       boolean canCancel, boolean isLoading) {
        mQueue.add(what,request,
                new HttpResponseListener<T>(context, request, httpListener, canCancel, isLoading));
    }
}
