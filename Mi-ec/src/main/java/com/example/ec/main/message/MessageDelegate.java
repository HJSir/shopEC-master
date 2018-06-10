package com.example.ec.main.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.delegates.bottom.BaseBottomDelegate;
import com.example.mi.delegates.bottom.BottomItemDelegate;
import com.example.mi.delegates.bottom.BottomTabBean;
import com.example.mi.delegates.bottom.ItemBuilder;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jian
 */

public class MessageDelegate extends BottomItemDelegate  {
    @BindView(R2.id.rv_message_delegate)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_message;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        List<MessageListBean> list = new ArrayList<>();
        list.add(new MessageListBean.Builder()
                .setItemType(MessageListItemType.MEESAGE_LIST)
                .setIcon("{fa-bell}")
                .setValue("通知") //名称
                .setNumber("11")//数量
                .setText("公司、系统等相关通知")
                .build());
        list.add(new MessageListBean.Builder()
                .setItemType(MessageListItemType.MEESAGE_LIST)
                .setIcon("{fa-bullhorn}")
                .setValue("提醒") //名称
                .setNumber("1")//数量
                .setText("预约课程成功、友情提醒等")
                .build());
        list.add(new MessageListBean.Builder()
                .setItemType(MessageListItemType.MEESAGE_LIST)
                .setIcon("{fa-files-o}")
                .setValue("订单消息") //名称
                .setNumber("2")//数量
                .setText("可以查看订单相关信息")
                .build());
        list.add(new MessageListBean.Builder()
                .setItemType(MessageListItemType.MEESAGE_LIST)
                .setIcon("{icon-news}")
                .setValue("系统公告") //名称
                .setNumber("1")//数量
                .setText("系统平台信息、如升级等")
                .build());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new MessageListAdapter(list));
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                MessageListBean  bean= (MessageListBean) adapter.getData().get(position);


                                Bundle bundle = new Bundle();
                                bundle.putString("type", bean.getValue());
                                MessageListInDelegate messageListInDelegate = new MessageListInDelegate();
                                messageListInDelegate.setArguments(bundle);
                                getParentDelegate().getSupportDelegate().start(messageListInDelegate);

            }
        });

    }

    @Override
    public void post(Runnable runnable) {

    }


}
