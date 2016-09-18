package com.myxh.coolshopping.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.network.HttpListener;
import com.myxh.coolshopping.ui.activity.LocationActivity;
import com.myxh.coolshopping.ui.base.BaseFragment;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2016/8/27.
 */
public class AroundFragment extends BaseFragment implements HttpListener<String>, View.OnClickListener {

    private static final String MENU_DATA_KEY = "name";
    private Toolbar mToolbar;
    private TextView mSupplierListTitleTv;
    private ImageView mSupplierListCartIv;
    private FrameLayout mTitleBarLayout;
    private TextView mSupplierListTvProduct;
    private TextView mSupplierListTvSort;
    private TextView mSupplierListTvActivity;
    private LinearLayout mSupplierListProduct;
    private LinearLayout mSupplierListSort;
    private LinearLayout mSupplierListActivity;
    private ListView mListView;

    private List<Map<String, String>> mMenuData1;
    private List<Map<String, String>> mMenuData2;
    private List<Map<String, String>> mMenuData3;
    private ListView mPopListView;
    private PopupWindow mPopupWindow;
    private SimpleAdapter mMenuAdapter1;
    private SimpleAdapter mMenuAdapter2;
    private SimpleAdapter mMenuAdapter3;
    private int supplierMenuIndex = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_around, null);

        initData();
        initView(view);
        initPopMenu();
        return view;
    }

    private void initData() {
        mMenuData1 = new ArrayList<>();
        String[] products = getResources().getStringArray(R.array.supplier_product);
        for (int i = 0; i < products.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(MENU_DATA_KEY, products[i]);
            mMenuData1.add(map);
        }
        mMenuData2 = new ArrayList<>();
        String[] sorts = getResources().getStringArray(R.array.supplier_sort);
        for (int i = 0; i < sorts.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(MENU_DATA_KEY, sorts[i]);
            mMenuData2.add(map);
        }
        mMenuData3 = new ArrayList<>();
        String[] activitys = getResources().getStringArray(R.array.supplier_activity);
        for (int i = 0; i < activitys.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(MENU_DATA_KEY, activitys[i]);
            mMenuData3.add(map);
        }

    }

    @Override
    public void onSucceed(int what, Response<String> response) {
    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.around_toolbar);
        mToolbar.inflateMenu(R.menu.around_title_menu);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.around_toolbar_menu_map:
                        openActivity(LocationActivity.class);
                        break;
                }
                return true;
            }
        });

        mSupplierListTitleTv = (TextView) view.findViewById(R.id.around_supplier_list_title_tv);
        mSupplierListCartIv = (ImageView) view.findViewById(R.id.around_supplier_list_cart_iv);
        mTitleBarLayout = (FrameLayout) view.findViewById(R.id.around_titleBar_layout);
        mSupplierListTvProduct = (TextView) view.findViewById(R.id.around_supplier_list_tv_product);
        mSupplierListTvSort = (TextView) view.findViewById(R.id.around_supplier_list_tv_sort);
        mSupplierListTvActivity = (TextView) view.findViewById(R.id.around_supplier_list_tv_activity);
        mSupplierListProduct = (LinearLayout) view.findViewById(R.id.around_supplier_list_product);
        mSupplierListProduct.setOnClickListener(this);
        mSupplierListSort = (LinearLayout) view.findViewById(R.id.around_supplier_list_sort);
        mSupplierListSort.setOnClickListener(this);
        mSupplierListActivity = (LinearLayout) view.findViewById(R.id.around_supplier_list_activity);
        mSupplierListActivity.setOnClickListener(this);
        mListView = (ListView) view.findViewById(R.id.around_listView);
    }

    private void initPopMenu() {
        View popView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popwin_supplier_list, null);
        mPopListView = (ListView) popView.findViewById(R.id.popwin_list_view);
        mPopupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mSupplierListTvProduct.setTextColor(getResources().getColor(R.color.around_supplier_title_color));
                mSupplierListTvSort.setTextColor(getResources().getColor(R.color.around_supplier_title_color));
                mSupplierListTvActivity.setTextColor(getResources().getColor(R.color.around_supplier_title_color));
            }
        });

        popView.findViewById(R.id.popwin_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });

        mMenuAdapter1 = new SimpleAdapter(getActivity(), mMenuData1, R.layout.item_popwin_list,
                new String[]{"name"}, new int[]{R.id.item_popwin_tv});
        mMenuAdapter2 = new SimpleAdapter(getActivity(), mMenuData2, R.layout.item_popwin_list,
                new String[]{"name"}, new int[]{R.id.item_popwin_tv});
        mMenuAdapter3 = new SimpleAdapter(getActivity(), mMenuData3, R.layout.item_popwin_list,
                new String[]{"name"}, new int[]{R.id.item_popwin_tv});
        mPopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (supplierMenuIndex) {
                    case 0:
                        mSupplierListTvProduct.setText(mMenuData1.get(i).get(MENU_DATA_KEY));
                        mPopupWindow.dismiss();
                        break;
                    case 1:
                        mSupplierListTvSort.setText(mMenuData2.get(i).get(MENU_DATA_KEY));
                        mPopupWindow.dismiss();
                        break;
                    case 2:
                        mSupplierListTvActivity.setText(mMenuData3.get(i).get(MENU_DATA_KEY));
                        mPopupWindow.dismiss();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.around_supplier_list_product:
                mSupplierListTvProduct.setTextColor(getResources().getColor(R.color.around_supplier_title_selected_color));
                mPopListView.setAdapter(mMenuAdapter1);
                mPopupWindow.showAsDropDown(mSupplierListProduct, 0, 2);
                supplierMenuIndex = 0;
                break;
            case R.id.around_supplier_list_sort:
                mSupplierListTvSort.setTextColor(getResources().getColor(R.color.around_supplier_title_selected_color));
                mPopListView.setAdapter(mMenuAdapter2);
                mPopupWindow.showAsDropDown(mSupplierListSort, 0, 2);
                supplierMenuIndex = 1;
                break;
            case R.id.around_supplier_list_activity:
                mSupplierListTvActivity.setTextColor(getResources().getColor(R.color.around_supplier_title_selected_color));
                mPopListView.setAdapter(mMenuAdapter3);
                mPopupWindow.showAsDropDown(mSupplierListActivity, 0, 2);
                supplierMenuIndex = 2;
                break;
            default:
                break;
        }
    }
}
