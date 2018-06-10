package com.example.ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by jian
 */

public class SectionBean extends SectionEntity<SectionContentItemEntity> {
    public boolean isMore() {
        return mIsMore;
    }

    public void setIsMore(boolean more) {
        mIsMore = more;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    private boolean mIsMore=false;
    private int mId=-1;
    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionBean(SectionContentItemEntity entity) {
        super(entity);
    }
}
