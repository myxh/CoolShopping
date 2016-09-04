package com.myxh.coolshopping.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.ui.base.BaseFragment;

/**
 * Created by asus on 2016/8/28.
 */
public class BannerFragment extends BaseFragment {

    private int imgRes;

    public BannerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_banner,container,false);

        ImageView imageView = (ImageView) view.findViewById(R.id.banner_iv_bg);
        imageView.setBackgroundResource(imgRes);
        return view;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
