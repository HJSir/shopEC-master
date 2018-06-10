package com.example.ec.main.personal.wallet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ec.main.personal.PersonalClickListener;
import com.example.ec.main.personal.list.ListAdapter;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.list.ListItemType;
import com.example.ec.main.personal.team.TeamDelegate;
import com.example.mi.app.Mi;

import com.example.mi.delegates.MiDelegate;
import com.example.miec.R;
import com.example.miec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by jian
 */
/*
  充值说明：检查是否有充值权限，如果有跳转支付宝进行充值流程，如果没有弹出无权限提示框。
  提现说明：根据自身绑定的银行卡号，弹出框，问提现多少，提交确认后转入后台，提现成功会有推送提示。
  升级代理：获取自身权限，支付相应金额，成功后后台升级权限，

 */
public class WalletDelegate extends MiDelegate {

    private boolean checkLimits = false;
    private boolean isBindBankCard = false;
    @BindView(R2.id.tv_wallet_payment)
    TextView tvPayment = null;
    @BindView(R2.id.tv_wallet_profit)
    TextView tvProfit = null;
    MaterialDialog mMaterialDialog = null;

    @OnClick(R2.id.bt_wallet_explain)
    void onExplainClick() {

        mMaterialDialog.setTitle("说明")
                .setMessage("Hello world!")
                .show();

    }

    @OnClick(R2.id.bt_wallet_pay)
    void onPayClick() {
        if (checkLimits) {
            //执行支付流程
        } else {
            mMaterialDialog.setTitle("提示")
                    .setMessage("您没有权限执行充值操作\n总代以下可以申请划账得到货款")
                    .show();
        }
    }

    @OnClick(R2.id.bt_wallet_deposit)
    void onDepositClick() {
//检查是否绑定银行卡
        isBindBankCard=true;
        if (isBindBankCard) {
            getSupportDelegate().start(new UserDepositDelegate());

        } else {
            mMaterialDialog.setTitle("提示")
                    .setMessage("请先绑定银行卡！")
                    .show();
        }
    }


    @BindView(R2.id.rv_wallet_in)
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

        getSupportDelegate().start(new BlanceDetailDelegate());
    }

    @Override
    public Object setLayout() {

        return R.layout.delegate_wallet;
    }

    void init() {
        mMaterialDialog = new MaterialDialog(getContext());
        mMaterialDialog.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();

            }
        });
        topMidText.setText("我的钱包");
        topRightText.setText("余额明细");
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        init();
        final ListBean up = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new UpGradeDelegate())
                .setText("升级代理")
                .build();
        final ListBean remit = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new RemitAccountDelegate())
                .setText("申请划账")
                .setValue("代理以下只可申请划账得到货款")
                .build();
        final ListBean bindCard = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setDelegate(new BindBankCardDelegate())
                .setText("银行卡管理")
                .setValue("绑定银行卡或解绑银行卡")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(up);
        data.add(remit);
        data.add(bindCard);
        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
                getSupportDelegate().start(bean.getDelegate());
            }
        });
    }

    @Override
    public void post(Runnable runnable) {

    }
}
