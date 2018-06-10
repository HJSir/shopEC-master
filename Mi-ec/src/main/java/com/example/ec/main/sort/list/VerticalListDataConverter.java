package com.example.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mi.ui.recycler.DataConverter;
import com.example.mi.ui.recycler.ItemType;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;


import java.util.ArrayList;

/**
 * Created by jian
 */

public final class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> datalist = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData())
                .getJSONArray("data");
        final int dataSize = dataArray.size();


        for (int i = 0; i < dataSize; i++) {

            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TAG, false)
                    .setField(MultipleFields.NAME, name).build();
datalist.add(entity);
datalist.get(0).setField(MultipleFields.TAG,true);

        }

        return datalist;
    }
}
