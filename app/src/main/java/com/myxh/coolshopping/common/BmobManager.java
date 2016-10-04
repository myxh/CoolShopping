package com.myxh.coolshopping.common;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.listener.IBmobListener;
import com.myxh.coolshopping.model.BaseModel;
import com.myxh.coolshopping.model.FavorModel;
import com.myxh.coolshopping.model.User;
import com.myxh.coolshopping.util.DialogUtil;
import com.myxh.coolshopping.util.ToastUtil;

import java.util.List;

import c.b.BP;
import c.b.PListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static android.R.attr.name;

/**
 * Created by asus on 2016/9/9.
 */
public final class BmobManager {
    private static final String TAG = BmobManager.class.getSimpleName();
    private static BmobManager bmobManager;

    private static IBmobListener mListener;

    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_DESC = "product_desc";
    public static final String PRODUCT_PRICE = "product_price";

    public static BmobManager getInstance(IBmobListener listener) {
        mListener = listener;
        if (bmobManager == null) {
            synchronized (BmobManager.class) {
                if (bmobManager == null) {
                    bmobManager = new BmobManager();
                }
            }
        }
        return bmobManager;
    }

    /**
     * 发送短信验证码
     * @param phoneNumber
     */
    public void sendMsgCode(String phoneNumber) {
        BmobSMS.requestSMSCode(phoneNumber,"coolgou", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId,BmobException ex) {
                if(ex==null){//验证码发送成功
                    mListener.onMsgSendSuccess();
                } else {
                    mListener.onMsgSendFailure();
                }
            }
        });
    }

    /**
     * 手机验证码一键登录
     * @param phoneNumber
     * @param code
     */
    public void loginByMsgCode(String phoneNumber, String code) {
        BmobUser.loginBySMSCode(phoneNumber, code, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (user != null) {
                    if (mListener != null) {
                        mListener.onLoginSuccess();
                    }
                } else {
                    if (mListener != null) {
                        mListener.onLoginFailure();
                    }
                }
            }
        });
    }

    /**
     * 手机验证码一键注册或登录
     * @param phoneNumber
     * @param code
     */
    public void signOrLoginByMsgCode(String phoneNumber, String code) {
        BmobUser.signOrLoginByMobilePhone(phoneNumber, code, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(user!=null){
                    if (mListener != null) {
                        mListener.onLoginSuccess();
                    }
                } else {
                    if (mListener != null) {
                        mListener.onLoginFailure();
                    }
                }
            }
        });
    }

    /**
     * 用户注册
     * @param phoneNumber
     * @param code
     * @param password
     */
    public void signUp(String phoneNumber, String code, String password) {
        User user = new User();
        user.setMobilePhoneNumber(phoneNumber);
        user.setUsername(phoneNumber);
        user.setPassword(password);
        user.setSex("男");
        user.signOrLogin(code, new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    mListener.onSignUpSuccess(user);
                } else {
                    mListener.onSignUpFailure(e);
                }
            }
        });
    }

    /**
     * 使用用户名密码登录
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e==null) {
                    mListener.onLoginSuccess();
                } else {
                    mListener.onLoginFailure();
                }
            }
        });
    }


    /**
     * 插入数据
     * @param model
     * @return
     */
    public static<T extends BaseModel> boolean insertData(T model) {
        final boolean[] isSuccess = {false};
        model.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    isSuccess[0] = true;
                } else {
                    isSuccess[0] = false;
                }
            }
        });
        return isSuccess[0];
    }

    /**
     * 删除数据
     * @param model
     * @return
     */
    public static<T extends BaseModel> boolean deleteData(T model) {
        final boolean[] isSuccess = {false};
        model.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    isSuccess[0] = true;
                } else {
                    isSuccess[0] = false;
                }
            }
        });
        return isSuccess[0];
    }

    /**
     * 更新数据
     * @param model
     * @return
     */
    public static<T extends BaseModel> boolean updateData(T model) {
        final boolean[] isSuccess = {false};
        model.update(model.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    isSuccess[0] = true;
                } else {
                    isSuccess[0] = false;
                }
            }
        });
        return isSuccess[0];
    }

    /**
     * 查询数据
     * @param queryKey
     * @param queryValue
     */
    public<U extends BaseModel> void queryData(String queryKey, Object queryValue) {
        BmobQuery<U> query = new BmobQuery<>();
//        Log.i(TAG, "queryData: T---------"+model.getClass().getSimpleName());
        query.addWhereEqualTo(queryKey,queryValue);
        query.findObjects(new FindListener<U>() {
            @Override
            public void done(List<U> list, BmobException e) {
                if (e == null) {
                    if (mListener != null) {
                        mListener.onQuerySuccess(list);
                    }
                } else {
                    if (mListener != null) {
                        mListener.onQueryFailure(e);
                    }
                }
            }
        });
    }

    /**
     * 查询我的收藏
     * @param queryKey
     * @param queryValue
     */
    public void queryFavorData(String queryKey, Object queryValue) {
        BmobQuery<FavorModel> query = new BmobQuery<>();
        query.addWhereEqualTo(queryKey,queryValue);
        query.findObjects(new FindListener<FavorModel>() {
            @Override
            public void done(List<FavorModel> list, BmobException e) {
                if (e == null) {
                    if (mListener != null) {
                        mListener.onQuerySuccess(list);
                    }
                } else {
                    if (mListener != null) {
                        mListener.onQueryFailure(e);
                    }
                }
            }
        });
    }

    /**
     * 调用第三方支付
     * @param appContext
     * @param alipayOrWechatPay true为支付宝支付，false为微信支付
     * @param productInfo 商品信息
     */
    public static void pay(final Context appContext, final boolean alipayOrWechatPay, Bundle productInfo) {
        DialogUtil.showDialog(appContext, "正在获取订单...");

        String productName = productInfo.getString(PRODUCT_NAME, "商品名");
        String productDesc = productInfo.getString(PRODUCT_DESC, "商品详情");
        double productPrice = productInfo.getDouble(PRODUCT_PRICE, 0.01);

        BP.pay(productName, productDesc, productPrice, alipayOrWechatPay, new PListener() {

            // 因为网络等原因,支付结果未知(小概率事件),出于保险起见稍后手动查询
            @Override
            public void unknow() {
                ToastUtil.show(appContext,appContext.getString(R.string.pay_result_unknow));
                DialogUtil.hideDialog();
            }

            // 支付成功,如果金额较大请手动查询确认
            @Override
            public void succeed() {
                ToastUtil.show(appContext,appContext.getString(R.string.pay_success));
                DialogUtil.hideDialog();
            }

            // 无论成功与否,返回订单号
            @Override
            public void orderId(String orderId) {
                // 此处应该保存订单号,比如保存进数据库等,以便以后查询
//                order.setText(orderId);
                DialogUtil.showDialog(appContext,appContext.getString(R.string.get_order_success));
            }

            // 支付失败,原因可能是用户中断支付操作,也可能是网络原因
            @Override
            public void fail(int code, String reason) {

                // 当code为-2,意味着用户中断了操作
                // code为-3意味着没有安装BmobPlugin插件
                if (code == -3) {
                    ToastUtil.show(appContext,appContext.getString(R.string.check_no_plugin));
                    CoolApplication.getAppContext().installBmobPayPlugin(AppConstant.PAY_PLUGIN_NAME);
                } else {
                    ToastUtil.show(appContext,appContext.getString(R.string.pay_interrupt)
                            +"\ncode:"+code
                            +"\nreason:"+reason);
                }
                DialogUtil.hideDialog();
            }
        });
    }

}
