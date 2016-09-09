package com.myxh.coolshopping.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.myxh.coolshopping.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mTitleBarIvBack;
    private EditText mEtPhoneNumber;
    private ImageView mIvClearPhoneNumber;
    private EditText mEtCode;
    private Button mBtnGetCode;
    private ImageView mIvClearCode;
    private EditText mEtPassword;
    private CheckBox mPasswordCheckBox;
    private ImageView mIvClearPassword;
    private EditText mEtRepassword;
    private ImageView mIvClearRepassword;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mTitleBarIvBack = (ImageView) findViewById(R.id.register_titleBar_iv_back);
        mEtPhoneNumber = (EditText) findViewById(R.id.register_et_phoneNumber);
        mIvClearPhoneNumber = (ImageView) findViewById(R.id.register_iv_clear_phoneNumber);
        mEtCode = (EditText) findViewById(R.id.register_et_code);
        mBtnGetCode = (Button) findViewById(R.id.register_btn_getCode);
        mIvClearCode = (ImageView) findViewById(R.id.register_iv_clear_code);
        mEtPassword = (EditText) findViewById(R.id.register_et_password);
        mPasswordCheckBox = (CheckBox) findViewById(R.id.register_password_checkBox);
        mIvClearPassword = (ImageView) findViewById(R.id.register_iv_clear_password);
        mEtRepassword = (EditText) findViewById(R.id.register_et_repassword);
        mIvClearRepassword = (ImageView) findViewById(R.id.register_iv_clear_repassword);
        mBtnRegister = (Button) findViewById(R.id.register_btn_register);

        mTitleBarIvBack.setOnClickListener(this);
        mBtnGetCode.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
        mIvClearPhoneNumber.setOnClickListener(this);
        mIvClearCode.setOnClickListener(this);
        mIvClearPassword.setOnClickListener(this);
        mIvClearRepassword.setOnClickListener(this);

        mPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    mEtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mEtRepassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mEtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mEtRepassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_titleBar_iv_back:
                finish();
                break;
            case R.id.register_iv_clear_phoneNumber:
                mEtPhoneNumber.setText("");
                mIvClearPhoneNumber.setVisibility(View.GONE);
                break;
            case R.id.register_iv_clear_code:
                mEtCode.setText("");
                mIvClearCode.setVisibility(View.GONE);
                break;
            case R.id.register_iv_clear_password:
                mEtPassword.setText("");
                mIvClearPassword.setVisibility(View.GONE);
                break;
            case R.id.register_iv_clear_repassword:
                mEtRepassword.setText("");
                mIvClearRepassword.setVisibility(View.GONE);
                break;
            case R.id.register_btn_getCode:

                break;
            case R.id.register_btn_register:

                break;
        }
    }

}
