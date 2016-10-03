package com.myxh.coolshopping.common;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class LocationService extends Service {
    private LocationClient mClient;
    private LocationClientOption mClientOpt;

    @Override
    public void onCreate() {
        mClient = new LocationClient(getApplicationContext());
        initLocation();
        mClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                if (CoolApplication.getAppContext().getLocations().size() >= 5) {
                    CoolApplication.getAppContext().getLocations().clear();//存放位置信息超出阈值之后清空位置列表
                }
                CoolApplication.getAppContext().getLocations().add(bdLocation);//往Application中添加地理位置
            }
        });
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        mClient.start();//开启定位服务
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        mClient.stop();//停止定位
        super.onDestroy();
    }

    private void initLocation() {
        mClientOpt = new LocationClientOption();
        mClientOpt.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//高精度
        mClientOpt.setCoorType("bd09ll");//设置返回的定位结果坐标系
        mClientOpt.setScanSpan(3000);//设置发起定位请求的时间间隔
        mClientOpt.setIsNeedAddress(true);//设置需要地址信息
        mClientOpt.setOpenGps(true);//设置打开GPS
        mClient.setLocOption(mClientOpt);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
