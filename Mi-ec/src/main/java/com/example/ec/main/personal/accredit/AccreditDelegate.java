package com.example.ec.main.personal.accredit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class AccreditDelegate extends MiDelegate {


    @BindView(R2.id.et_delegate_accredit_content)
    EditText mContent=null;
    @BindView(R2.id.rv_search_content)
    RecyclerView mRecyclerView=null;
    //头部 **
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }
    //**
    @OnClick(R2.id.bt_delegate_accredit_submit)
    void submitOnClick(){
        RestClient.builder().url("search").loader(getContext()).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                ArrayList<MultipleItemEntity> arrayList = new SearchResultDataConverter().setJsonData(response).convert();
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setAdapter(new AccreditAdapter(arrayList));


            }
        }).build().get();

    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_accredit;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("授权查询");
    }

    @Override
    public void post(Runnable runnable) {

    }
}
