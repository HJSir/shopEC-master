package com.example.ec.main.personal.order;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mi.ui.recycler.DataConverter;
import com.example.mi.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by jian
 */

public class TruckFindListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {


        final JSONArray array = JSON.parseObject(getJsonData()).getJSONObject("result").getJSONArray("list");


        final int size = array.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = array.getJSONObject(i);
            final String datetime = data.getString("datetime");
            final String remark = data.getString("remark");


            //头部
            final MultipleItemEntity headEntity = MultipleItemEntity.builder()
                    .setItemType(TruckFindFields.TRUCK_TYPE)
                    .setField(TruckFindListType.TIME, datetime)
                    .setField(TruckFindListType.CONTENT, remark)

                    .build();

            ENTITIES.add(headEntity);



        }
        return ENTITIES;
    }
}
