package com.myxh.coolshopping.common;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.yolanda.nohttp.NoHttp;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.update.BmobUpdateAgent;

/**
 * Created by asus on 2016/8/30.
 */
public class CoolApplication extends Application {

    private boolean flag = true;

    @Override
    public void onCreate() {
        super.onCreate();

        //NoHttp初始化
        NoHttp.initialize(this);
        //Fresco初始化
        Fresco.initialize(this);
        //百度地图初始化
        SDKInitializer.initialize(this);
        //Bmob初始化
        Bmob.initialize(this, AppConstant.BMOB_AppID);

        if (flag) {
            flag = false;
            BmobUpdateAgent.initAppVersion();
        }
    }
}
