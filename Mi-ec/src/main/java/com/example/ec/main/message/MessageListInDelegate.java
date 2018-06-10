package com.example.ec.main.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ec.main.personal.order.OrderDetailDelegate;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.miec.R;
import com.example.miec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */
public class MessageListInDelegate extends MiDelegate {
 String type;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_message_list_in;
    }
@BindView(R2.id.rv_message_delegate_in)
    RecyclerView mRecyclerView=null;
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        type=getArguments().getString("type");
        if(type!=null)
        topMidText.setText(type);
        if(type.equals("订单消息")){
            dataInit("message");
        }else if(type.equals("通知")){
            dataInit("message");
        }else if(type.equals("系统公告")){
            dataInit("message");
        }



    }

    void dataInit(String url){

        RestClient.builder().loader(getContext()).url(url).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {

                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setAdapter(new MessageListAdapter(new MessageListInDataConverter().convert(response) ));
                mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if(type.equals("订单消息")){
                        //得到订单ID 跳订单，
                            getSupportDelegate().start(new OrderDetailDelegate());

                        }else if(type.equals("通知")){
                           //得到通知ID，跳详情页
                            getSupportDelegate().start(new MessageDetailDlegate());
                        }else if(type.equals("系统公告")){
                         //得到公告ID 跳详情页
                            getSupportDelegate().start(new MessageDetailDlegate());
                        }

                    }
                });

            }
        }).build().get();


    }

    @Override
    public void post(Runnable runnable) {

    }
}
