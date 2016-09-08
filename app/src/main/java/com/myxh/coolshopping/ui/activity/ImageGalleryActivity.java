package com.myxh.coolshopping.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myxh.coolshopping.R;
import com.myxh.coolshopping.entity.GoodsDetailInfo;
import com.myxh.coolshopping.ui.adapter.ViewPageAdapter;
import com.myxh.coolshopping.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ImageGalleryActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mPager;
    private TextView mTvProduct;
    private TextView mTvIndex;
    private TextView mTvCount;

    private ViewPageAdapter mAdapter;
    private List<View> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        initView();
        setViewWithIntentData();
    }

    private void setViewWithIntentData() {
        mViews = new ArrayList<>();
        GoodsDetailInfo detailInfo = (GoodsDetailInfo) getIntent().getSerializableExtra(DetailActivity.DETAIL_INFO);
        List<String> detailImags = detailInfo.getResult().getDetail_imags();
        for (int i = 0; i < detailImags.size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.item_image_gallery,null);
            SimpleDraweeView photo = (SimpleDraweeView) view.findViewById(R.id.item_image_big_photo);
            photo.setImageURI(Uri.parse(detailImags.get(i)));
            mViews.add(view);
        }
        mAdapter = new ViewPageAdapter(mViews);
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(this);

        mTvProduct.setText(detailInfo.getResult().getProduct());
        mTvIndex.setText("1");
        mTvCount.setText(""+detailImags.size());
    }

    private void initView() {
        mPager = (ViewPager) findViewById(R.id.image_pager);
        mTvProduct = (TextView) findViewById(R.id.image_tv_product);
        mTvIndex = (TextView) findViewById(R.id.image_tv_index);
        mTvCount = (TextView) findViewById(R.id.image_tv_count);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTvIndex.setText(""+(position+1));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
