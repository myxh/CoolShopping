package com.myxh.coolshopping.ui.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by asus on 2016/9/2.
 */
public class ViewHolder {

    //效率高，key值只能是Integer
    private SparseArray<View> mViews;
    private View mConvertView;

    public ViewHolder(Context context, @LayoutRes int layoutID, ViewGroup parent) {
        mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutID,parent,false);
        mConvertView.setTag(this);
    }

    /**
     * 获取ViewHolder
     * @param context
     * @param convertView
     * @param layoutID
     * @param parent
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 @LayoutRes int layoutID, ViewGroup parent) {
        if (convertView == null) {
            return new ViewHolder(context, layoutID, parent);
        }
        return (ViewHolder) convertView.getTag();
    }

    /**
     * 获取子控件
     * @param viewID
     * @param <T>
     * @return
     */
    public <T extends View> T getView(@IdRes int viewID) {
        View view = mViews.get(viewID);
        if (view == null) {
            view = mConvertView.findViewById(viewID);
            mViews.put(viewID,view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }
}
