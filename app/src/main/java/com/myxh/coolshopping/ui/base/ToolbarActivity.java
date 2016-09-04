package com.myxh.coolshopping.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.myxh.coolshopping.ui.ToolbarHelper;

/**
 * Created by asus on 2016/8/29.
 */
public class ToolbarActivity extends BaseActivity {

    private ToolbarHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        mHelper = new ToolbarHelper(this,layoutResID);
        Toolbar toolbar = mHelper.getToolbar();
        setContentView(mHelper.getContentView());
        setSupportActionBar(toolbar);

        setCustomToolbar(toolbar);
    }

    protected void setCustomToolbar(Toolbar toolbar) {

    }
}
