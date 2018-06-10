package com.example.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mi.app.Mi;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.ui.loader.MiLoader;
import com.example.mi.utils.log.MiLogger;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.miec.R;
import com.example.miec.R2;
import com.mob.MobSDK;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by jian
 */

public class SignUpDelegate extends MiDelegate {
    @BindView(R2.id.btn_sign_up_phone_code)
    TextView mBtnCode;
    @BindView(R2.id.edit_sign_up_otherid)
    TextInputEditText mOtherId = null;
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_phone_code)
    TextInputEditText mCode = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onCancelTimer();
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up_phone_code)
    void onClickGetCode() {
        if (mPhone.getText().toString().isEmpty() || mPhone.getText().toString().length() != 11) {
            mPhone.setError("手机号码格式错误");

        } else {
            mBtnCode.setEnabled(false);
            sendCode("86", mPhone.getText().toString());
            onRestartTimer();
            mPhone.setError(null);
        }


    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            MiLoader.showLoading(getContext());
            //继续检测验证码是否正确
            submitCode("86", mPhone.getText().toString(), mCode.getText().toString());
        }
    }

    void upLoadInfo() {

        RestClient.builder()
                .url("http://127.0.0.1/index")
                .params("name", mName.getText().toString())

                .params("phone", mPhone.getText().toString())
                .params("password", mPassword.getText().toString())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                            MiLogger.json("USER_PROFILE", response);
//                            SignHandler.onSignUp(response, mISignListener);
                        MiLogger.json("USER_PROFILE", "");
                        SignHandler.onSignUp("", mISignListener);

                    }
                })
//                .loader(getContext())
                .build()
                .get();


    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        getSupportDelegate().start(new SignInDelegate());
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String code = mCode.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();
        final String otherId = mOtherId.getText().toString();
        boolean isPass = true;
        if (otherId.isEmpty()) {
            mOtherId.setError("请输入邀请人ID");
            isPass = false;
        } else {
            mOtherId.setError(null);
        }
        if (name.isEmpty()) {
            mName.setError("请输入昵称");
            isPass = false;
        } else {
            mName.setError(null);
        }
        //验证码检验是否为空
        if (code.isEmpty()) {
            mCode.setError("请填写验证码");
            isPass = false;
        } else {
            mCode.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码格式错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        MobSDK.init(Mi.getApplicationContext(), "1a02cfb2afca0", "64677de12ae65903516875a5e8a7c506");

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
    }

    // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
    public void sendCode(String country, String phone) {
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理成功得到验证码的结果
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                    Log.i("MobSDK", "信息发送成功");
                } else {
                    // TODO 处理错误的结果
                    Log.i("MobSDK", "信息发送失败");
                }

            }
        });
        // 触发操作
        SMSSDK.getVerificationCode(country, phone);
    }

    // 提交验证码，其中的code表示验证码，如“1357”
    public void submitCode(String country, String phone, String code) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理验证成功的结果
                    upLoadInfo();
                } else {
                    // TODO 处理错误的结果
                }

            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }


    public void onCancelTimer() {
        timer.cancel();
    }

    /**
     * 开始倒计时
     */

    public void onRestartTimer() {
        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            mBtnCode.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            mBtnCode.setEnabled(true);
            mBtnCode.setText("获取验证码");
        }
    };

    @Override
    public void post(Runnable runnable) {

    }
}
