package com.example.ec.main;

import android.graphics.Color;


import com.example.ec.main.cart.ShopCartDelegate;
import com.example.ec.main.index.IndexDelegate;
import com.example.ec.main.message.MessageDelegate;
import com.example.ec.main.personal.PersonalDelegate;
import com.example.ec.main.sort.SortDelegate;
import com.example.mi.delegates.bottom.BaseBottomDelegate;
import com.example.mi.delegates.bottom.BottomItemDelegate;
import com.example.mi.delegates.bottom.BottomTabBean;
import com.example.mi.delegates.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * Created by jian
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());

        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-comments}","消息"),new MessageDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"),new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }

    @Override
    public void post(Runnable runnable) {

    }
}
