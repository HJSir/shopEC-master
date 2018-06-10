package com.example.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.example.ec.main.personal.PersonalDelegate;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.miec.R;
import com.example.miec.R2;


import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class OrderListDelegate extends MiDelegate {

    private String mType = null;
     OrderListAdapter adapter;
    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();
       mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {


    }

    @Override
    public void post(Runnable runnable) {

    }



    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        RestClient.builder()
                .loader(getContext())
                .url("order")
//                .params("type", mType)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(response).convert();
                       adapter = new OrderListAdapter(data,OrderListDelegate.this);
                        mRecyclerView.setAdapter(adapter);
//                        mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderListDelegate.this));
                    }
                })
                .build()
                .get();
    }
}
