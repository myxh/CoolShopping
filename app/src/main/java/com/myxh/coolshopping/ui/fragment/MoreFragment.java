package com.myxh.coolshopping.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.common.CoolApplication;
import com.myxh.coolshopping.ui.base.BaseFragment;
import com.myxh.coolshopping.util.DataClearUtil;
import com.myxh.coolshopping.util.ToastUtil;

import java.util.Date;

import cn.bmob.v3.update.BmobUpdateAgent;

/**
 * Created by asus on 2016/8/27.
 */
public class MoreFragment extends BaseFragment implements View.OnClickListener {
    private Toolbar mToolbar;
    private CheckBox mBtnWifiSwitch;
    private CheckBox mBtnNoticeSwitch;
    private RelativeLayout mItemShareLayout;
    private TextView mItemTvCacheSize;
    private RelativeLayout mItemClearCacheLayout;
    private RelativeLayout mItemCommentLayout;
    private RelativeLayout mItemFeedbackLayout;
    private TextView mItemTvKefu;
    private RelativeLayout mItemContactKefuLayout;
    private TextView mItemTvCurrentVersion;
    private RelativeLayout mItemCheckUpdateLayout;
    private RelativeLayout mItemAboutLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.more_toolbar);
        mBtnWifiSwitch = (CheckBox) view.findViewById(R.id.more_btn_wifi_switch);
        mBtnNoticeSwitch = (CheckBox) view.findViewById(R.id.more_btn_notice_switch);
        mItemShareLayout = (RelativeLayout) view.findViewById(R.id.more_item_share_layout);
        mItemTvCacheSize = (TextView) view.findViewById(R.id.more_item_tv_cacheSize);
        mItemClearCacheLayout = (RelativeLayout) view.findViewById(R.id.more_item_clear_cache_layout);
        mItemCommentLayout = (RelativeLayout) view.findViewById(R.id.more_item_comment_layout);
        mItemFeedbackLayout = (RelativeLayout) view.findViewById(R.id.more_item_feedback_layout);
        mItemTvKefu = (TextView) view.findViewById(R.id.more_item_tv_kefu);
        mItemContactKefuLayout = (RelativeLayout) view.findViewById(R.id.more_item_contact_kefu_layout);
        mItemTvCurrentVersion = (TextView) view.findViewById(R.id.more_item_tv_current_version);
        mItemCheckUpdateLayout = (RelativeLayout) view.findViewById(R.id.more_item_check_update_layout);
        mItemAboutLayout = (RelativeLayout) view.findViewById(R.id.more_item_about_layout);

        mBtnWifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {

                }
            }
        });
        mBtnNoticeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {

                }
            }
        });
        mItemShareLayout.setOnClickListener(this);
        mItemClearCacheLayout.setOnClickListener(this);
        mItemCommentLayout.setOnClickListener(this);
        mItemFeedbackLayout.setOnClickListener(this);
        mItemContactKefuLayout.setOnClickListener(this);
        mItemCheckUpdateLayout.setOnClickListener(this);
        mItemAboutLayout.setOnClickListener(this);

        mItemTvCacheSize.setText(DataClearUtil.getTotalCacheSize(getActivity()));
        mItemTvCurrentVersion.setText(CoolApplication.getAppContext().getAppVersion());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_item_share_layout:
                break;
            case R.id.more_item_clear_cache_layout:
                DataClearUtil.cleanAllCache(getActivity());
                ToastUtil.show(getActivity(),R.string.clear_cache_success);
                mItemTvCacheSize.setText(DataClearUtil.getTotalCacheSize(getActivity()));
                break;
            case R.id.more_item_comment_layout:
                openAppMarket("com.netease.edu.study");
                break;
            case R.id.more_item_feedback_layout:
                break;
            case R.id.more_item_contact_kefu_layout:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+mItemTvKefu.getText()));
                startActivity(intent);
                break;
            case R.id.more_item_check_update_layout:
                BmobUpdateAgent.forceUpdate(getActivity());
                break;
            case R.id.more_item_about_layout:
                break;
        }
    }

    /**
     * 打开应用商店
     * @param packageName
     */
    private void openAppMarket(String packageName) {
        try {
            String str = "market://detail?id=" + packageName;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(str));
            startActivity(intent);
        } catch (Exception e) {
            ToastUtil.show(getActivity(),R.string.open_app_market_failed);
            e.printStackTrace();
            //打开应用商店失败后通过浏览器访问
            String url = "https://github.com/myxh";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }
}
