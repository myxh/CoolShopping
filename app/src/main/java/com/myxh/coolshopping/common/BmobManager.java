package com.myxh.coolshopping.common;

import com.myxh.coolshopping.listener.IBmobListener;
import com.myxh.coolshopping.model.BaseModel;
import com.myxh.coolshopping.model.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by asus on 2016/9/9.
 */
public final class BmobManager {
    private static BmobManager bmobManager;

    private static IBmobListener mListener;

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
                    mListener.onLoginSuccess();
                } else {
                    mListener.onLoginFailure();
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
                    mListener.onLoginSuccess();
                } else {
                    mListener.onLoginFailure();
                }
            }
        });
    }

    public static boolean insertData(BaseModel model) {
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

    public static boolean deleteData(BaseModel model) {
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

    public static boolean updateData(BaseModel model) {
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

    public boolean queryData(BaseModel model, String queryKey, Object queryValue) {
        final boolean[] isSuccess = {false};
        BmobQuery<BaseModel> query = new BmobQuery<>();
        query.addWhereEqualTo(queryKey,queryValue);
        query.findObjects(new FindListener<BaseModel>() {
            @Override
            public void done(List<BaseModel> list, BmobException e) {
                if (e == null) {
                    mListener.onQuerySuccess();
                    isSuccess[0] = true;
                } else {
                    mListener.onQueryFailure();
                    isSuccess[0] = false;
                }
            }
        });
        return isSuccess[0];
    }
}
