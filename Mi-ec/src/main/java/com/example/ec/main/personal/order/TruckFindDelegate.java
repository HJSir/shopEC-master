package com.example.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.IError;
import com.example.mi.net.callback.IFailure;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class TruckFindDelegate extends MiDelegate implements ISuccess {
    String name = "yt";
    String number = "888588710242830601";
    String key = "d057453754f844e9b6772450a039e26e";
    String url = "exp/index?key=" + key
            + "&com=" + name + "&no=" + number;

    //头部 **
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }

    @BindView(R2.id.rv_delegate_truck)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_truck_find;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("物流查询");
//        Log.i("tag","mRecyclerView"+mRecyclerView);

        RestClient.builder().loader(getContext()).url(url).success(this).failure(new IFailure() {
            @Override
            public void onFailure() {
                Log.i("Tag", "失败");
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                Log.i("Tag", "错误");
            }
        }).build().getTruck();
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onSuccess(String response) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        ArrayList<MultipleItemEntity> arrayList = new TruckFindListDataConverter().setJsonData(response).convert();
        Collections.reverse(arrayList);
        TruckFindAdapter truckFindAdapter = new TruckFindAdapter(arrayList);
        mRecyclerView.setAdapter(truckFindAdapter);
    }
}
