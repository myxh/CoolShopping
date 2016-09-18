package com.myxh.coolshopping.common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by asus on 2016/6/29.
 * 管理所有Activity
 */
public class AppManager {

    public static Stack<Activity> activityStack;
    public static AppManager instance;

    private AppManager() {}

    /**
     * 获取AppManager单例
     */
    public static AppManager getInstance() {
        if(instance == null) {
            synchronized (AppManager.class) {
                if (instance == null)
                    instance = new AppManager();
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity
     */
    public Activity getCurrentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity
     */
    public void finishCurrentActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名Activity
     */
    public void finishActivity(Class<?> activityClass) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(activityClass)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
    }

}
