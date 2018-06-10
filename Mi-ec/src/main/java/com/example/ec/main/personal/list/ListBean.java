package com.example.ec.main.personal.list;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.mi.delegates.MiDelegate;

/**
 * Created by jian
 */
public class ListBean implements MultiItemEntity {
   private String mIconFont="";
    private int mItemType = 0;
    private String mImageUrl = null;
    private String mText = null;
    private String mValue = null;
    private String mNumber=null;

    public String getNumber() {
        return mNumber;
    }

    private int mId = 0;
    private MiDelegate mDelegate = null;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;

    public ListBean(String mNumber,String iconFont, int itemType, String imageUrl, String text, String value, int id, MiDelegate delegate, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        mIconFont = iconFont;
        mItemType = itemType;
        mImageUrl = imageUrl;
        mText = text;
        mValue = value;
        mId = id;
        mDelegate = delegate;
        mOnCheckedChangeListener = onCheckedChangeListener;
        this.mNumber=mNumber;
    }

    public String getIconFont() {
        return mIconFont;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getText() {
        return mText;
    }

    public String getValue() {
        return mValue;
    }

    public int getId() {
        return mId;
    }

    public MiDelegate getDelegate() {
        return mDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public static final class Builder {
  private String mNumber=null;
        private int id = 0;
        private int itemType = 0;
        private String imageUrl = null;
        private String text = null;
        private String value = null;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;
        private MiDelegate delegate = null;
private String icon=null;
        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setNumber(String Number) {
            this.mNumber = Number;
            return this;
        }
        public Builder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public Builder setDelegate(MiDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public ListBean build() {
            return new ListBean(mNumber,icon,itemType, imageUrl, text, value, id, delegate, onCheckedChangeListener);
        }
    }
}
