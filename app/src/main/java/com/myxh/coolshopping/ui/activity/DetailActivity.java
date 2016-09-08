package com.myxh.coolshopping.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.myxh.coolshopping.R;
import com.myxh.coolshopping.common.AppConstant;
import com.myxh.coolshopping.entity.GoodsDetailInfo;
import com.myxh.coolshopping.network.CallServer;
import com.myxh.coolshopping.network.HttpListener;
import com.myxh.coolshopping.ui.fragment.HomeFragment;
import com.myxh.coolshopping.ui.widget.ObserverScrollView;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, HttpListener<String>,ObserverScrollView.ScrollViewListener {

    private static final int REQUEST_GOOD = 300;
    public static final String DETAIL_INFO = "detailInfo";
    private SimpleDraweeView mProductPhoto;
    private TextView mTvProductName;
    private TextView mTvDescription;
    private TextView mTvBought;
    private LinearLayout mSureLayoutAnytime;
    private LinearLayout mSureLayoutOverdue;
    private LinearLayout mSureLayoutSevenday;
    private TextView mTvMerchantTitle;
    private TextView mMerchantTvAddress;
    private TextView mMerchantTvHours;
    private TextView mMerchantTvDistance;
    private ImageView mMerchantIvCall;
    private WebView mDescWvDescription;
    private RelativeLayout mDescCheckDetailLayout;
    private WebView mDescWvTips;
    private ListView mDescListRecommend;
    private ImageView mTitleIvBack;
    private TextView mTitleTvTitle;
    private ImageView mTitleIvFavorite;
    private ImageView mTitleIvShare;
    private LinearLayout mTitleLayout;
    private TextView mLayoutBuyPrice;
    private TextView mLayoutBuyValue;
    private Button mLayoutBuyBtn;
    private RelativeLayout mLayoutBuy;
    private String mGoodsId;
    private int mGoodsBought;
    private String mSevenRefund;
    private int mTimeRefund;
    private GoodsDetailInfo mDetailInfo;

    private ObserverScrollView mScrollView;
    private int mPhotoHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initData();
        initView();
        setViewWithIntentData();
        initScrollViewListener();
    }

    private void setViewWithIntentData() {
        mTvBought.setText(""+mGoodsBought);
        if (mSevenRefund.equals("1")) {
            mSureLayoutSevenday.setVisibility(View.VISIBLE);
        } else {
            mSureLayoutSevenday.setVisibility(View.INVISIBLE);
        }
        if (mTimeRefund == 1) {
            mSureLayoutAnytime.setVisibility(View.VISIBLE);
            mSureLayoutOverdue.setVisibility(View.VISIBLE);
        } else {
            mSureLayoutAnytime.setVisibility(View.INVISIBLE);
            mSureLayoutOverdue.setVisibility(View.INVISIBLE);
        }
    }

    private void initData() {
        mGoodsId = getIntent().getExtras().getString(HomeFragment.GOODS_ID);
        mGoodsBought = getIntent().getExtras().getInt(HomeFragment.GOODS_BOUGHT);
        mSevenRefund = getIntent().getExtras().getString(HomeFragment.GOODS_SEVEN_REFUND);
        mTimeRefund = getIntent().getExtras().getInt(HomeFragment.GOODS_TIME_REFUND);
        Request<String> request = NoHttp.createStringRequest(AppConstant.BASE_URL+ mGoodsId +".txt", RequestMethod.GET);
        CallServer.getInstance().add(this,REQUEST_GOOD,request,this,true,true);
    }

    /**
     * 设置滚动监听
     */
    private void initScrollViewListener() {
        ViewTreeObserver treeObserver = mProductPhoto.getViewTreeObserver();
        treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mProductPhoto.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mPhotoHeight = mProductPhoto.getHeight();
                mScrollView.setScrollListener(DetailActivity.this);
            }
        });
    }

    private void initView() {
        mProductPhoto = (SimpleDraweeView) findViewById(R.id.detail_product_photo);
        mTvProductName = (TextView) findViewById(R.id.detail_tv_product_name);
        mTvDescription = (TextView) findViewById(R.id.detail_tv_description);
        mTvBought = (TextView) findViewById(R.id.detail_tv_bought);
        mSureLayoutAnytime = (LinearLayout) findViewById(R.id.detail_sure_layout_anytime);
        mSureLayoutOverdue = (LinearLayout) findViewById(R.id.detail_sure_layout_overdue);
        mSureLayoutSevenday = (LinearLayout) findViewById(R.id.detail_sure_layout_sevenday);
        mTvMerchantTitle = (TextView) findViewById(R.id.detail_tv_merchant_title);
        mMerchantTvAddress = (TextView) findViewById(R.id.detail_merchant_tv_address);
        mMerchantTvHours = (TextView) findViewById(R.id.detail_merchant_tv_hours);
        mMerchantTvDistance = (TextView) findViewById(R.id.detail_merchant_tv_distance);
        mMerchantIvCall = (ImageView) findViewById(R.id.detail_merchant_iv_call);
        mDescWvDescription = (WebView) findViewById(R.id.detail_desc_wv_description);
        mDescCheckDetailLayout = (RelativeLayout) findViewById(R.id.detail_desc_check_detail_layout);
        mDescWvTips = (WebView) findViewById(R.id.detail_desc_wv_tips);
        mDescListRecommend = (ListView) findViewById(R.id.detail_desc_list_recommend);
        mTitleIvBack = (ImageView) findViewById(R.id.detail_title_iv_back);
        mTitleTvTitle = (TextView) findViewById(R.id.detail_title_tv_title);
        mTitleIvFavorite = (ImageView) findViewById(R.id.detail_title_iv_favorite);
        mTitleIvShare = (ImageView) findViewById(R.id.detail_title_iv_share);
        mTitleLayout = (LinearLayout) findViewById(R.id.detail_title_layout);
        mLayoutBuyPrice = (TextView) findViewById(R.id.detail_layout_buy_price);
        mLayoutBuyValue = (TextView) findViewById(R.id.detail_layout_buy_value);
        mLayoutBuyBtn = (Button) findViewById(R.id.detail_layout_buy_btn);
        mLayoutBuy = (RelativeLayout) findViewById(R.id.detail_layout_buy);
        mScrollView = (ObserverScrollView) findViewById(R.id.detail_scroll_view);

        mTitleIvBack.setOnClickListener(this);
        mTitleIvFavorite.setOnClickListener(this);
        mTitleIvShare.setOnClickListener(this);
        mProductPhoto.setOnClickListener(this);
        mDescCheckDetailLayout.setOnClickListener(this);
        mLayoutBuyBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_title_iv_back:
                finish();
                break;
            case R.id.detail_title_iv_favorite:

                break;
            case R.id.detail_title_iv_share:

                break;
            case R.id.detail_product_photo:
                Intent intent = new Intent(this,ImageGalleryActivity.class);
                intent.putExtra(DETAIL_INFO,mDetailInfo);
                startActivity(intent);
                break;
            case R.id.detail_desc_check_detail_layout:
                
                break;
            case R.id.detail_layout_buy_btn:

                break;
        }
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        switch (what) {
            case REQUEST_GOOD:
                Gson gson = new Gson();
                mDetailInfo = gson.fromJson(response.get(),GoodsDetailInfo.class);
                //商品名称
                mTvProductName.setText(mDetailInfo.getResult().getProduct());
                mTvMerchantTitle.setText(mDetailInfo.getResult().getProduct());
                //商品照片
                mProductPhoto.setImageURI(Uri.parse(mDetailInfo.getResult().getImages().get(0).getImage()));
                //商品描述
                mTvDescription.setText(mDetailInfo.getResult().getTitle());
                //商品详情
                mDescWvDescription.loadDataWithBaseURL(AppConstant.BASE_URL, mDetailInfo.getResult().getDetails(),
                        "text/html","UTF-8",null);
                //温馨提示
                mDescWvTips.loadDataWithBaseURL(AppConstant.BASE_URL, mDetailInfo.getResult().getNotice(),
                        "text/html","UTF-8",null);
                //团购价
                mLayoutBuyPrice.setText(mDetailInfo.getResult().getPrice());
                //门市价
                mLayoutBuyValue.setText("$"+ mDetailInfo.getResult().getValue());
                mLayoutBuyValue.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//添加删除线
                break;
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }

    @Override
    public void onScroll(ObserverScrollView scrollView, int x, int y, int oldX, int oldY) {
        if (y <= 0) {
            mTitleTvTitle.setVisibility(View.VISIBLE);
            mTitleTvTitle.setText("");
            mTitleTvTitle.setTextColor(Color.argb(0,0,0,0));
            mTitleLayout.setBackgroundColor(Color.argb(0,255,255,255));
        } else if (y >0 && y < mPhotoHeight) {
            float factor = (float) y/mPhotoHeight;
            int alpha = (int) (factor * 255);
            mTitleTvTitle.setVisibility(View.VISIBLE);
            mTitleTvTitle.setText(mDetailInfo.getResult().getProduct());
            mTitleTvTitle.setTextColor(Color.argb(alpha,0,0,0));
            mTitleLayout.setBackgroundColor(Color.argb(alpha,255,255,255));
        } else {
            mTitleTvTitle.setVisibility(View.VISIBLE);
            mTitleTvTitle.setText(mDetailInfo.getResult().getProduct());
            mTitleTvTitle.setTextColor(Color.BLACK);
            mTitleLayout.setBackgroundColor(Color.WHITE);
        }
    }
}
