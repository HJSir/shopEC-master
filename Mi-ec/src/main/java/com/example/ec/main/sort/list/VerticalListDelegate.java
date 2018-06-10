package com.example.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ec.main.sort.SortDelegate;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by jian
 */

public class VerticalListDelegate extends MiDelegate {
    @BindView(R2.id.rv_vertical_delegate_list)
    RecyclerView mRecyclerView;
    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }
private void initRecyclerView(){
        final LinearLayoutManager manager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画
    mRecyclerView.setItemAnimator(null);


}
    @Override
    public void onBindView(Bundle saveInstanceState, View rootView) {
initRecyclerView();
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder().url("sortlist")
                .loader(getContext())
                .success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                      final List<MultipleItemEntity> data=new VerticalListDataConverter().setJsonData(response).convert();
                      final SortDelegate delegate=getParentDelegate();
                      final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data,delegate);
                      mRecyclerView.setAdapter(adapter);
            }
        }) .build().get();
}
}
