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
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class BindBankCardDelegate extends MiDelegate {
    BankCardAdapter adapter=null;
    @BindView(R2.id.tv_top_right)
    IconTextView topRightText = null;
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.tv_top_right)
     void addCard(){

getSupportDelegate().start(new AddBankCardDelegate());

    }

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }

    @BindView(R2.id.rv_bankcard_detail)
    RecyclerView mRecyclerView =null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_bankcard;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topRightText.setText("{fa-plus-square}");
        topRightText.setTextSize(20);
        topMidText.setText("银行卡管理");

        initData();
    }


    public void initData(){
        RestClient.builder().url("bank").loader(getContext()).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {



                ArrayList<ListBean> data = new WalletDataConverter(response,ListItemType.ITEM_BANKCARD).convert();
                //设置RecyclerView
                final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(manager);

                        adapter = new BankCardAdapter(data,BindBankCardDelegate.this);
                mRecyclerView.setItemAnimator(null);
                mRecyclerView.setAdapter(adapter);

            }
        }).build().get();



    }
    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null)
        adapter.notifyDataSetChanged();
    }
}
