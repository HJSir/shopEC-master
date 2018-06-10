package com.example.ec.main.personal.wallet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class RemitAccountDelegate extends MiDelegate {
    @BindView(R2.id.tv_remit_account_size)
    TextView accountSize=null;

    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }
    @OnClick(R2.id.bt_remit_account_submit)
    void submitInfo() {
        //提交申请
        getSupportDelegate().pop();

    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_remit_account;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("申请划账");
    }

    @Override
    public void post(Runnable runnable) {

    }
}
