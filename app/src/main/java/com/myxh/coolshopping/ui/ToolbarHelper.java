package com.myxh.coolshopping.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.myxh.coolshopping.R;

/**
 * Created by asus on 2016/8/29.
 */
public class ToolbarHelper {
    private Context mContext;
    private FrameLayout mContentView;
    private LayoutInflater mInflater;
    private View mUserView;
    private Toolbar mToolbar;

    public ToolbarHelper(Context context, @LayoutRes int layoutID) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        //初始化整个内容
        initContentView();
        //初始化自定义布局
        initUserView(layoutID);
        //初始化Toolbar
        initToolbar();
    }

    private void initUserView(int layoutID) {
        mUserView = mInflater.inflate(layoutID,null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mUserView.setLayoutParams(params);
    }

    private void initToolbar() {
        View view = mInflater.inflate(R.layout.layout_toolbar,mContentView);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
    }

    private void initContentView() {
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(params);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public FrameLayout getContentView() {
        return mContentView;
    }
}
