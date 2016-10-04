package com.myxh.coolshopping.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.myxh.coolshopping.common.BmobManager;
import com.myxh.coolshopping.common.CoolApplication;
import com.myxh.coolshopping.entity.GoodsDetailInfo;
import com.myxh.coolshopping.listener.BmobQueryCallback;
import com.myxh.coolshopping.model.BaseModel;
import com.myxh.coolshopping.model.FavorModel;
import com.myxh.coolshopping.model.User;
import com.myxh.coolshopping.network.CallServer;
import com.myxh.coolshopping.network.HttpListener;
import com.myxh.coolshopping.ui.base.BaseActivity;
import com.myxh.coolshopping.ui.fragment.HomeFragment;
import com.myxh.coolshopping.ui.widget.ObserverScrollView;
import com.myxh.coolshopping.util.ToastUtil;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class DetailActivity extends BaseActivity implements View.OnClickListener, HttpListener<String>,ObserverScrollView.ScrollViewListener {

    private static final int REQUEST_GOOD = 300;
    public static final String DETAIL_INFO = "detailInfo";
    private static final String TAG = DetailActivity.class.getSimpleName();
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

    //网络是否请求成功
    private boolean isRequestSuccess = false;

    //是否收藏
    private boolean isFavor = false;
    //收藏按钮是否点击
    private boolean isClickFavor = false;
    private FavorModel mFavoredData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initData();
        initView();
        setViewWithIntentData();
        initScrollViewListener();
        showIsFavor();
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
                isClickFavor = true;
                showIsFavor();
                break;
            case R.id.detail_title_iv_share:
                showShare();
                break;
            case R.id.detail_product_photo:
                Intent intent = new Intent(this,ImageGalleryActivity.class);
                intent.putExtra(DETAIL_INFO,mDetailInfo);
                startActivity(intent);
                break;
            case R.id.detail_desc_check_detail_layout:
                
                break;
            case R.id.detail_layout_buy_btn:
                BmobManager.pay(CoolApplication.getAppContext(),false,new Bundle());
                break;
        }
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        isRequestSuccess = true;
        switch (what) {
            case REQUEST_GOOD:
                Gson gson = new Gson();
                mDetailInfo = gson.fromJson(response.get(),GoodsDetailInfo.class);
                //商品名称
                mTvProductName.setText(mDetailInfo.getResult().getProduct());
                //商家
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

    /**
     * 滑动监听
     * @param scrollView
     * @param x
     * @param y
     * @param oldX
     * @param oldY
     */
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

    /**
     * 收藏点击
     */
    private void showIsFavor() {
        final User user = User.getCurrentUser(User.class);
        if (user == null) {
            ToastUtil.show(this, R.string.me_nologin_not_login);
        } else {
            Log.i(TAG, "favorClick: 正在收藏。。。");
            BmobManager.getInstance(new BmobQueryCallback() {
                @Override
                public void onQuerySuccess(List<? extends BaseModel> dataList) {
                    Log.i(TAG, "onQuerySuccess: 收藏查询成功");
                    List<FavorModel> favorList = (List<FavorModel>) dataList;
                    if (favorList == null || favorList.size() == 0) {
                        Log.i(TAG, "onQuerySuccess: 数据表为空");
                        if (isClickFavor) {
                            if (isRequestSuccess){
                                Log.i(TAG, "onQuerySuccess: 请求数据成功-----正在收藏。。。。");
                                FavorModel model = getFavorData(user);
                                BmobManager.insertData(model);
                                ToastUtil.show(DetailActivity.this,R.string.collect_success);
                                isFavor = true;
                            } else {
                                isFavor = false;
                                ToastUtil.show(DetailActivity.this,R.string.collect_failed);
                                Log.i(TAG, "onQuerySuccess: 请求数据失败--------");
                            }
                            isClickFavor = false;
                        } else {
                            isFavor = false;
                        }
                    } else {
                        Log.i(TAG, "onQuerySuccess: 数据表不为空");

                        if (isClickFavor) {
                            isFavor = favorDataQuery(favorList);
                            if (isFavor) {
                                Log.i(TAG, "onQuerySuccess: 点击收藏------已经收藏---------取消收藏");
                                FavorModel model = new FavorModel();
                                model.setObjectId(mFavoredData.getObjectId());
                                BmobManager.deleteData(model);
                                ToastUtil.show(DetailActivity.this,R.string.uncollect_success);
                                isFavor = false;
                            } else {
                                if (isRequestSuccess) {
                                    Log.i(TAG, "onQuerySuccess: 点击收藏------没有收藏---------收藏");
                                    FavorModel model = getFavorData(user);
                                    BmobManager.insertData(model);
                                    ToastUtil.show(DetailActivity.this,R.string.collect_success);
                                    isFavor = true;
                                } else {
                                    isFavor = false;
                                }
                            }
                            isClickFavor = false;
                        } else {
                            isFavor = favorDataQuery(favorList);
                        }
                    }
                    if (isFavor) {
                        mTitleIvFavorite.setImageResource(R.mipmap.icon_collected_black);
                    } else {
                        mTitleIvFavorite.setImageResource(R.mipmap.icon_uncollect_black);
                    }
                }

                @Override
                public void onQueryFailure(BmobException e) {
                    if(e.getErrorCode() == 101) {//数据表不存在，创建表
                        if (isClickFavor) {
                            if (isRequestSuccess) {
                                FavorModel model = getFavorData(user);
                                BmobManager.insertData(model);
                                mTitleIvFavorite.setImageResource(R.mipmap.icon_collected_black);
                                ToastUtil.show(DetailActivity.this,R.string.collect_success);
                                isFavor = true;
                            } else {
                                ToastUtil.show(DetailActivity.this,R.string.collect_failed);
                            }
                            isClickFavor = false;
                        }
                    } else {
                        Log.i(TAG, "onQueryFailure: 错误码"+e.getErrorCode());
                        ToastUtil.show(DetailActivity.this,R.string.collect_failed);
                    }
                }
            }).queryFavorData(AppConstant.KEY_USER_ID,user.getObjectId());


        }
    }

    /**
     * 查询收藏列表是否已经收藏
     * @param favorList
     */
    private boolean favorDataQuery(List<FavorModel> favorList) {
        int i;
        for (i = 0; i < favorList.size(); i++) {
            if (favorList.get(i).getGoodsId().equals(mGoodsId)) {
                Log.i(TAG, "favorDataQuery: 查询到了-----已经收藏");
                mFavoredData = favorList.get(i);
                return true;
            }
        }

        if (i == favorList.size()-1) {
            Log.i(TAG, "favorDataQuery: 没有收藏商品-----添加收藏");
            return false;
        }
        return false;
    }

    /**
     * 获取收藏数据
     * @param user
     * @return
     */
    private FavorModel getFavorData(User user) {
        FavorModel model = new FavorModel();
        model.setUserId(user.getObjectId());
        model.setGoodsId(mGoodsId);
        model.setBought(mGoodsBought);
        model.setImageUrl(mDetailInfo.getResult().getImages().get(0).getImage());
        model.setProduct(mDetailInfo.getResult().getProduct());
        model.setDescription(mDetailInfo.getResult().getTitle());
        model.setPrice(mDetailInfo.getResult().getPrice());
        model.setValue(mDetailInfo.getResult().getValue());
        model.setSevenRefund(mSevenRefund);
        model.setTimeRefund(mTimeRefund);
        return model;
    }

    /**
     * 社会化分享
     */
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        if (isRequestSuccess) {
            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
            oks.setTitle(mDetailInfo.getResult().getProduct());
            // titleUrl是标题的网络链接，QQ和QQ空间等使用
            oks.setTitleUrl(mDetailInfo.getResult().getImages().get(0).getImage());
            // text是分享文本，所有平台都需要这个字段
            oks.setText(mDetailInfo.getResult().getTitle());
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
            // url仅在微信（包括好友和朋友圈）中使用
            oks.setUrl("http://sharesdk.cn");
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
            oks.setComment("我是测试评论文本");
            // site是分享此内容的网站名称，仅在QQ空间使用
            oks.setSite(getString(R.string.app_name));
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
            oks.setSiteUrl(mDetailInfo.getResult().getImages().get(0).getImage());
            // 启动分享GUI
            oks.show(this);
        }
    }
}
