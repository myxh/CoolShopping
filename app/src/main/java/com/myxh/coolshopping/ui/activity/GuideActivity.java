package com.myxh.coolshopping.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.ui.adapter.ViewPageAdapter;
import com.myxh.coolshopping.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/8/28.
 */
public class GuideActivity extends BaseActivity {

    private ViewPager pager;
    private Button mBtnStart;
    private List<View> mViewList = new ArrayList<>();
    private ViewPageAdapter mPageAdapter;

    private int[] imgRes = new int[] {
            R.mipmap.guide_1,R.mipmap.guide_2,
            R.mipmap.guide_3,R.mipmap.guide_4};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initDatas();
        initViews();
    }

    private void initDatas() {
        for (int i = 0; i < imgRes.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_guide,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.item_guide_bg);
            imageView.setBackgroundResource(imgRes[i]);
            mViewList.add(view);
        }
        mPageAdapter = new ViewPageAdapter(mViewList);
    }

    private void initViews() {
        pager = (ViewPager) findViewById(R.id.guide_viewPager);
        pager.setAdapter(mPageAdapter);
        pager.addOnPageChangeListener(new MyPageChangeListener());
        mBtnStart = (Button) findViewById(R.id.guide_btn_start);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == mViewList.size()-1) {
                mBtnStart.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(GuideActivity.this,R.anim.anim_guide_btn_start);
                mBtnStart.startAnimation(animation);
            } else {
                mBtnStart.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
