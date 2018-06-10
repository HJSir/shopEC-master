package com.example.ec.main.personal.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.miec.R;
import com.example.miec.R2;


import butterknife.BindView;

/**
 * Created by jian
 */

public class AboutDelegate extends MiDelegate {

    @BindView(R2.id.tv_info)
    AppCompatTextView mTextView = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RestClient.builder()
                .url("about.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        mTextView.setText(info);
                    }
                })
                .build()
                .get();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
