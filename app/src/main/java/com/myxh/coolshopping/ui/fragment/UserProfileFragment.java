package com.myxh.coolshopping.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myxh.coolshopping.R;
import com.myxh.coolshopping.model.User;
import com.myxh.coolshopping.ui.activity.UserProfileActivity;
import com.myxh.coolshopping.ui.base.BaseFragment;

/**
 * Created by asus on 2016/9/18.
 */
public class UserProfileFragment extends BaseFragment implements View.OnClickListener {

    private SimpleDraweeView mAvatar;
    private RelativeLayout mAvatarLayout;
    private TextView mNameTvUsername;
    private RelativeLayout mNameLayout;
    private TextView mNicknameTvNickname;
    private RelativeLayout mNicknameLayout;
    private TextView mBindPhoneTvNickname;
    private TextView mBindPhoneTvState;
    private RelativeLayout mBindPhoneLayout;
    private TextView mPayPasswordTvState;
    private RelativeLayout mPayPasswordLayout;
    private TextView mLoginPasswordTvState;
    private RelativeLayout mLoginPasswordLayout;
    private ImageView mLevelIvLevel;
    private RelativeLayout mLevelLayout;
    private TextView mGenderTvState;
    private RelativeLayout mGenderLayout;
    private TextView mBirthdayTvState;
    private RelativeLayout mBirthdayLayout;
    private RelativeLayout mAddressLayout;
    private RelativeLayout mQrLayout;
    private Button mBtnExitLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mAvatar = (SimpleDraweeView) view.findViewById(R.id.user_item_avatar);
        mAvatarLayout = (RelativeLayout) view.findViewById(R.id.user_item_avatar_layout);
        mNameTvUsername = (TextView) view.findViewById(R.id.user_item_username_tv_username);
        mNameLayout = (RelativeLayout) view.findViewById(R.id.user_item_username_layout);
        mNicknameTvNickname = (TextView) view.findViewById(R.id.user_item_nickname_tv_nickname);
        mNicknameLayout = (RelativeLayout) view.findViewById(R.id.user_item_nickname_layout);
        mBindPhoneTvNickname = (TextView) view.findViewById(R.id.user_item_bindPhone_tv_nickname);
        mBindPhoneTvState = (TextView) view.findViewById(R.id.user_item_bindPhone_tv_state);
        mBindPhoneLayout = (RelativeLayout) view.findViewById(R.id.user_item_bindPhone_layout);
        mPayPasswordTvState = (TextView) view.findViewById(R.id.user_item_payPassword_tv_state);
        mPayPasswordLayout = (RelativeLayout) view.findViewById(R.id.user_item_payPassword_layout);
        mLoginPasswordTvState = (TextView) view.findViewById(R.id.user_item_loginPassword_tv_state);
        mLoginPasswordLayout = (RelativeLayout) view.findViewById(R.id.user_item_loginPassword_layout);
        mLevelIvLevel = (ImageView) view.findViewById(R.id.user_item_level_iv_level);
        mLevelLayout = (RelativeLayout) view.findViewById(R.id.user_item_level_layout);
        mGenderTvState = (TextView) view.findViewById(R.id.user_item_gender_tv_state);
        mGenderLayout = (RelativeLayout) view.findViewById(R.id.user_item_gender_layout);
        mBirthdayTvState = (TextView) view.findViewById(R.id.user_item_birthday_tv_state);
        mBirthdayLayout = (RelativeLayout) view.findViewById(R.id.user_item_birthday_layout);
        mAddressLayout = (RelativeLayout) view.findViewById(R.id.user_item_address_layout);
        mQrLayout = (RelativeLayout) view.findViewById(R.id.user_item_qr_layout);
        mBtnExitLogin = (Button) view.findViewById(R.id.user_btn_exit_login);

        mAvatarLayout.setOnClickListener(this);
        mNameLayout.setOnClickListener(this);
        mNicknameLayout.setOnClickListener(this);
        mBindPhoneLayout.setOnClickListener(this);
        mPayPasswordLayout.setOnClickListener(this);
        mLoginPasswordLayout.setOnClickListener(this);
        mLevelLayout.setOnClickListener(this);
        mGenderLayout.setOnClickListener(this);
        mBirthdayLayout.setOnClickListener(this);
        mAddressLayout.setOnClickListener(this);
        mQrLayout.setOnClickListener(this);
        mBtnExitLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_item_avatar_layout:
                break;
            case R.id.user_item_username_layout:
                break;
            case R.id.user_item_nickname_layout:
                break;
            case R.id.user_item_bindPhone_layout:
                break;
            case R.id.user_item_payPassword_layout:
                break;
            case R.id.user_item_loginPassword_layout:
                break;
            case R.id.user_item_level_layout:
                break;
            case R.id.user_item_gender_layout:
                break;
            case R.id.user_item_birthday_layout:
                break;
            case R.id.user_item_address_layout:
                break;
            case R.id.user_item_qr_layout:
                break;
            case R.id.user_btn_exit_login:
                User.logOut();
                Intent data = new Intent();
                getActivity().setResult(UserProfileActivity.PROFILE_RESULT_CODE,data);
                getActivity().finish();
                break;
        }
    }
}
