package com.myxh.coolshopping.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.common.BmobManager;
import com.myxh.coolshopping.listener.BmobMsgSendCallback;
import com.myxh.coolshopping.listener.BmobSignUpCallback;
import com.myxh.coolshopping.listener.TextInputWatcher;
import com.myxh.coolshopping.model.User;
import com.myxh.coolshopping.ui.base.BaseActivity;
import com.myxh.coolshopping.util.LoginHelperUtil;
import com.myxh.coolshopping.util.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.exception.BmobException;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    public static final String INTENT_USER = "user";
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

    private boolean isPhoneNumberNull = true;
    private boolean isMsgCodeNull = true;
    private boolean isPasswordNull = true;
    private boolean isRepasswordNull = true;
    private String mPhoneNumber;
    private String mMsgCode;
    private String mPassword;
    private String mRepassword;
    private int mSecCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setViewListener();
    }

    private void setViewListener() {
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
                mEtPassword.setSelection(mEtPassword.length());
                mEtRepassword.setSelection(mEtRepassword.length());
            }
        });

        mEtPhoneNumber.addTextChangedListener(new TextInputWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                isPhoneNumberNull = TextUtils.isEmpty(mEtPhoneNumber.getText());
                mIvClearPhoneNumber.setVisibility(isPhoneNumberNull ? View.GONE : View.VISIBLE);
                mIvClearPhoneNumber.setEnabled(!isPhoneNumberNull);
                mBtnRegister.setEnabled((isPhoneNumberNull||isMsgCodeNull||isPasswordNull||isRepasswordNull
                        ? false : true));
            }
        });

        mEtCode.addTextChangedListener(new TextInputWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                isMsgCodeNull = TextUtils.isEmpty(mEtCode.getText());
                mIvClearCode.setVisibility(isMsgCodeNull ? View.GONE : View.VISIBLE);
                mIvClearCode.setEnabled(!isMsgCodeNull);
                mBtnRegister.setEnabled((isPhoneNumberNull||isMsgCodeNull||isPasswordNull||isRepasswordNull
                        ? false : true));
            }
        });

        mEtPassword.addTextChangedListener(new TextInputWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                isPasswordNull = TextUtils.isEmpty(mEtPassword.getText());
                mIvClearPassword.setVisibility(isPasswordNull ? View.GONE : View.VISIBLE);
                mIvClearPassword.setEnabled(!isPasswordNull);
                mBtnRegister.setEnabled((isPhoneNumberNull||isMsgCodeNull||isPasswordNull||isRepasswordNull
                        ? false : true));
            }
        });

        mEtRepassword.addTextChangedListener(new TextInputWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                isRepasswordNull = TextUtils.isEmpty(mEtRepassword.getText());
                mIvClearRepassword.setVisibility(isRepasswordNull ? View.GONE : View.VISIBLE);
                mIvClearRepassword.setEnabled(!isRepasswordNull);
                mBtnRegister.setEnabled((isPhoneNumberNull||isMsgCodeNull||isPasswordNull||isRepasswordNull
                        ? false : true));
            }
        });

       /* mEtRepassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mPassword = mEtPassword.getText().toString();
                    mRepassword = mEtRepassword.getText().toString();
                    if (!TextUtils.equals(mPassword,mRepassword)) {
                        ToastUtil.show(RegisterActivity.this,R.string.register_password_not_consistant);
                    }
                }
            }
        });*/
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
                mPhoneNumber = mEtPhoneNumber.getText().toString();
                if (LoginHelperUtil.isPhoneNumber(mPhoneNumber)) {
                    BmobManager.getInstance(new BmobMsgSendCallback() {
                        @Override
                        public void onMsgSendSuccess() {
                            ToastUtil.show(RegisterActivity.this,R.string.sms_code_send_success);
                            //验证码发送成功，倒计时
                            setCodeTimeDown();
                        }

                        @Override
                        public void onMsgSendFailure() {
                            ToastUtil.show(RegisterActivity.this,R.string.sms_code_send_failure);
                        }
                    }).sendMsgCode(mPhoneNumber);
                } else {
                    ToastUtil.show(this,R.string.phone_number_incorrect);
                }
                break;
            case R.id.register_btn_register:
                mPhoneNumber = mEtPhoneNumber.getText().toString();
                mMsgCode = mEtCode.getText().toString();
                mPassword = mEtPassword.getText().toString();
                mRepassword = mEtRepassword.getText().toString();
                if (LoginHelperUtil.isPhoneNumber(mPhoneNumber) && LoginHelperUtil.isCodeCorrect(mMsgCode)
                        && TextUtils.equals(mPassword,mRepassword)) {
                    BmobManager.getInstance(new BmobSignUpCallback() {
                        @Override
                        public void onSignUpSuccess(User user) {
                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                            intent.putExtra(INTENT_USER,user);
                            startActivity(intent);
                            ToastUtil.show(RegisterActivity.this,R.string.register_success);
                            finish();
                        }

                        @Override
                        public void onSignUpFailure(BmobException e) {
                            ToastUtil.show(RegisterActivity.this,R.string.register_failure);
                        }
                    }).signUp(mPhoneNumber,mMsgCode,mPassword);
                } else {
                    ToastUtil.show(this,R.string.register_input_incorrect);
                }
                break;
        }
    }

    private void setCodeTimeDown() {
        mBtnGetCode.setEnabled(false);
        final Timer timer = new Timer();
        mSecCount = 60;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSecCount--;
                        mBtnGetCode.setText(mSecCount+" s");
                        if (mSecCount<=0) {
                            timer.cancel();
                            mBtnGetCode.setText(R.string.reSend);
                            mBtnGetCode.setEnabled(true);
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask,1000,1000);

    }

}
