package com.example.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;


import com.example.mi.delegates.MiDelegate;
import com.example.mi.utils.log.MiLogger;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.IError;
import com.example.mi.net.callback.IFailure;
import com.example.mi.net.callback.ISuccess;
import com.example.miec.R;
import com.example.miec.R2;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */
public class SignInDelegate extends MiDelegate {

    @BindView(R2.id.edit_sign_in_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://127.0.0.1/test")
//                    .params("email", mEmail.getText().toString())
//                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            MiLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    }).error(new IError() {
                @Override
                public void onError(int code, String msg) {
                    Log.i("Tag","fa"+msg.toString());
                }
            }).failure(new IFailure() {
                @Override
                public void onFailure() {
                    Log.i("Tag","fa");
                }
            })
                    .loader(getContext())
                    .build()
                    .get();


        }
    }
//微信登录预留接口
//    @OnClick(R2.id.icon_sign_in_wechat)
//    void onClickWeChat() {
//        LatteWeChat
//                .getInstance()
//                .onSignSuccess(new IWeChatSignInCallback() {
//                    @Override
//                    public void onSignInSuccess(String userInfo) {
//                        Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
//                    }
//                })
//                .signIn();
//    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            mPhone.setError("错误的电话格式");
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

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void post(Runnable runnable) {

    }
//    @Override
//    public boolean onBackPressedSupport() {
//        // 默认flase，继续向上传递
//// super的实现为：
//
//
//
//        return true;
//    }
}
