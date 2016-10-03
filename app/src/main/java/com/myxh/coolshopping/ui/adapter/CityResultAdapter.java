package com.myxh.coolshopping.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.model.City;

import java.util.List;

/**
 * Created by asus on 2016/10/3.
 */

public class CityResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<City> mResultData;

    public CityResultAdapter(Context context, List<City> resultData) {
        mContext = context;
        mResultData = resultData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_result_city,parent);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).tvCityName.setText(mResultData.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCityName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvCityName = (TextView) itemView.findViewById(R.id.item_result_city_tv_city);
        }
    }
}
