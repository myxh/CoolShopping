package com.myxh.coolshopping.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myxh.coolshopping.R;
import com.myxh.coolshopping.model.FavorModel;

import java.util.List;

/**
 * Created by asus on 2016/9/11.
 */
public class FavorListAdapter extends CommonAdapter<FavorModel> {
    public FavorListAdapter(Context context, List<FavorModel> dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(getContext(), convertView, R.layout.item_good_list, parent);
        SimpleDraweeView goodsPhoto = holder.getView(R.id.good_photo);
        TextView goodsProduct = holder.getView(R.id.good_tv_title);
        TextView goodsDesc = holder.getView(R.id.good_tv_description);
        TextView goodsPrice = holder.getView(R.id.good_tv_price);
        TextView goodsValue = holder.getView(R.id.good_tv_value);

        goodsPhoto.setImageURI(Uri.parse(getDataList().get(position).getImageUrl()));
        goodsProduct.setText(getDataList().get(position).getProduct());
        goodsDesc.setText(getDataList().get(position).getDescription());
        goodsPrice.setText(getDataList().get(position).getPrice());
        goodsValue.setText(getDataList().get(position).getValue());
        goodsValue.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//添加删除线
        return holder.getConvertView();
    }
}
