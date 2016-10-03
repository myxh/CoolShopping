package com.myxh.coolshopping.ui.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.common.AppConstant;
import com.myxh.coolshopping.dao.AllCityDbHelper;
import com.myxh.coolshopping.dao.CityDbHelper;
import com.myxh.coolshopping.model.City;
import com.myxh.coolshopping.ui.activity.CityActivity;
import com.myxh.coolshopping.ui.adapter.CityListAdapter;
import com.myxh.coolshopping.ui.adapter.CityResultAdapter;
import com.myxh.coolshopping.ui.base.BaseFragment;
import com.myxh.coolshopping.ui.widget.SidebarView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by asus on 2016/9/29.
 */

public class CityFragment extends BaseFragment {

    private static final String TAG = CityFragment.class.getSimpleName();
    private TextView mTvShow;
    private SidebarView mSidebar;
    private EditText mEtSearch;
    private RecyclerView mRvCityList;
    private RecyclerView mRvSearchResult;
    private TextView mTvNoResult;
    private RelativeLayout mContentLayout;
    private LinearLayoutManager mLinearLayoutManager;

    private List<City> allCityData;//所有的城市
    private List<String> hotCityData;//热门城市列表
    private List<City> searchCityData;//搜索城市列表
    private List<String> recentCityData;//最近访问城市列表

    private CityListAdapter mCityListAdapter;
    private CityResultAdapter mCityResultAdapter;

