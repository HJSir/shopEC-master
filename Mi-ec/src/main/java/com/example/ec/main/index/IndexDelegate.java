package com.example.ec.main.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ec.main.EcBottomDelegate;
import com.example.ec.main.index.list.IndexListAdapter;
import com.example.mi.delegates.bottom.BottomItemDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.ui.recycler.BaseDecoration;
import com.example.mi.ui.refresh.RefreshHandler;
import com.example.miec.R;
import com.example.miec.R2;


import butterknife.BindView;

/**
 * Created by jian
 */
public class IndexDelegate extends BottomItemDelegate {


    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
//    @BindView(R2.id.srl_index)
//    SwipeRefreshLayout mRefreshLayout = null;


//    private RefreshHandler mRefreshHandler = null;


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
//        mRefreshLayout.setEnabled(false);

//        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());


    }

//    private void initRefreshLayout() {
//        mRefreshLayout.setColorSchemeResources(
//                android.R.color.holo_blue_bright,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light
//        );
//        mRefreshLayout.setProgressViewOffset(true, 120, 300);
//    }

    private void initData() {
        RestClient.builder().loader(getContext()).url("first ").success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                final GridLayoutManager manager = new GridLayoutManager(getContext(), 10);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.addItemDecoration
                        (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
                final EcBottomDelegate ecBottomDelegate = getParentDelegate();
                IndexItemClickListener listener = IndexItemClickListener.create(ecBottomDelegate);
                IndexAdapter adapter = new IndexAdapter(new IndexDataConverter().setJsonData(response).convert());
                adapter.setTitleItemListener(listener);
                mRecyclerView.setAdapter(adapter);

                mRecyclerView.addOnItemTouchListener(listener);
            }
        }).build().get();

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
//        mRefreshHandler.firstPage("first");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
