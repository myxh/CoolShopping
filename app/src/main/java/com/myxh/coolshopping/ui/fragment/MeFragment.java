package com.myxh.coolshopping.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myxh.coolshopping.R;
import com.myxh.coolshopping.model.User;
import com.myxh.coolshopping.ui.activity.CartActivity;
import com.myxh.coolshopping.ui.activity.CollectActivity;
import com.myxh.coolshopping.ui.activity.CouponsActivity;
import com.myxh.coolshopping.ui.activity.HistoryActivity;
import com.myxh.coolshopping.ui.activity.LoginActivity;
import com.myxh.coolshopping.ui.activity.LotteryActivity;
import com.myxh.coolshopping.ui.activity.PaidActivity;
import com.myxh.coolshopping.ui.activity.TreasureActivity;
import com.myxh.coolshopping.ui.activity.UnpaidActivity;
import com.myxh.coolshopping.ui.activity.UserProfileActivity;
import com.myxh.coolshopping.ui.base.BaseFragment;
import com.myxh.coolshopping.util.ToastUtil;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

/**
 * Created by asus on 2016/8/27.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    private static final int LOGIN_REQUEST_CODE = 100;
    private static final int PROFILE_REQUEST_CODE = 101;
    private static final int SCAN_QR_REQUEST = 3001;
    private SimpleDraweeView mLoginIvHead;
    private TextView mLoginTvUsername;
    private ImageView mLoginIvLevel;
    private TextView mLoginTvBalance;
    private ImageView mLoginIvArrowRight;
    private RelativeLayout mLoginLayout;
    private Button mNologinBtnLogin;
    private LinearLayout mNologinLayout;
    private TextView mUserTvCart;
    private TextView mUserTvFavorite;
    private TextView mUserTvHistory;
    private TextView mItemUnpaidTvCount;
    private RelativeLayout mItemUnpaidLayout;
    private TextView mItemPaidTvCount;
    private TextView mItemPaidTvUncommentCount;
    private RelativeLayout mItemPaidOrderLayout;
    private TextView mItemLotteryTvCount;
    private RelativeLayout mItemLotteryLayout;
    private TextView mItemTreasureTvCount;
    private RelativeLayout mItemTreasureLayout;
    private LinearLayout mUserOrderLayout;
    private TextView mItemBankcardTvComplete;
    private RelativeLayout mItemBankcardLayout;
    private TextView mItemCouponsTvCount;
    private RelativeLayout mItemCouponsLayout;
    private TextView mItemRecommendTvNew;
    private RelativeLayout mItemRecommendLayout;
    private RelativeLayout mItemQrLayout;
    private LinearLayout mLoginProfileLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        initView(view);
        initUserLayout();
        return view;
    }

    private void initUserLayout() {
        User user = User.getCurrentUser(User.class);
        if (user != null) {
            mLoginLayout.setVisibility(View.VISIBLE);
            mNologinLayout.setVisibility(View.GONE);
            mItemBankcardLayout.setVisibility(View.VISIBLE);
            mItemCouponsLayout.setVisibility(View.VISIBLE);

            mLoginTvUsername.setText(user.getUsername());
            mLoginTvBalance.setText(user.getBalance() + "");
            if (user.getHeadIcon() == null) {
                mLoginIvHead.setImageResource(R.mipmap.user_head);
            } else {
                mLoginIvHead.setImageURI(Uri.parse(user.getHeadIcon().getFileUrl()));
            }
        } else {
            mLoginLayout.setVisibility(View.GONE);
            mNologinLayout.setVisibility(View.VISIBLE);
            mItemBankcardLayout.setVisibility(View.GONE);
            mItemCouponsLayout.setVisibility(View.GONE);
        }
    }

    private void initView(View view) {
        mLoginIvHead = (SimpleDraweeView) view.findViewById(R.id.me_login_iv_head);
        mLoginTvUsername = (TextView) view.findViewById(R.id.me_login_tv_username);
        mLoginIvLevel = (ImageView) view.findViewById(R.id.me_login_iv_level);
        mLoginTvBalance = (TextView) view.findViewById(R.id.me_login_tv_balance);
        mLoginIvArrowRight = (ImageView) view.findViewById(R.id.me_login_iv_arrow_right);
        mLoginLayout = (RelativeLayout) view.findViewById(R.id.me_login_layout);
        mNologinBtnLogin = (Button) view.findViewById(R.id.me_nologin_btn_login);
        mNologinLayout = (LinearLayout) view.findViewById(R.id.me_nologin_layout);
        mUserTvCart = (TextView) view.findViewById(R.id.me_user_tv_cart);
        mUserTvFavorite = (TextView) view.findViewById(R.id.me_user_tv_favorite);
        mUserTvHistory = (TextView) view.findViewById(R.id.me_user_tv_history);
        mItemUnpaidTvCount = (TextView) view.findViewById(R.id.me_item_unpaid_tv_count);
        mItemUnpaidLayout = (RelativeLayout) view.findViewById(R.id.me_item_unpaid_layout);
        mItemPaidTvCount = (TextView) view.findViewById(R.id.me_item_paid_tv_count);
        mItemPaidTvUncommentCount = (TextView) view.findViewById(R.id.me_item_paid_tv_uncomment_count);
        mItemPaidOrderLayout = (RelativeLayout) view.findViewById(R.id.me_item_paid_order_layout);
        mItemLotteryTvCount = (TextView) view.findViewById(R.id.me_item_lottery_tv_count);
        mItemLotteryLayout = (RelativeLayout) view.findViewById(R.id.me_item_lottery_layout);
        mItemTreasureTvCount = (TextView) view.findViewById(R.id.me_item_treasure_tv_count);
        mItemTreasureLayout = (RelativeLayout) view.findViewById(R.id.me_item_treasure_layout);
        mUserOrderLayout = (LinearLayout) view.findViewById(R.id.me_user_order_layout);
        mItemBankcardTvComplete = (TextView) view.findViewById(R.id.me_item_bankcard_tv_complete);
        mItemBankcardLayout = (RelativeLayout) view.findViewById(R.id.me_item_bankcard_layout);
        mItemCouponsTvCount = (TextView) view.findViewById(R.id.me_item_coupons_tv_count);
        mItemCouponsLayout = (RelativeLayout) view.findViewById(R.id.me_item_coupons_layout);
        mItemRecommendTvNew = (TextView) view.findViewById(R.id.me_item_recommend_tv_new);
        mItemRecommendLayout = (RelativeLayout) view.findViewById(R.id.me_item_recommend_layout);
        mItemQrLayout = (RelativeLayout) view.findViewById(R.id.me_item_qr_layout);
        mLoginProfileLayout = (LinearLayout) view.findViewById(R.id.me_login_profile_layout);

        mLoginIvHead.setOnClickListener(this);
        mLoginIvArrowRight.setOnClickListener(this);
        mNologinBtnLogin.setOnClickListener(this);
        mUserTvCart.setOnClickListener(this);
        mUserTvFavorite.setOnClickListener(this);
        mUserTvHistory.setOnClickListener(this);
        mItemUnpaidLayout.setOnClickListener(this);
        mItemPaidOrderLayout.setOnClickListener(this);
        mItemLotteryLayout.setOnClickListener(this);
        mItemTreasureLayout.setOnClickListener(this);
        mItemBankcardLayout.setOnClickListener(this);
        mItemCouponsLayout.setOnClickListener(this);
        mItemRecommendLayout.setOnClickListener(this);
        mItemQrLayout.setOnClickListener(this);
        mLoginProfileLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_login_iv_head:

                break;
            case R.id.me_login_profile_layout:
                Intent profileIntent = new Intent(getActivity(), UserProfileActivity.class);
                startActivityForResult(profileIntent, PROFILE_REQUEST_CODE);
//                openActivity(UserProfileActivity.class);
                break;
            case R.id.me_login_iv_arrow_right:

                break;
            case R.id.me_nologin_btn_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
//                openActivity(LoginActivity.class);
                break;
            case R.id.me_user_tv_cart:
                if (User.getCurrentUser() != null) {
                    openActivity(CartActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
                break;
            case R.id.me_user_tv_favorite:
                if (User.getCurrentUser() != null) {
                    openActivity(CollectActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
                break;
            case R.id.me_user_tv_history:
                if (User.getCurrentUser() != null) {
                    openActivity(HistoryActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
                break;
            case R.id.me_item_unpaid_layout:
                if (User.getCurrentUser() != null) {
                    openActivity(UnpaidActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
                break;
            case R.id.me_item_paid_order_layout:
                if (User.getCurrentUser() != null) {
                    openActivity(PaidActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
                break;
            case R.id.me_item_lottery_layout:
                if (User.getCurrentUser() != null) {
                    openActivity(LotteryActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
                break;
            case R.id.me_item_treasure_layout:
                if (User.getCurrentUser() != null) {
                    openActivity(TreasureActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
                break;
            case R.id.me_item_bankcard_layout:

                break;
            case R.id.me_item_coupons_layout:
                openActivity(CouponsActivity.class);
                break;
            case R.id.me_item_recommend_layout:

                break;
            case R.id.me_item_qr_layout:
                Intent qrIntent = new Intent(getActivity(), CaptureActivity.class);
                getActivity().startActivityForResult(qrIntent,SCAN_QR_REQUEST);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == LoginActivity.LOGIN_RESULT_CODE) {
            initUserLayout();
        } else if (requestCode == PROFILE_REQUEST_CODE && resultCode == UserProfileActivity.PROFILE_RESULT_CODE) {
            initUserLayout();
        } else if (requestCode == SCAN_QR_REQUEST) {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
