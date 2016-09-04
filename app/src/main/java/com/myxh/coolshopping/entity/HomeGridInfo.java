package com.myxh.coolshopping.entity;

import android.support.annotation.StringRes;

/**
 * Created by asus on 2016/8/31.
 */
public class HomeGridInfo {

    private @StringRes int gridIcon;
    private String gridTitle;

    public HomeGridInfo(int gridIcon, String gridTitle) {
        this.gridIcon = gridIcon;
        this.gridTitle = gridTitle;
    }

    public int getGridIcon() {
        return gridIcon;
    }

    public void setGridIcon(int gridIcon) {
        this.gridIcon = gridIcon;
    }

    public String getGridTitle() {
        return gridTitle;
    }

    public void setGridTitle(String gridTitle) {
        this.gridTitle = gridTitle;
    }
}

