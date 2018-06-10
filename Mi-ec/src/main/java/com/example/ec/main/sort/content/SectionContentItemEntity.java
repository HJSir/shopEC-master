package com.example.ec.main.sort.content;

/**
 * Created by jian
 */

public class SectionContentItemEntity {

    private int goodsId=0;
    private String mGoodsName=null;
    private String mGoodsThumb=null;

    public String getGoodsName() {
        return mGoodsName;
    }

    public void setGoodsName(String goodsName) {
        mGoodsName = goodsName;
    }

    public String getGoodsThumb() {
        return mGoodsThumb;
    }

    public void setGoodsThumb(String goodsThumb) {
        mGoodsThumb = goodsThumb;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }
}
