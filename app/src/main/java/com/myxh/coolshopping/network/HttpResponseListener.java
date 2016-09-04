package com.myxh.coolshopping.network;

import android.content.Context;
import android.content.DialogInterface;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.ui.widget.WaitDialog;
import com.myxh.coolshopping.util.ToastUtil;
import com.yolanda.nohttp.error.NetworkError;
import com.yolanda.nohttp.error.NotFoundCacheError;
import com.yolanda.nohttp.error.ParseError;
import com.yolanda.nohttp.error.TimeoutError;
import com.yolanda.nohttp.error.URLError;
import com.yolanda.nohttp.error.UnKnownHostError;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.net.ProtocolException;

/**
 * Created by asus on 2016/8/30.
 */
public class HttpResponseListener<T> implements OnResponseListener<T> {

    private Context mContext;
    private Request<T> mRequest;
    private HttpListener<T> mHttpListener;
    private WaitDialog mWaitDialog;
    private boolean isLoading;

    /**
     * 构造请求监听器
     * @param context       上下文环境
     * @param request       Http请求
     * @param httpListener  Http请求监听
     * @param canCancel     进度条是否可以取消
     * @param isLoading     是否正在加载
     */
    public HttpResponseListener(Context context, Request<T> request, HttpListener<T> httpListener,
                                boolean canCancel, boolean isLoading) {
        mContext = context;
        mRequest = request;
        mHttpListener = httpListener;
        if (context != null && isLoading) {
            mWaitDialog = new WaitDialog(context);
            mWaitDialog.setCancelable(canCancel);
            mWaitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    mWaitDialog.cancel();
                }
            });
        }
    }

    @Override
    public void onStart(int what) {
        if (mWaitDialog != null && !mWaitDialog.isShowing() && isLoading) {
            mWaitDialog.show();
        }
    }

    @Override
    public void onSucceed(int what, Response<T> response) {
        if (mContext != null) {
            mHttpListener.onSucceed(what, response);
        }
    }

    @Override
    public void onFailed(int what, Response<T> response) {
        Exception exception = response.getException();
        if (exception instanceof NetworkError) {// 网络不好
            ToastUtil.show(mContext, R.string.error_please_check_network);
        } else if (exception instanceof TimeoutError) {// 请求超时
            ToastUtil.show(mContext, R.string.error_timeout);
        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
            ToastUtil.show(mContext, R.string.error_not_found_server);
        } else if (exception instanceof URLError) {// URL是错的
            ToastUtil.show(mContext, R.string.error_url_error);
        } else if (exception instanceof NotFoundCacheError) {
            // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
            ToastUtil.show(mContext, R.string.error_not_found_cache);
        } else if (exception instanceof ProtocolException) {
            ToastUtil.show(mContext, R.string.error_system_unsupport_method);
        } else if (exception instanceof ParseError) {
            ToastUtil.show(mContext, R.string.error_parse_data_error);
        } else {
            ToastUtil.show(mContext, R.string.error_unknow);
        }
        if (mContext != null) {
            mHttpListener.onFailed(what, response);
        }
    }

    @Override
    public void onFinish(int what) {
        if (mContext!=null && mWaitDialog.isShowing()) {
            mWaitDialog.dismiss();
        }
    }
}
