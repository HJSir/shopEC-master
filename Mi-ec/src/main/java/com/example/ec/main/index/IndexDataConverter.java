package com.example.ec.main.index;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mi.delegates.bottom.ItemBuilder;
import com.example.mi.ui.recycler.DataConverter;
import com.example.mi.ui.recycler.ItemType;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

import static com.example.mi.ui.recycler.IdexIconFont.IS_SHOW_ICON;

/**
 * Created by jian
 */

public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        boolean shouldBtn = true;
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String title = data.getString("title");
            final String imageUrl = data.getString("imageUrl");
            int spanSize ;
            final String id = data.getString("Id");
            final String layoutType = data.getString("type");
            final JSONArray banners = data.getJSONArray("banners");
            final JSONArray goods = data.getJSONArray("goods");
            final String skipUrl = data.getString("skip_url");


            int type = 0;
          if (banners != null) {
                final ArrayList<String> bannerImages = new ArrayList<>();
                final ArrayList<String> bannerSkip = new ArrayList<>();
                spanSize = 10;
                type = ItemType.BANNER;
                //Banner的初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {

                    bannerImages.add(banners.getJSONObject(j).getString("banner_image_url"));
                    bannerSkip.add(banners.getJSONObject(j).getString("banner_image_skip_url"));
                }


                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setField(MultipleFields.ITEM_TYPE, type)
                        .setField(MultipleFields.SPAN_SIZE, spanSize)
                        .setField(MultipleFields.ID, id)
                        .setField(MultipleFields.IMAGE_URL, imageUrl)
                        .setField(MultipleFields.BANNERS, bannerImages)
                        .setField(MultipleFields.SKIP_URL, skipUrl)
                        .setField(MultipleFields.BANNERS_SKIP, bannerSkip)

                        .build();

                ENTITIES.add(entity);
            }

            if ( shouldBtn ) {
                type = ItemType.ICON;
                spanSize = 2;
                for (int j=0;j<5;j++) {
                    final MultipleItemEntity entity4 = MultipleItemEntity.builder()
                            .setField(MultipleFields.ITEM_TYPE, type)
                            .setField(MultipleFields.SPAN_SIZE, spanSize)
                            .setField(MultipleFields.ICON, j)
                            .build();
                    ENTITIES.add(entity4);
                    shouldBtn=false;

                }

            }

            if (goods != null) {
                final MultipleItemEntity entity3 = MultipleItemEntity.builder()
                        .setField(MultipleFields.ITEM_TYPE, ItemType.TITLE)
                        .setField(MultipleFields.SPAN_SIZE, 10)
                        .setField(MultipleFields.TITLE, title)
                        .setField(MultipleFields.ITEM_CONTENT_TYPE, layoutType) //设置内容type 跳转标准
                        .build();
                ENTITIES.add(entity3);
                if(Integer.valueOf(layoutType)== ItemType.HOT)
                {
                    spanSize=5;
                }else{
                    spanSize=10;
                }
                for (int j = 0; j < goods.size(); j++) {
                    final MultipleItemEntity entity2 = MultipleItemEntity.builder()

                            .setField(MultipleFields.ITEM_TYPE, Integer.valueOf(layoutType))
                            .setField(MultipleFields.SPAN_SIZE, spanSize)
                            .setField(MultipleFields.START_TIME, goods.getJSONObject(j).getString("goodsStartTime"))
                            .setField(MultipleFields.END_TIME, goods.getJSONObject(j).getString("goodsEndTime"))
                            .setField(MultipleFields.TEXT, goods.getJSONObject(j).getString("goodsInfo"))
                            .setField(MultipleFields.GOODS_IMAGE_URL, goods.getJSONObject(j).getString("goodsImage"))
                            .setField(MultipleFields.NAME,goods.getJSONObject(j).getString("goodsName"))
                            .setField(MultipleFields.PRICE,goods.getJSONObject(j).getString("goodsPrice"))
                            .setField(MultipleFields.UNIT,goods.getJSONObject(j).getString("unit"))
                            .setField(MultipleFields.SURPLUS,goods.getJSONObject(j).getString("surplus"))
                            .build();
                    ENTITIES.add(entity2);
                }

            }


        }
//        IS_SHOW_ICON = true;

        return ENTITIES;
    }
}
