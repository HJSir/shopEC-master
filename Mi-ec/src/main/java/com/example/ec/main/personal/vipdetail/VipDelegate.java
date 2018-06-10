package com.example.ec.main.personal.vipdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.mi.delegates.MiDelegate;
import com.example.miec.R;
import com.example.miec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class VipDelegate extends MiDelegate {
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_vip_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("特权详情");
    }

    @Override
    public void post(Runnable runnable) {

    }
}
