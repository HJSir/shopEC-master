package com.example.ec.settle;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.ec.main.personal.order.OrderDetailDataConverter;
import com.example.ec.main.personal.order.OrderListAdapter;
import com.example.ec.main.personal.order.OrderListDataConverter;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.miec.R;
import com.example.miec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class SettleDelegate extends MiDelegate {

    public int PAY_WAY = 0; //0默认无选择,1微信支付，2支付宝支付
    public boolean IS_GOODS_PAY = false; //货款支付默认关闭
    public double goodsTotalPrice = 0; //商品总价
    public double wayPrice = 0; //运费
    public double goodsPayPrice = 0;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }

    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @BindView(R2.id.rv_settle_goodslist)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.tv_settle_adress)
    TextView addressTextView = null;
    @BindView(R2.id.tv_settle_adress_phone_Name)
    TextView phoneAndNameTextView = null;
    @BindView(R2.id.tv_settle_icon_choose_alipay)
    IconTextView alipayIcon = null;
    @BindView(R2.id.tv_settle_icon_choose_wxpay)
    IconTextView wxpayIcon = null;
    @BindView(R2.id.tv_settle_goods_price_all)
    TextView goodsTotalPriceTextView = null;
    @BindView(R2.id.tv_settle_total_price)
    TextView totalPriceTextView = null;
    @BindView(R2.id.tv_settle_way_price)
    TextView wayAndPriceTextView = null;
    @BindView(R2.id.tv_arrow_switch_text)
    AppCompatTextView goodsPayPriceTextView = null;
    @BindView(R2.id.tv_settle_ohterPayPrice)
    TextView otherPayPriceTextView = null;


    @OnClick(R2.id.rl_settle_adress)
    void adressOnclick() {

    }

    @OnClick(R2.id.rl_settle_way)
    void wayOnclick() {

    }

    @BindView(R2.id.list_item_switch)
    SwitchCompat goodsPayIcon = null;


    @OnClick(R2.id.rl_settle_wxpay)
    void wxPayOnclick() {
        if (PAY_WAY == 1) {
            wxpayIcon.setTextColor(Color.GRAY);
            PAY_WAY = 0;
        } else {
            PAY_WAY = 1;
            wxpayIcon.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            alipayIcon.setTextColor(Color.GRAY);
        }

    }


    @OnClick(R2.id.rl_settle_alipay)
    void aliPayOnclick() {
        if (PAY_WAY == 2) {
            alipayIcon.setTextColor(Color.GRAY);
            PAY_WAY = 0;
        } else {
            PAY_WAY = 2;
            alipayIcon.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            wxpayIcon.setTextColor(Color.GRAY);
        }
    }

    @OnClick(R2.id.tv_settle_pay)
    void payOnClick() {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_settle;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("订单结算");
        initData();
        setData();
    }

    void initData() {
        goodsPayPrice = 100;
        goodsTotalPrice = 400;
        wayPrice = 20;
        RestClient.builder().url("hdored").success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                ArrayList<MultipleItemEntity> LIST= new OrderDetailDataConverter().setJsonData(response).convert();
                LIST.remove(0);
                mRecyclerView.setAdapter(new OrderListAdapter(LIST,SettleDelegate.this));
            }
        }).build().get();

    }

    //初始化页面数据
    void setData() {
        goodsPayIcon.setChecked(false);
        goodsPayPriceTextView.setText("目前可用货款为：100元");
        otherPayPriceTextView.setText((goodsTotalPrice + wayPrice) + "元");
        goodsPayIcon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean b) {
                if (b) {
                    IS_GOODS_PAY = true;
                    goodsPayPriceTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
                    //打开货款支付后，需要更改现金支付金额
                    otherPayPriceTextView.setText((goodsTotalPrice - goodsPayPrice + wayPrice) + "元");
                } else {
                    IS_GOODS_PAY = false;
                    goodsPayPriceTextView.setTextColor(Color.GRAY);
                    //关闭货款支付后，需要更改现金支付金额
                    otherPayPriceTextView.setText((goodsTotalPrice + wayPrice) + "元");
                }
            }
        });

        goodsTotalPriceTextView.setText(goodsTotalPrice + "元");
        totalPriceTextView.setText("¥" + (goodsTotalPrice + wayPrice));
        wayAndPriceTextView.setText("快递（运费20元）");



    }

    @Override
    public void post(Runnable runnable) {

    }


}
