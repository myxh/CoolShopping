package com.myxh.coolshopping.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.ui.base.BaseFragment;

/**
 * Created by asus on 2016/8/27.
 */
public class MeFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,null);
        return view;
    }
}
