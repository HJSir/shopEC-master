package com.example.ec.main.personal.order;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ec.main.personal.address.AddressItemFields;
import com.example.mi.ui.recycler.DataConverter;
import com.example.mi.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by jian
 */

public class OrderDetailDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONObject data = JSON.parseObject(getJsonData()).getJSONObject("data");


        final String orderId = data.getString("orderId");
        final String shouldPayAll = data.getString("shouldPayAll");
        final String orderStatus = data.getString("orderStatus");
        final String typeName = data.getString("typeNmae");
        final String orderTime = data.getString("orderTime");
//            final String orderQuantity=data.getString("orderQuantity");
        final String freight = data.getString("freight");
        final String ohterPayId = data.getString("otherPayId");
        final String ohterPay = data.getString("ohterPay");
        final String paymentOfGoods = data.getString("paymentOfGoods");
        final String address = data.getString("address");
        final String reciveGoodsName = data.getString("reciveGoodsName");
        final String phoneNumber = data.getString("phoneNumber");
        JSONArray listArray = data.getJSONArray("orderItem");
        ;
        final int size = listArray.size();

        final MultipleItemEntity headEntity = MultipleItemEntity.builder()
                .setItemType(OrderListItemType.ORDER_DETAIL_NORMAL)
                .setField(OrderItemFields.ORDER_STATUES, orderStatus)
                .setField(OrderItemFields.ORDER_ID, orderId)
                .setField(OrderItemFields.ORDER_TYPE_NAME, typeName)
                .setField(OrderItemFields.ORDER_CREATE_TIME, orderTime)
                .setField(OrderItemFields.ORDER_OHTER_PAY_ID, ohterPayId)
                .setField(OrderItemFields.ORDER_OHTER_PAY_PRICE, ohterPay)
                .setField(OrderItemFields.ORDER_PAYMENT_GOODS, paymentOfGoods)
                .setField(AddressItemFields.ADDRESS, address)
                .setField(AddressItemFields.NAME, reciveGoodsName)
                .setField(AddressItemFields.PHONE, phoneNumber)
                .setField(OrderItemFields.ORDER_FREIGHT, freight)
                .setField(OrderItemFields.ALL_PAY, shouldPayAll)
                .setField(OrderItemFields.FLAG,"detail")

                .build();

        ENTITIES.add(headEntity);


        for (int j = 0; j < size; j++) {
            final JSONObject list = listArray.getJSONObject(j);

            final String goodsImage = list.getString("goodsImage");
            final String goodsPrice = list.getString("goodsPrice");
            final String goodsName = list.getString("goodsName");
            final String goodsId = list.getString("goodsId");
            final String quantity = list.getString("quantity");
            final MultipleItemEntity contentEntity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST_ALL_CONTENT)
                    .setField(OrderItemFields.GOODS_PRICE, goodsPrice)
                    .setField(OrderItemFields.GOODS_NAME, goodsName)
                    .setField(OrderItemFields.GOODS_ID, goodsId)
                    .setField(OrderItemFields.GOODS_QUANTITY, quantity)
                    .setField(OrderItemFields.GOODS_IMAGE, goodsImage)
                    .setField(OrderItemFields.FLAG,"detail")
                    .build();

            ENTITIES.add(contentEntity);

        }


        return ENTITIES;
    }
}
