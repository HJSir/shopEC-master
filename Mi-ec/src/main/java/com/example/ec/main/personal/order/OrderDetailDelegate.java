package com.example.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ec.main.personal.address.AddressItemFields;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class OrderDetailDelegate extends MiDelegate implements ISuccess{

    @BindView(R2.id.tv_order_detail_address)
          TextView address=null;
    @BindView(R2.id.tv_order_detail_alipayid)
    TextView otherPayId=null;
    @BindView(R2.id.tv_order_detail_allgoodsprice)
    TextView allGoodsPrice=null;
    @BindView(R2.id.tv_order_detail_createtime)
    TextView createTime=null;
    @BindView(R2.id.tv_order_detail_orderid)
    TextView orderId=null;
    @BindView(R2.id.tv_order_detail_orderstatues)
    TextView OrderStatues=null;
    @BindView(R2.id.tv_order_detail_paydetail)
    TextView payDetail=null;
    @BindView(R2.id.tv_order_detail_phonenumber)
    TextView phoneNumber=null;
    @BindView(R2.id.tv_order_detail_recivegoods)
    TextView reciveGoodsName=null;
    @BindView(R2.id.tv_order_detail_truck)
    TextView truckPrice=null;

    @BindView(R2.id.rv_order_detail)
    RecyclerView mRecyclerView=null;



//头部 **
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }
    //**
        @Override
    public Object setLayout() {
        return R.layout.delegate_order_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("订单详情");
        RestClient.builder().loader(getContext()).url("hdored").success(this).build().get();

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onSuccess(String response) {

        ArrayList<MultipleItemEntity> data = new OrderDetailDataConverter().setJsonData(response).convert();



        address.setText(data.get(0).getField(AddressItemFields.ADDRESS).toString());
        otherPayId.setText("支付ID:"+data.get(0).getField(OrderItemFields.ORDER_OHTER_PAY_ID).toString());
        allGoodsPrice.setText(data.get(0).getField(OrderItemFields.ORDER_PAYMENT_GOODS).toString()+"元");
        createTime.setText("下单时间:"+data.get(0).getField(OrderItemFields.ORDER_CREATE_TIME).toString());
        orderId.setText("订单号："+data.get(0).getField(OrderItemFields.ORDER_ID).toString());
        OrderStatues.setText(data.get(0).getField(OrderItemFields.ORDER_STATUES).toString());

        payDetail.setText(
                "共需要支付（含运费）："+
                data.get(0).getField(OrderItemFields.ALL_PAY).toString()+
                        "元 其中现金支付："+ data.get(0).getField(OrderItemFields.ORDER_OHTER_PAY_PRICE).toString()+
                        "元 货款支付："+ data.get(0).getField(OrderItemFields.ORDER_PAYMENT_GOODS).toString()+" 元"


        );

        phoneNumber.setText("联系电话："+data.get(0).getField(AddressItemFields.PHONE).toString());
        reciveGoodsName.setText("收货人："+data.get(0).getField(AddressItemFields.NAME).toString());
        truckPrice.setText(data.get(0).getField(OrderItemFields.ORDER_FREIGHT).toString());
        data.remove(0);
        Log.i("Tag",""+data.size());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new OrderListAdapter(data,OrderDetailDelegate.this));
    }
}
