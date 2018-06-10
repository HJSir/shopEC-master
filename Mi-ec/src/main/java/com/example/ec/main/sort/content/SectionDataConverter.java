package com.example.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by jian
 */

public class SectionDataConverter {

    final List<SectionBean> convert(String json) {

        final List<SectionBean> datalist = new ArrayList<>();
        final JSONObject data = JSON.parseObject(json).getJSONObject("data");





            final int id = data.getInteger("id");
            final String title = data.getString("headImageUrl");

            //添加title
            final SectionBean sectionTitleBean = new SectionBean(true, title);
            sectionTitleBean.setId(id);
            sectionTitleBean.setIsMore(true);
            datalist.add(sectionTitleBean);
//            Log.i("Tag",dataSize+" "+title);
            final JSONArray goods = data.getJSONArray("goods");
            //商品内容循环
            final int goodSize = goods.size();
            for (int j = 0; j < goodSize; j++) {
                final com.alibaba.fastjson.JSONObject contentItem = goods.getJSONObject(j);
                final int goodsId = contentItem.getInteger("goods_id");
                final String goodsName = contentItem.getString("goods_name");
                final String goodsThumb = contentItem.getString("goods_thumb");
                //获取内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setGoodsId(goodsId);
                itemEntity.setGoodsName(goodsName);
                itemEntity.setGoodsThumb(goodsThumb);
                //添加内容
                datalist.add(new SectionBean(itemEntity));
            }
            //商品内容循环结束




        return datalist;
    }
}
