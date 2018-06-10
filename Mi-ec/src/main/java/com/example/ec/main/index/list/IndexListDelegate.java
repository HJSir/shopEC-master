package com.example.ec.main.index.list;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.ec.detail.GoodsDetailDelegate;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.ui.recycler.BaseDecoration;
import com.example.mi.ui.recycler.ItemType;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;

import com.example.mi.ui.webview.MiWebViewDelegate;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class IndexListDelegate extends MiDelegate implements BaseQuickAdapter.OnItemClickListener{

    int layoutType;
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;
    @BindView(R2.id.rv_index_list)
    RecyclerView mRecyclerView = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index_list;
    }

    @Override
    public void onBindView(Bundle saveInstanceState, View rootView) {

        layoutType = getArguments().getInt("layoutType");


        switch (layoutType) {

            case IndexListItemType.LIST_ACTIVITY:
                topMidText.setText("活动商品");
                initData("activity");
                break;
            case IndexListItemType.LIST_HOT:
                topMidText.setText("热卖商品");
                initData("hot");
                break;
            case IndexListItemType.LIST_NEW:
                topMidText.setText("新品预售");
                initData("goodsnew");
                break;
            case IndexListItemType.LIST_NEWS:
                topMidText.setText("新闻公告");
                initData("news");
                break;
            case IndexListItemType.LIST_STUDY:
                topMidText.setText("在线学习");
                break;
            default:
                break;

        }


    }

    void initData(String url) {

        RestClient.builder().url(url).loader(getContext()).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {

                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                ArrayList<MultipleItemEntity> list = new IndexListDataConverter().setJsonData(response).convert();
                mRecyclerView.addItemDecoration
                        (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
                IndexListAdapter adapter =new IndexListAdapter(list);
                adapter.setOnItemClickListener(IndexListDelegate.this);
                mRecyclerView.setAdapter(adapter);

            }
        }).build().get();

    }


    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
        if(layoutType!= IndexListItemType.LIST_NEWS){
            final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(1);
            getSupportDelegate().start(delegate);

        }else if(layoutType== IndexListItemType.LIST_NEWS){

            MiWebViewDelegate delegate =new MiWebViewDelegate();
            Bundle bundle =new Bundle();
             bundle.putString("title","详情");
            bundle.putString("url",entity.getField(MultipleFields.SKIP_URL).toString());
            delegate.setArguments(bundle);
            getSupportDelegate().start(delegate);
        }

    }
}
