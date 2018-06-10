package com.example.ec.main.index.list;

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

public class IndexListDataConverter extends DataConverter{
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONObject jsonObject = JSON.parseObject(getJsonData());
        final String type=jsonObject.getString("type");

        final JSONArray data =jsonObject.getJSONArray("data"); //新闻

        for (int j = 0; j < data.size(); j++) {

            final MultipleItemEntity entity2 = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, Integer.valueOf(type))
                    .setField(MultipleFields.START_TIME, data.getJSONObject(j).getString("goodsStartTime"))
                    .setField(MultipleFields.END_TIME, data.getJSONObject(j).getString("goodsEndTime"))
                    .setField(MultipleFields.TEXT, data.getJSONObject(j).getString("goodsInfo"))
                    .setField(MultipleFields.GOODS_IMAGE_URL, data.getJSONObject(j).getString("goodsImage"))
                    .setField(MultipleFields.NEWS_IMAGE_URL, data.getJSONObject(j).getString("imageUrl"))
                    .setField(MultipleFields.NAME,data.getJSONObject(j).getString("goodsName"))
                    .setField(MultipleFields.PRICE,data.getJSONObject(j).getString("goodsPrice"))
                    .setField(MultipleFields.UNIT,data.getJSONObject(j).getString("unit"))
                    .setField(MultipleFields.SURPLUS,data.getJSONObject(j).getString("surplus"))
                    .setField(MultipleFields.NEWS_INFO,data.getJSONObject(j).getString("newsInfo"))
                    .setField(MultipleFields.SKIP_URL,data.getJSONObject(j).getString("skip_url"))
                    .setField(MultipleFields.ID,data.getJSONObject(j).getString("id"))
                    .build();
            ENTITIES.add(entity2);
        }


        return ENTITIES;
    }
}
