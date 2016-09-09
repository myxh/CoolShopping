package com.myxh.coolshopping.listener;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by asus on 2016/9/9.
 */
public abstract class TextInputWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public abstract void afterTextChanged(Editable editable);
}
