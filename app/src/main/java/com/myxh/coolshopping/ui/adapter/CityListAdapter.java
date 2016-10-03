package com.myxh.coolshopping.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.myxh.coolshopping.R;
import com.myxh.coolshopping.common.CoolApplication;
import com.myxh.coolshopping.model.City;
import com.myxh.coolshopping.ui.widget.NoScrollGridView;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by asus on 2016/9/30.
 */

public class CityListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> recentCityList;
    private List<String> hotCityList;
    private List<City> allCityList;
    public HashMap<String,Integer> alphaIndexer;
    private String[] sections;

    private String currentCity;
    private boolean isNeedFresh;


    private Context mContext;
    private static final int VIEW_TYPE_NUM = 5;

    private CityGridAdapter mGridAdapter;

    private boolean isNeedRefresh;
    private TextView tvLocate;
    private TextView tvCurrentLocateCity;
    private TextView pbLocate;

    private OnCityClickListener mOnCityClickListener;

    public void setOnCityClickListener(OnCityClickListener onCityClickListener) {
        mOnCityClickListener = onCityClickListener;
    }

    public CityListAdapter(Context context, List<String> recentCityList, List<String> hotCityList, List<City> allCityList) {
        mContext = context;
        this.recentCityList = recentCityList;
        this.hotCityList = hotCityList;
        this.allCityList = allCityList;

        alphaIndexer = new HashMap<>();
        sections = new String[allCityList.size()];

        for (int i = 0; i < this.allCityList.size(); i++) {
            String currentStr = getAlpha(this.allCityList.get(i).getPinyin());
            String previewStr = (i - 1) >= 0 ? getAlpha(this.allCityList.get(i - 1).getPinyin()) : " ";
            if (!previewStr.equals(currentStr)) {
                String name = getAlpha(this.allCityList.get(i).getPinyin());
                alphaIndexer.put(name, i);
                sections[i] = name;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0:
                View locationCity = LayoutInflater.from(mContext).inflate(R.layout.item_location_city,null);
                ItemLocationCityHolder locationCityHolder = new ItemLocationCityHolder(locationCity);
                return locationCityHolder;
            case 1:
                View recentCity = LayoutInflater.from(mContext).inflate(R.layout.item_recent_city,null);
                ItemRecentCityHolder recentCityHolder = new ItemRecentCityHolder(recentCity);
                return recentCityHolder;
            case 2:
                View hotCity = LayoutInflater.from(mContext).inflate(R.layout.item_recent_city,null);
                ItemRecentCityHolder hotCityHolder = new ItemRecentCityHolder(hotCity);
                return hotCityHolder;
            case 3:
                View allCity = LayoutInflater.from(mContext).inflate(R.layout.item_all_city,null);
                ItemAllCityHolder allCityHolder = new ItemAllCityHolder(allCity);
                return allCityHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ItemLocationCityHolder locationCityHolder = (ItemLocationCityHolder) holder;
                final List<BDLocation> locations = CoolApplication.getAppContext().getLocations();
                if (locations == null || locations.size() <= 0) {
//                }
//                final String city = CoolApplication.getAppContext().getLocations().get(0).getCity();
//                if (city == null) {
                    locationCityHolder.tvCity.setVisibility(View.GONE);
                    locationCityHolder.tvLocation.setVisibility(View.GONE);
                    locationCityHolder.tvFailure.setVisibility(View.VISIBLE);
                } else {
                    String cityLocation = locations.get(0).getCity();
                    final String cityStr = cityLocation.substring(0,cityLocation.length()-1);
                    locationCityHolder.tvCity.setVisibility(View.VISIBLE);
                    locationCityHolder.tvLocation.setVisibility(View.GONE);
                    locationCityHolder.tvFailure.setVisibility(View.GONE);
                    locationCityHolder.tvCity.setText(cityStr);
                    locationCityHolder.tvCity.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnCityClickListener != null) {
                                mOnCityClickListener.onCityClick(cityStr);
                            }
                        }
                    });
                }
                break;
            case 1:
                ItemRecentCityHolder recentCityHolder = (ItemRecentCityHolder) holder;
                recentCityHolder.tvTitle.setText(R.string.recent_city);
                recentCityHolder.mNoScrollGridView.setAdapter(new CityGridAdapter(mContext,recentCityList));
                recentCityHolder.mNoScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (mOnCityClickListener != null) {
                            mOnCityClickListener.onCityClick(recentCityList.get(position));
                        }
                    }
                });
                break;
            case 2:
                ItemRecentCityHolder hotCityHolder = (ItemRecentCityHolder) holder;
                hotCityHolder.tvTitle.setText(R.string.hot_city);
                hotCityHolder.mNoScrollGridView.setAdapter(new CityGridAdapter(mContext,hotCityList));
                hotCityHolder.mNoScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (mOnCityClickListener != null) {
                            mOnCityClickListener.onCityClick(hotCityList.get(position));
                        }
                    }
                });
                break;
            case 3:
                ItemAllCityHolder allCityHolder = (ItemAllCityHolder) holder;
                if (position >= 1) {
                    final String cityName = allCityList.get(position).getName();
                    allCityHolder.tvCityName.setText(cityName);
                    allCityHolder.tvCityName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnCityClickListener != null) {
                                mOnCityClickListener.onCityClick(cityName);
                            }
                        }
                    });
                    String currentStr = getAlpha(allCityList.get(position).getPinyin());
                    String previewStr = position-1 >= 0 ?
                            getAlpha(allCityList.get(position-1).getPinyin()) : "";
                    //如果前一条城市拼音首字母与当前城市不相同，展示显示字母的TextView
                    if (!previewStr.equals(currentStr)) {
                        allCityHolder.alphaLayout.setVisibility(View.VISIBLE);
                        allCityHolder.tvAlpha.setText(currentStr);
                    } else {
                        allCityHolder.alphaLayout.setVisibility(View.GONE);
                    }
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return allCityList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position < 3 ? position : 3;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取拼音首字母
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        if (str == null) {
            return "$";
        }
        if (str.trim().length() == 0) {
            return "$";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else if (str.equals(0+"")) {
            return "$";
        } else if (str.equals(1+"")) {
            return "#";
        } else if (str.equals(2+"")) {
            return "*";
        }
        return "$";
    }


    class ItemLocationCityHolder extends RecyclerView.ViewHolder {

        public RelativeLayout itemLayout;
        public TextView tvLocation;
        public TextView tvFailure;
        public TextView tvCity;

        public ItemLocationCityHolder(View itemView) {
            super(itemView);
            itemLayout = (RelativeLayout) itemView.findViewById(R.id.item_location_city_layout);
            tvLocation = (TextView) itemView.findViewById(R.id.item_location_city_tv_ing);
            tvFailure = (TextView) itemView.findViewById(R.id.item_location_city_tv_failure);
            tvCity = (TextView) itemView.findViewById(R.id.item_location_city_tv_city);
        }
    }

    class ItemRecentCityHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public NoScrollGridView mNoScrollGridView;

        public ItemRecentCityHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.item_recent_city_tv);
            mNoScrollGridView = (NoScrollGridView) itemView.findViewById(R.id.item_recent_city_gv);
        }
    }

    class ItemAllCityHolder extends RecyclerView.ViewHolder {

        public LinearLayout alphaLayout;
        public TextView tvAlpha;
        public TextView tvCityName;

        public ItemAllCityHolder(View itemView) {
            super(itemView);
            alphaLayout = (LinearLayout) itemView.findViewById(R.id.item_all_city_alpha_layout);
            tvAlpha = (TextView) itemView.findViewById(R.id.item_all_city_tv_alpha);
            tvCityName = (TextView) itemView.findViewById(R.id.item_all_city_tv_city);
        }
    }

    public interface OnCityClickListener {
        void onCityClick(String cityName);
    }
}
