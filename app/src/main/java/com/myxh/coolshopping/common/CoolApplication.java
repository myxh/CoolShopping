package com.myxh.coolshopping.common;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.yolanda.nohttp.NoHttp;

/**
 * Created by asus on 2016/8/30.
 */
public class CoolApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        NoHttp.initialize(this);
        Fresco.initialize(this);
        SDKInitializer.initialize(this);
    }
}
