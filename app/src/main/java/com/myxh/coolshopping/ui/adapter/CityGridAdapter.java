package com.myxh.coolshopping.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myxh.coolshopping.R;

import java.util.List;

/**
 * Created by asus on 2016/10/2.
 */

public class CityGridAdapter extends CommonAdapter<String> {

    public CityGridAdapter(Context context, List<String> dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(getContext(),convertView, R.layout.item_city_grid_item,parent);
        TextView tvCity = holder.getView(R.id.item_city_grid_tv);

        tvCity.setText(getDataList().get(position));
        return holder.getConvertView();
    }
}
