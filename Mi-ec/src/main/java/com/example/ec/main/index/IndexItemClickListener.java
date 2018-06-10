package com.example.ec.main.index;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.ec.detail.GoodsDetailDelegate;
import com.example.ec.main.index.list.IndexListDelegate;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.ui.recycler.ItemType;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.mi.ui.webview.MiWebViewDelegate;


/**
 * Created by jian
 */

public class IndexItemClickListener extends SimpleClickListener implements IIndexItemListener {

    private final MiDelegate DELEGATE;

    private IndexItemClickListener(MiDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static IndexItemClickListener create(MiDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
//        final int iconId = entity.getField(MultipleFields.ICON);
        final int itemType = entity.getField(MultipleFields.ITEM_TYPE);
//        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(1);
        final String skip = entity.getField(MultipleFields.SKIP_URL);
//        DELEGATE.getSupportDelegate().start(delegate);
        if (itemType == ItemType.BANNER && entity.getField(MultipleFields.BANNERS) != null && skip != null) {
            //说明这是顶部广告 ，跳转url

            MiWebViewDelegate delegate = new MiWebViewDelegate();
            Bundle bundle = new Bundle();
            bundle.putString("title", "详情");
            bundle.putString("url", skip);
            delegate.setArguments(bundle);
            DELEGATE.getSupportDelegate().start(delegate);

            return;
        } else if (itemType == ItemType.ICON) {
            final int iconId = entity.getField(MultipleFields.ICON);
            if (iconId != 3) {
                //跳转三个List列表
                IndexListDelegate listDelegate = new IndexListDelegate();
                Bundle bundle = new Bundle();
                bundle.putInt("layoutType", iconId + 1);
                listDelegate.setArguments(bundle);
                DELEGATE.getSupportDelegate().start(listDelegate);
            } else {
                //跳转学习

            }

        } else if (itemType >= 1 && itemType <= 3) {
            //说明这是下面的三个商品,跳转detail
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(1);
        DELEGATE.getSupportDelegate().start(delegate);
        }


    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }

    //title的全部按钮 点击事件
    @Override
    public void onItemClick(String type) {
        //跳转三个List列表
        IndexListDelegate listDelegate = new IndexListDelegate();
        Bundle bundle = new Bundle();
        bundle.putInt("layoutType", Integer.valueOf(type));
        listDelegate.setArguments(bundle);
        DELEGATE.getSupportDelegate().start(listDelegate);
    }

    @Override
    public void onBannerItemClick(String url) {
        //顶部banner点击事件
        MiWebViewDelegate delegate =new MiWebViewDelegate();
        Bundle bundle =new Bundle();
        bundle.putString("title","详情");
        bundle.putString("url",url);
        delegate.setArguments(bundle);
        DELEGATE.getSupportDelegate().start(delegate);
    }
}
