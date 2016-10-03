package com.myxh.coolshopping.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.ui.base.BaseActivity;

/**
 * Created by asus on 2016/10/3.
 */

public class MessageActivity extends BaseActivity {

    private ImageView mTitleBarIvBack;
    private TextView mTitleBarTvTitle;
    private TextView mTitleBarTvRight;
    private FrameLayout mContentLayout;
    private TextView mErrorTv;
    private LinearLayout mErrorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_info_activity);
        initView();
    }

    private void initView() {
        mTitleBarIvBack = (ImageView) findViewById(R.id.common_titleBar_iv_back);
        mTitleBarTvTitle = (TextView) findViewById(R.id.common_titleBar_tv_title);
        mTitleBarTvRight = (TextView) findViewById(R.id.common_titleBar_tv_right);
        mContentLayout = (FrameLayout) findViewById(R.id.common_content_layout);
        mErrorTv = (TextView) findViewById(R.id.common_error_tv);
        mErrorLayout = (LinearLayout) findViewById(R.id.common_error_layout);

        mTitleBarTvTitle.setText(R.string.message);
        mTitleBarTvRight.setText(R.string.collect_titleBar_manager);
        mTitleBarIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mContentLayout.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.VISIBLE);
        mErrorTv.setText(R.string.no_message);
    }
}
