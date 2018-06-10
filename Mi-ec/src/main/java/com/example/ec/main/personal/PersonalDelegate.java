package com.example.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ec.main.personal.accredit.AccreditDelegate;
import com.example.ec.main.personal.address.AddressDelegate;
import com.example.ec.main.personal.list.ListAdapter;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.list.ListItemType;
import com.example.ec.main.personal.order.BaseOrderListDelegate;
import com.example.ec.main.personal.setting.SettingsDelegate;
import com.example.ec.main.personal.profile.UserProfileDelegate;
import com.example.ec.main.personal.team.TeamDelegate;
import com.example.ec.main.personal.vipdetail.VipDelegate;
import com.example.ec.main.personal.wallet.WalletDelegate;
import com.example.mi.delegates.bottom.BottomItemDelegate;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class PersonalDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    @OnClick(R2.id.ll_delegate_personalorder_in)
    void inClick() {
        mArgs.putInt(ORDER_CURRENT, 3);
        startOrderListByType();
    }

    @OnClick(R2.id.ll_delegate_personalorder_out)
    void outClick() {
        mArgs.putInt(ORDER_CURRENT, 2);
        startOrderListByType();
    }

    @OnClick(R2.id.ll_delegate_personalorder_truck)
    void truckClick() {
        mArgs.putInt(ORDER_CURRENT, 4);
        startOrderListByType();
    }

    @OnClick(R2.id.ll_delegate_personalorder_doing)
    void doingClick() {
        mArgs.putInt(ORDER_CURRENT, 1);
        startOrderListByType();
    }
    public static final String ORDER_CURRENT = "ORDER_TYPE";
    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @OnClick(R2.id.ll_user_vip)
    void onVipClick() {
        getParentDelegate().getSupportDelegate().start(new VipDelegate());
    }

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder() {
        mArgs.putInt(ORDER_CURRENT, 0);
        startOrderListByType();
    }

    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar() {
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    private void startOrderListByType() {
        final BaseOrderListDelegate delegate = new BaseOrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        final ListBean wallet = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new WalletDelegate())
                .setText("我的钱包")
                .setIcon("{icon-wallet}")
                .build();
        final ListBean team = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new TeamDelegate())
                .setText("我的团队")
                .setIcon("{icon-team}")
                .build();

        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setDelegate(new AddressDelegate())
                .setText("收货地址")
                .setIcon("{icon-address}")
                .build();
        final ListBean accredit = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setDelegate(new AccreditDelegate())
                .setText("授权查询")
                .setIcon("{icon-accredit}")
                .build();
//        final ListBean feedback = new ListBean.Builder()
//                .setItemType(ListItemType.ITEM_NORMAL)
//                .setId(5)
//                .setDelegate(new FeedBackDelegate())
//                .setText("意见反馈")
//                .setIcon("{icon-feedback}")
//                .build();
        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(6)
                .setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .setIcon("{icon-setting}")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(wallet);
        data.add(team);
        data.add(address);
        data.add(accredit);
//        data.add(feedback);
        data.add(system);
        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
        mRvSettings.addOnItemTouchListener(new PersonalClickListener(this));
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void post(Runnable runnable) {

    }
}
