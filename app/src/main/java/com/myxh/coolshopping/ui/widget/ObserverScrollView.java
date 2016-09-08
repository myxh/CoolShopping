package com.myxh.coolshopping.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by asus on 2016/9/7.
 */
public class ObserverScrollView extends ScrollView {

    public void setScrollListener(ScrollViewListener listener) {
        mListener = listener;
    }

    private ScrollViewListener mListener;

    public interface ScrollViewListener {
        public void onScroll(ObserverScrollView scrollView, int x, int y, int oldX, int oldY);
    }

    public ObserverScrollView(Context context) {
        super(context);
    }

    public ObserverScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObserverScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener != null) {
            mListener.onScroll(this,l,t,oldl,oldt);
        }
    }
}
