package com.myxh.coolshopping.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myxh.coolshopping.R;
import com.myxh.coolshopping.entity.GoodsInfo;

import java.util.List;

/**
 * Created by asus on 2016/8/31.
 */
public class GoodsListAdapter extends BaseAdapter {

    private Context mContext;
    private List<GoodsInfo.ResultBean.GoodlistBean> mGoodlist;
    private int mHeaderCount;

    public GoodsListAdapter(Context context, List<GoodsInfo.ResultBean.GoodlistBean> goodlist, int headerCount) {
        mContext = context;
        mGoodlist = goodlist;
        mHeaderCount = headerCount;
    }

    @Override
    public int getCount() {
        return mGoodlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mGoodlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_good_list,null);
            holder.goodPhoto = (SimpleDraweeView) convertView.findViewById(R.id.good_photo);
            holder.goodIcon = (ImageView) convertView.findViewById(R.id.good_icon);
            holder.goodAppointmentImg = (ImageView) convertView.findViewById(R.id.good_appointment_img);
            holder.goodLoading = (ProgressBar) convertView.findViewById(R.id.good_loading);
            holder.goodDistance = (TextView) convertView.findViewById(R.id.good_tv_distance);
            holder.goodTitle = (TextView) convertView.findViewById(R.id.good_tv_title);
            holder.goodFreshOrderLayout = (LinearLayout) convertView.findViewById(R.id.good_fresh_order_layout);
            holder.goodDescription = (TextView) convertView.findViewById(R.id.good_tv_description);
            holder.goodCommentScore = (RatingBar) convertView.findViewById(R.id.good_comment_score);
            holder.goodPrice = (TextView) convertView.findViewById(R.id.good_tv_price);
            holder.goodValue = (TextView) convertView.findViewById(R.id.good_tv_value);
            holder.goodValue.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//添加删除线
            holder.goodCount = (TextView) convertView.findViewById(R.id.good_tv_count);
            holder.goodArea = (TextView) convertView.findViewById(R.id.good_tv_area);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.goodPhoto.setImageURI(Uri.parse(mGoodlist.get(i).getImages().get(0).getImage()));
        holder.goodIcon.setVisibility(View.VISIBLE);
        if (mGoodlist.get(i).getIs_appointment() == 1) {
            holder.goodAppointmentImg.setVisibility(View.VISIBLE);
        } else {
            holder.goodAppointmentImg.setVisibility(View.GONE);
        }
        holder.goodLoading.setVisibility(View.GONE);
        holder.goodDistance.setText(mGoodlist.get(i).getDistance());
        holder.goodTitle.setText(mGoodlist.get(i).getProduct());
        if (mGoodlist.get(i).getIs_new().equals("0")) {
            holder.goodFreshOrderLayout.setVisibility(View.GONE);
        } else {
            holder.goodFreshOrderLayout.setVisibility(View.VISIBLE);
        }
        holder.goodDescription.setText(mGoodlist.get(i).getTitle());
        holder.goodCommentScore.setVisibility(View.GONE);
        holder.goodPrice.setText(mGoodlist.get(i).getPrice());
        holder.goodValue.setText(mGoodlist.get(i).getValue());
        holder.goodCount.setText("已售"+mGoodlist.get(i).getBought()+"份");
//        holder.goodArea.setText();
        return convertView;
    }

    static class ViewHolder
    {
        public SimpleDraweeView goodPhoto;
        public ImageView goodIcon;
        public ImageView goodAppointmentImg;
        public ProgressBar goodLoading;

        public TextView goodDistance;
        public TextView goodTitle;
        public LinearLayout goodFreshOrderLayout;
        public TextView goodDescription;
        public RatingBar goodCommentScore;
        public TextView goodPrice;
        public TextView goodValue;
        public TextView goodCount;
        public TextView goodArea;
    }
}
