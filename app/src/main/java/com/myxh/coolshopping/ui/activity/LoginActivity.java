package com.myxh.coolshopping.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myxh.coolshopping.R;
import com.myxh.coolshopping.listener.TextInputWatcher;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mTitleBarIvBack;
    private TextView mTitleBarTvRegister;
    private TextView mSelectTvQuickLogin;
    private TextView mSelectTvAccountLogin;
    private View mSelectLeftLine;
    private View mSelectRightLine;
    private EditText mQuickLoginEtPhoneNumber;
    private ImageView mQuickLoginIvClearPhoneNumber;
    private EditText mQuickLoginEtCode;
    private Button mQuickLoginBtnGetCode;
    private ImageView mQuickLoginIvClearCode;
    private EditText mEtCheckPicture;
    private ImageView mIvCheckPicture;
    private RelativeLayout mLlCheckPicture;
    private LinearLayout mQuickLoginLayout;
    private EditText mAccountLoginEtUsername;
    private ImageView mAccountLoginIvClearUsername;
    private EditText mAccountLoginEtPassword;
    private CheckBox mAccountLoginCheckBox;
    private ImageView mAccountLoginIvClearPassword;
    private EditText mEtCheckCode;
    private ImageView mIvCheckCode;
    private RelativeLayout mLlCheckCode;
    private LinearLayout mAccountLoginLayout;
    private Button mQuickLoginBtn;
    private Button mAccountLoginBtn;
    private TextView mAccountLoginTvForgetPassword;
    private ImageView mBottomIvQq;
    private ImageView mBottomIvWechat;
    private ImageView mBottomIvWeibo;
    private ImageView mBottomIvAlipay;

    private Animation mLeftLineAnimation;
    private Animation mRightLineAnimation;

    private int mSelectedTextColor;
    private int mUnselectedTextColor;

    /**快速登陆界面*/
    private boolean isPhoneNumberNull = true;
    private boolean isCodeNull = true;
    /**账号登陆界面*/
    private boolean isUserNameNull = true;
    private boolean isPasswordNull = true;

    private boolean isQuickLoginSelected = true;
    private boolean isAccountLoginSelected = false;
    private int mSecCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initAnimation();
        setViewListener();
    }

    private void setViewListener() {
        //
        mTitleBarIvBack.setOnClickListener(this);
        mTitleBarTvRegister.setOnClickListener(this);
        mSelectTvQuickLogin.setOnClickListener(this);
        mSelectTvAccountLogin.setOnClickListener(this);
        //
        mQuickLoginIvClearPhoneNumber.setOnClickListener(this);
        mQuickLoginBtnGetCode.setOnClickListener(this);
        mQuickLoginIvClearCode.setOnClickListener(this);
        mQuickLoginBtn.setOnClickListener(this);
        //
        mAccountLoginIvClearUsername.setOnClickListener(this);
        mAccountLoginIvClearPassword.setOnClickListener(this);
        mAccountLoginBtn.setOnClickListener(this);
        mAccountLoginTvForgetPassword.setOnClickListener(this);

        mAccountLoginCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                //切换明密文
                if (checked) {
                    mAccountLoginEtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mAccountLoginEtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                //光标在末尾显示
                mAccountLoginEtPassword.setSelection(mAccountLoginEtPassword.length());
            }
        });

        mQuickLoginEtPhoneNumber.addTextChangedListener(new TextInputWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                isPhoneNumberNull = TextUtils.isEmpty(mQuickLoginEtPhoneNumber.getText());
                mQuickLoginIvClearPhoneNumber.setVisibility(isPhoneNumberNull ? View.GONE : View.VISIBLE);
                mQuickLoginIvClearPhoneNumber.setEnabled(!isPhoneNumberNull);
                mQuickLoginBtn.setEnabled((isPhoneNumberNull||isCodeNull) ? false : true);
            }
        });

        mQuickLoginEtCode.addTextChangedListener(new TextInputWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                isCodeNull = TextUtils.isEmpty(mQuickLoginEtCode.getText());
                mQuickLoginIvClearCode.setVisibility(isCodeNull ? View.GONE : View.VISIBLE);
                mQuickLoginIvClearCode.setEnabled(!isCodeNull);
                mQuickLoginBtn.setEnabled((isPhoneNumberNull||isCodeNull) ? false : true);
            }
        });

        mAccountLoginEtUsername.addTextChangedListener(new TextInputWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                isUserNameNull = TextUtils.isEmpty(mAccountLoginEtUsername.getText());
                mAccountLoginIvClearUsername.setVisibility(isUserNameNull ? View.GONE : View.VISIBLE);
                mAccountLoginIvClearUsername.setEnabled(!isUserNameNull);
                mAccountLoginBtn.setEnabled((isUserNameNull||isPasswordNull) ? false : true);
            }
        });

        mAccountLoginEtPassword.addTextChangedListener(new TextInputWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                isPasswordNull = TextUtils.isEmpty(mAccountLoginEtPassword.getText());
                mAccountLoginIvClearPassword.setVisibility(isPasswordNull ? View.GONE : View.VISIBLE);
                mAccountLoginIvClearPassword.setEnabled(!isPasswordNull);
                mAccountLoginBtn.setEnabled((isUserNameNull||isPasswordNull) ? false : true);
            }
        });
    }

    private void initAnimation() {
        mSelectedTextColor = getResources().getColor(R.color.app_yellow);
        mUnselectedTextColor = getResources().getColor(R.color.content_color);
        mLeftLineAnimation = AnimationUtils.loadAnimation(this,R.anim.anim_line_move_right);
        mLeftLineAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSelectTvQuickLogin.setTextColor(mUnselectedTextColor);
                mSelectTvAccountLogin.setTextColor(mSelectedTextColor);
                mQuickLoginLayout.setVisibility(View.GONE);
                mAccountLoginLayout.setVisibility(View.VISIBLE);
                mSelectLeftLine.setVisibility(View.INVISIBLE);
                mSelectRightLine.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mRightLineAnimation = AnimationUtils.loadAnimation(this,R.anim.anim_line_move_left);
        mRightLineAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSelectTvQuickLogin.setTextColor(mSelectedTextColor);
                mSelectTvAccountLogin.setTextColor(mUnselectedTextColor);
                mQuickLoginLayout.setVisibility(View.VISIBLE);
                mAccountLoginLayout.setVisibility(View.GONE);
                mSelectLeftLine.setVisibility(View.VISIBLE);
                mSelectRightLine.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initView() {
        mTitleBarIvBack = (ImageView) findViewById(R.id.login_titleBar_iv_back);
        mTitleBarTvRegister = (TextView) findViewById(R.id.login_titleBar_tv_register);
        mSelectTvQuickLogin = (TextView) findViewById(R.id.login_select_tv_quickLogin);
        mSelectTvAccountLogin = (TextView) findViewById(R.id.login_select_tv_accountLogin);
        mSelectLeftLine = findViewById(R.id.login_select_left_line);
        mSelectRightLine = findViewById(R.id.login_select_right_line);
        mQuickLoginEtPhoneNumber = (EditText) findViewById(R.id.login_quick_login_et_phoneNumber);
        mQuickLoginIvClearPhoneNumber = (ImageView) findViewById(R.id.login_quick_login_iv_clear_phoneNumber);
        mQuickLoginEtCode = (EditText) findViewById(R.id.login_quick_login_et_code);
        mQuickLoginBtnGetCode = (Button) findViewById(R.id.login_quick_login_btn_getCode);
        mQuickLoginIvClearCode = (ImageView) findViewById(R.id.login_quick_login_iv_clear_code);
        mEtCheckPicture = (EditText) findViewById(R.id.et_check_picture);
        mIvCheckPicture = (ImageView) findViewById(R.id.iv_check_picture);
        mLlCheckPicture = (RelativeLayout) findViewById(R.id.ll_check_picture);
        mQuickLoginLayout = (LinearLayout) findViewById(R.id.login_quick_login_layout);
        mAccountLoginEtUsername = (EditText) findViewById(R.id.login_account_login_et_username);
        mAccountLoginIvClearUsername = (ImageView) findViewById(R.id.login_account_login_iv_clear_username);
        mAccountLoginEtPassword = (EditText) findViewById(R.id.login_account_login_et_password);
        mAccountLoginCheckBox = (CheckBox) findViewById(R.id.login_account_login_checkBox);
        mAccountLoginIvClearPassword = (ImageView) findViewById(R.id.login_account_login_iv_clear_password);
        mEtCheckCode = (EditText) findViewById(R.id.et_check_code);
        mIvCheckCode = (ImageView) findViewById(R.id.iv_check_code);
        mLlCheckCode = (RelativeLayout) findViewById(R.id.ll_check_code);
        mAccountLoginLayout = (LinearLayout) findViewById(R.id.login_account_login_layout);
        mQuickLoginBtn = (Button) findViewById(R.id.login_quick_login_btn);
        mAccountLoginBtn = (Button) findViewById(R.id.login_account_login_btn);
        mAccountLoginTvForgetPassword = (TextView) findViewById(R.id.login_account_login_tv_forget_password);
        mBottomIvQq = (ImageView) findViewById(R.id.login_bottom_iv_qq);
        mBottomIvWechat = (ImageView) findViewById(R.id.login_bottom_iv_wechat);
        mBottomIvWeibo = (ImageView) findViewById(R.id.login_bottom_iv_weibo);
        mBottomIvAlipay = (ImageView) findViewById(R.id.login_bottom_iv_alipay);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_titleBar_iv_back:
                finish();
                break;
            case R.id.login_titleBar_tv_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.login_select_tv_quickLogin:
                if (!isQuickLoginSelected) {
                    mSelectRightLine.startAnimation(mRightLineAnimation);
                    isQuickLoginSelected = true;
                    isAccountLoginSelected = false;
                }
                break;
            case R.id.login_select_tv_accountLogin:
                if (!isAccountLoginSelected) {
                    mSelectLeftLine.startAnimation(mLeftLineAnimation);
                    isAccountLoginSelected = true;
                    isQuickLoginSelected = false;
                }
                break;
            case R.id.login_quick_login_iv_clear_phoneNumber:
                mQuickLoginEtPhoneNumber.setText("");
                mQuickLoginIvClearPhoneNumber.setVisibility(View.GONE);
                break;
            case R.id.login_quick_login_iv_clear_code:
                mQuickLoginEtCode.setText("");
                mQuickLoginIvClearCode.setVisibility(View.GONE);
                break;
            case R.id.login_quick_login_btn_getCode:
                setCodeTimeDown();
                break;
            case R.id.login_quick_login_btn:

                break;
            case R.id.login_account_login_iv_clear_username:
                mAccountLoginEtUsername.setText("");
                mAccountLoginIvClearUsername.setVisibility(View.GONE);
                break;
            case R.id.login_account_login_iv_clear_password:
                mAccountLoginEtPassword.setText("");
                mAccountLoginIvClearPassword.setVisibility(View.GONE);
                break;
            case R.id.login_account_login_btn:

                break;
            case R.id.login_account_login_tv_forget_password:

                break;
            default:
                break;
        }
    }

    private void setCodeTimeDown() {
        mQuickLoginBtnGetCode.setEnabled(false);
        final Timer timer = new Timer();
        mSecCount = 60;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSecCount--;
                        mQuickLoginBtnGetCode.setText(mSecCount+" s");
                        if (mSecCount <= 0) {
                            timer.cancel();
                            mQuickLoginBtnGetCode.setText(getString(R.string.reSend));
                            mQuickLoginBtnGetCode.setEnabled(true);
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask,1000,1000);
    }

}
