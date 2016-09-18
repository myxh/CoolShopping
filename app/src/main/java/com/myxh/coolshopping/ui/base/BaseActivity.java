package com.myxh.coolshopping.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.common.AppManager;

/**
 * Created by asus on 2016/8/28.
 */
public class BaseActivity extends AppCompatActivity {
    public static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //设置窗口无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: "+this.getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: "+this.getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: "+this.getClass().getSimpleName());
        AppManager.getInstance().finishActivity(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: "+this.getClass().getSimpleName());
        return super.dispatchTouchEvent(ev);
    }

    protected void openActivity(Class<?> mClass) {
        Log.d(TAG, "openActivity: open "+mClass.getSimpleName());
        openActivity(mClass,null);
    }

    protected void openActivity(Class<?> mClass, Bundle bundle) {
        Intent intent = new Intent(this,mClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        Log.d(TAG, "openActivity with bundle: open "+mClass.getSimpleName());
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    protected void openActivityWithoutAnim(Class<?> mClass) {
        Log.d(TAG, "openActivityWithoutAnim: "+mClass.getSimpleName());
        openActivityWithoutAnim(mClass,null);
    }

    protected void openActivityWithoutAnim(Class<?> mClass, Bundle bundle) {
        Intent intent = new Intent(this,mClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        Log.d(TAG, "openActivityWithoutAnim with bundle: "+mClass.getSimpleName());
        startActivity(intent);
    }

    protected void openActivity(String action) {
        openActivity(action,null);
    }

    protected void openActivity(String action, Bundle bundle) {
        Intent intent = new Intent(action);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        Log.d(TAG, "openActivity by action: action----"+action);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        Log.d(TAG, "finish: "+this.getClass().getSimpleName());
        overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
}
