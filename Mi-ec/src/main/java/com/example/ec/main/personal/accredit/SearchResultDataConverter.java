package com.example.ec.main.personal.accredit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ec.main.personal.address.AddressItemFields;
import com.example.ec.main.personal.address.AddressItemType;
import com.example.mi.ui.recycler.DataConverter;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by jian
 */

public class SearchResultDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {

            final JSONObject data = array.getJSONObject(i);
            final String id = data.getString("id");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String level = data.getString("level");
            final String higher = data.getString("higher");
            final String brand =data.getString("brand");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(AccreditItemType.ACCREDIT_TYPE_NORMAL)
                    .setField(AccreditItemFields.BRAND,brand)
                    .setField(AccreditItemFields.ID, id)
                    .setField(AccreditItemFields.NAME, name)
                    .setField(AccreditItemFields.LEVEL, level)
                    .setField(AccreditItemFields.HIGHER, higher)
                    .setField(AccreditItemFields.PHONE, phone)
                    .build();
            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
