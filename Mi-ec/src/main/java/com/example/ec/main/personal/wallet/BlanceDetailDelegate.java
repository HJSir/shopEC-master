package com.example.ec.main.personal.wallet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.alibaba.fastjson.JSON;
import com.example.ec.main.personal.list.ListAdapter;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.list.ListItemType;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class BlanceDetailDelegate extends MiDelegate {

    @BindView(R2.id.tv_top_right)
    TextView topRightText = null;
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }

    @BindView(R2.id.rv_blance_detail)
    RecyclerView mRecyclerView =null;
    @BindView(R2.id.tv_blanceDetail_currentpayment)
    TextView tvCurrentPayment=null;
    @BindView(R2.id.tv_blanceDetail_currentprofit)
    TextView tvCurrentProfit=null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_blance_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("余额明细");
        initData();
    }


    public void initData(){
        RestClient.builder().url("blance").loader(getContext()).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {


              String currentPayment = JSON.parseObject(response).getString("current_payment");
                String currentProfit = JSON.parseObject(response).getString("current_profit");
                tvCurrentPayment.setText("当前货款："+currentPayment);
                tvCurrentProfit.setText("当前利润："+currentProfit);
                ArrayList<ListBean> data = new WalletDataConverter(response, ListItemType.ITEM_BLANCE).convert();
                //设置RecyclerView
                final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(manager);
                final ListAdapter adapter = new ListAdapter(data);
                mRecyclerView.setAdapter(adapter);

            }
        }).build().get();



    }
    @Override
    public void post(Runnable runnable) {

    }
}
