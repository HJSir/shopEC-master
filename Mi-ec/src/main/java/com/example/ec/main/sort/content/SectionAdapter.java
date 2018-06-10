package com.example.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ec.detail.GoodsDetailDelegate;
import com.example.mi.delegates.MiDelegate;
import com.example.miec.R;


import java.util.List;

/**
 * Created by jian
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {
    MiDelegate DELEGATE;
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data,  MiDelegate DELEGATE) {
        super(layoutResId, sectionHeadResId, data);
        this.DELEGATE=DELEGATE;
    }


    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
//        Log.i("tag","--"+item.header+"");
        final AppCompatImageView goodsImageView = helper.getView(R.id.iv_sort_head);
        Glide.with(mContext)
                .load(item.header)
                .into(goodsImageView);
        goodsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(1);
                DELEGATE.getSupportDelegate().start(delegate);
            }
        });

    }

    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {

        //item.t返回SectionBean类型
        final String thumb = item.t.getGoodsThumb();
        final String name = item.t.getGoodsName();
        final int goodsId = item.t.getGoodsId();
//        Log.i("tag","--"+thumb+name+goodsId+"");
        final SectionContentItemEntity entity = item.t;
        helper.setText(R.id.tv, name);
        final AppCompatImageView goodsImageView = helper.getView(R.id.iv);

        goodsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(1);
                DELEGATE.getSupportDelegate().start(delegate);
            }
        });
        Glide.with(mContext)
                .load(thumb)
                .into(goodsImageView);
    }
}
