package com.example.ec.main.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mi.ui.recycler.DataConverter;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;


/**
 * Created by jian
 */

public class OrderListDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {
             JSONObject data = array.getJSONObject(i);

            final String orderId = data.getString("orderId");
            final String shouldPayAll = data.getString("shouldPayAll");
            final int orderStatus = data.getInteger("orderStatus");
            final String typeName=data.getString("typeNmae");
            final String orderTime=data.getString("orderTime");
            final String orderQuantity=data.getString("orderQuantity");
            final String freight =data.getString("freight");
            //头部
            final MultipleItemEntity headEntity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST_ALL_HEADER)
                    .setField(OrderItemFields.ORDER_STATUES, orderStatus)
                    .setField(OrderItemFields.ORDER_ID, orderId)
                    .setField(OrderItemFields.ORDER_TYPE_NAME,typeName)
                    .setField(OrderItemFields.ORDER_CREATE_TIME,orderTime)

                    .build();

            ENTITIES.add(headEntity);
 //中间内容

             JSONArray listArray=data.getJSONArray("orderItem");

            for(int j=0;j<listArray.size();j++){
                final JSONObject list = listArray.getJSONObject(j);

                final String goodsImage = list.getString("goodsImage");
                final String goodsPrice = list.getString("goodsPrice");
                final String goodsName = list.getString("goodsName");
                final String goodsId = list.getString("goodsId");
                final String quantity = list.getString("quantity");
                final MultipleItemEntity contentEntity = MultipleItemEntity.builder()
                        .setField(OrderItemFields.FLAG,"list")
                        .setItemType(OrderListItemType.ITEM_ORDER_LIST_ALL_CONTENT)
                        .setField(OrderItemFields.GOODS_PRICE, goodsPrice)
                        .setField(OrderItemFields.GOODS_NAME, goodsName)
                        .setField(OrderItemFields.GOODS_ID, goodsId)
                        .setField(OrderItemFields.GOODS_QUANTITY, quantity)
                        .setField(OrderItemFields.GOODS_IMAGE,goodsImage)

                        .build();

                ENTITIES.add(contentEntity);

            }

            final MultipleItemEntity footerEntity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST_ALL_FOOTER)
                    .setField(OrderItemFields.ALL_PAY, shouldPayAll)
                    .setField(OrderItemFields.ORDER_STATUES, orderStatus)
                    .setField(OrderItemFields.ORDER_QUANTITY,orderQuantity)
                    .setField(OrderItemFields.ORDER_FREIGHT,freight)
                    .build();
            ENTITIES.add(footerEntity);
        }

        return ENTITIES;
    }
}
