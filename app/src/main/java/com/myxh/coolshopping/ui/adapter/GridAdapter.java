package com.myxh.coolshopping.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.entity.HomeGridInfo;

import java.util.List;

/**
 * Created by asus on 2016/8/31.
 */
public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private List<HomeGridInfo> mData;

    public GridAdapter(Context context, List<HomeGridInfo> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_gridview, null);
        ImageView gridIcon = (ImageView) mView.findViewById(R.id.grid_icon);
        TextView gridTitle = (TextView) mView.findViewById(R.id.grid_title);

        gridIcon.setImageResource(mData.get(i).getGridIcon());
        gridTitle.setText(mData.get(i).getGridTitle());
        return mView;
    }
}
