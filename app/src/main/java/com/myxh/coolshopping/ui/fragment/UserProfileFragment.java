package com.myxh.coolshopping.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.ui.base.BaseFragment;

/**
 * Created by asus on 2016/9/18.
 */
public class UserProfileFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile,null);
        return view;
    }
}