    private CityDbHelper mCityDbHelper;
    private boolean isMove = false;
    private int mIndex = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        initView(view);
        initData();
        setListener();
        return view;
    }

    private void setListener() {
        mSidebar.setOnSlidingListener(new SidebarView.OnSlidingListener() {
            @Override
            public void onSliding(String selectedStr) {
                mTvShow.setText(selectedStr);
                if (mCityListAdapter.alphaIndexer.get(selectedStr) != null) {
                    int position = mCityListAdapter.alphaIndexer.get(selectedStr);
                    move(position);
                }
            }
        });

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString()==null || "".equals(s.toString().trim())) {
                    mSidebar.setVisibility(View.VISIBLE);
                    mRvCityList.setVisibility(View.VISIBLE);
                    mRvSearchResult.setVisibility(View.GONE);
                    mTvNoResult.setVisibility(View.GONE);
                } else {
                    mSidebar.setVisibility(View.GONE);
                    mRvCityList.setVisibility(View.GONE);
                    getResultCityList(s.toString());
                    if (searchCityData.size() <= 0) {
                        mRvSearchResult.setVisibility(View.GONE);
                        mTvNoResult.setVisibility(View.VISIBLE);
                    } else {
                        //用于解决The specified child already has a parent. You must call removeView() on the child's parent first.
                        mContentLayout.removeView(mRvSearchResult);
//                        mContentLayout.addView(mRvSearchResult);
                        mRvSearchResult.setVisibility(View.VISIBLE);
                        mTvNoResult.setVisibility(View.GONE);
                        mCityResultAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCityListAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String cityName) {
                //插入到最近浏览表中
                insertCity(cityName);
                ((CityActivity) getActivity()).setTitle(cityName);
                Intent data = new Intent();
                data.putExtra(AppConstant.KEY_CITY,cityName);
                getActivity().setResult(CityActivity.CITY_RESULT_CODE,data);
                getActivity().finish();
            }
        });
    }

    private void initData() {
        mCityDbHelper = new CityDbHelper(getActivity());

        allCityData = new ArrayList<>();
        hotCityData = new ArrayList<>();
        searchCityData = new ArrayList<>();
        recentCityData = new ArrayList<>();


        initRecentCityData();
        initHotCityData();
        initAllCityData();

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRvCityList.setLayoutManager(mLinearLayoutManager);
        mCityListAdapter = new CityListAdapter(getActivity(),recentCityData,hotCityData,allCityData);
        mRvCityList.setAdapter(mCityListAdapter);

        mRvSearchResult.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCityResultAdapter = new CityResultAdapter(getActivity(),searchCityData);
        mRvSearchResult.setAdapter(mCityResultAdapter);
    }

    private void initView(View view) {
        mTvShow = (TextView) view.findViewById(R.id.city_tv_show);
        mSidebar = (SidebarView) view.findViewById(R.id.city_sidebar);
        mEtSearch = (EditText) view.findViewById(R.id.city_et_search);
        mRvCityList = (RecyclerView) view.findViewById(R.id.city_rv_city_list);
        mRvSearchResult = (RecyclerView) view.findViewById(R.id.city_rv_search_result);
        mTvNoResult = (TextView) view.findViewById(R.id.city_tv_no_result);
        mContentLayout = (RelativeLayout) view.findViewById(R.id.city_fra_content_layout);

        mSidebar.setShowText(mTvShow);
    }

    private void initRecentCityData() {
        SQLiteDatabase recentDb = mCityDbHelper.getReadableDatabase();
        Cursor cursor = recentDb.rawQuery("select * from recentcity order by date desc limit 0, 4",null);
        while (cursor.moveToNext()) {
            String recentCityName = cursor.getString(cursor.getColumnIndex("name"));
            recentCityData.add(recentCityName);
        }
        cursor.close();
        recentDb.close();
    }

    private void initHotCityData() {
        String[] hotCitys = getResources().getStringArray(R.array.city_hot);
        for (int i = 0; i < hotCitys.length; i++) {
            hotCityData.add(hotCitys[i]);
        }
    }

    private void initAllCityData() {
        String[] categories = getResources().getStringArray(R.array.city_categories);
        for (int i = 0; i < categories.length; i++) {
            City city = new City(categories[i],i+"");
            allCityData.add(city);
        }

        allCityData.addAll(getCityList());
    }

    /**
     * 获取所有城市列表
     * @return
     */
    private ArrayList<City> getCityList() {
        SQLiteDatabase database = null;
        Cursor cursor = null;
        AllCityDbHelper allCityDbHelper = new AllCityDbHelper(getActivity());
        ArrayList<City> cityList = new ArrayList<>();
        try {
            allCityDbHelper.createDataBase();
            database = allCityDbHelper.getWritableDatabase();
            cursor = database.rawQuery("select * from city",null);

            while (cursor.moveToNext()) {
                String cityName = cursor.getString(cursor.getColumnIndex("name"));
                String cityPinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
                City city = new City(cityName,cityPinyin);
                cityList.add(city);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
        Collections.sort(cityList,mComparator);
        return cityList;
    }


    /**
     * 插入最近浏览城市
     * @param name
     */
    private void insertCity(String name) {
        SQLiteDatabase database = mCityDbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from recentcity where name = '"
                + name + "'", null);
        if (cursor.getCount() > 0) {
            database.delete("recentcity","name = ?",new String[]{name});
        }
        database.execSQL("insert into recentcity(name, date) values('" + name + "', "
                + System.currentTimeMillis() + ")");
        database.close();
    }

    /**
     * 查询结果列表
     * @param keyword
     */
    private void getResultCityList(String keyword) {
        AllCityDbHelper dbHelper = new AllCityDbHelper(getActivity());
        try {
            dbHelper.createDataBase();
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            Cursor cursor = database.rawQuery("select * from city where name like \"%" + keyword
                    + "%\" or pinyin like \"%" + keyword + "%\"", null);
            City city;
            while (cursor.moveToNext()) {
                String cityName = cursor.getString(cursor.getColumnIndex("name"));
                String cityPinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
                city = new City(cityName,cityPinyin);
                searchCityData.add(city);
                Log.i(TAG, "getResultCityList: "+cityName);
            }
            cursor.close();
            database.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(searchCityData,mComparator);
    }


    Comparator<City> mComparator = new Comparator<City>() {
        @Override
        public int compare(City o1, City o2) {
            String str1 = o1.getPinyin().substring(0,1);
            String str2 = o2.getPinyin().substring(0,1);
            return str1.compareTo(str2);
        }
    } ;

    /**
     * 滑动到指定位置
     * @param n
     */
    private void move(int n) {
        mIndex = n;
        mRvCityList.stopScroll();
        moveToPosition(n);
    }

    /**
     * RecycleView滑动到指定位置
     * @param n
     */
    private void moveToPosition(int n) {

        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (n <= firstItem ){
            //当要置顶的项在当前显示的第一个项的前面时
            mRvCityList.scrollToPosition(n);
        }else if ( n <= lastItem ){
            //当要置顶的项已经在屏幕上显示时
            int top = mRvCityList.getChildAt(n - firstItem).getTop();
            mRvCityList.scrollBy(0, top);
        }else{
            //当要置顶的项在当前显示的最后一项的后面时
            mRvCityList.scrollToPosition(n);
            isMove = true;
        }

    }

    private class OverlayThread implements Runnable {

        @Override
        public void run() {
            mTvShow.setVisibility(View.GONE);
        }
    }

    class RecycleViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (isMove && newState==RecyclerView.SCROLL_STATE_IDLE) {
                isMove = false;

            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //第二次滚动
            if (isMove ){
                isMove = false;
                //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                int n = mIndex - mLinearLayoutManager.findFirstVisibleItemPosition();
                if ( 0 <= n && n < mRvCityList.getChildCount()){
                    //获取要置顶的项顶部离RecyclerView顶部的距离
                    int top = mRvCityList.getChildAt(n).getTop();
                    //最后的移动
                    mRvCityList.scrollBy(0, top);
                }
            }
        }
    }

}
