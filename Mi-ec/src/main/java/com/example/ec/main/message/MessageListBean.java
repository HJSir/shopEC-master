package com.example.ec.main.message;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.mi.delegates.MiDelegate;

/**
 * Created by jian
 */

public class MessageListBean implements MultiItemEntity {
   private String mIconFont="";
    private int mItemType = 0;

    private String mText = null;
    private String mValue = null;
    private String mNumber=null;

    public String getNumber() {
        return mNumber;
    }

    private int mId = 0;
    private MiDelegate mDelegate = null;


    public MessageListBean(String mNumber, String iconFont, int itemType,  String text, String value, int id, MiDelegate delegate) {
        mIconFont = iconFont;
        mItemType = itemType;

        mText = text;
        mValue = value;
        mId = id;
        mDelegate = delegate;

        this.mNumber=mNumber;
    }

    public String getIconFont() {
        return mIconFont;
    }

    @Override
    public int getItemType() {
        return mItemType;
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



    public static final class Builder {
  private String mNumber=null;
        private int id = 0;
        private int itemType = 0;

        private String text = null;
        private String value = null;

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


        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }



        public Builder setDelegate(MiDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public MessageListBean build() {
            return new MessageListBean(mNumber,icon,itemType, text, value, id, delegate);
        }
    }
}
