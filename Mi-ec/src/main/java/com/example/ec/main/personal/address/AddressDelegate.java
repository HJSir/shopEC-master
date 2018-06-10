package com.example.ec.main.personal.address;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.example.ec.main.personal.wallet.BlanceDetailDelegate;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.miec.R;
import com.example.miec.R2;
import com.joanzapata.iconify.widget.IconTextView;


import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class AddressDelegate extends MiDelegate implements ISuccess {

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;



    @BindView(R2.id.tv_top_right)
    IconTextView topRightText = null;
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }

    @OnClick(R2.id.tv_top_right)
    void topRight() {
      getSupportDelegate().start(new AddAdressDelegate());

    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topRightText.setText("{fa-plus-square}");
        topMidText.setText("地址管理");
        RestClient.builder()
                .url("adress")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }

    @Override
    public void post(Runnable runnable) {

    }
}
