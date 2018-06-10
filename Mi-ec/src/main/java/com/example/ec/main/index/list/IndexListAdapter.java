package com.example.ec.main.index.list;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.mi.ui.recycler.MultipleRecyclerAdapter;
import com.example.mi.ui.recycler.MultipleViewHolder;
import com.example.miec.R;

import java.util.List;

/**
 * Created by jian
 */

public class IndexListAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> {
    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    protected IndexListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(IndexListItemType.LIST_ACTIVITY, R.layout.item_multiple_activity_goods);
        addItemType(IndexListItemType.LIST_HOT, R.layout.item_list_hot_goods);
        addItemType(IndexListItemType.LIST_NEW, R.layout.item_multiple_new_goods);
        addItemType(IndexListItemType.LIST_NEWS, R.layout.item_list_news);
    }


    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {

        String imageUrl;
        imageUrl = entity.getField(MultipleFields.GOODS_IMAGE_URL);
        switch (entity.getItemType()) {

            case IndexListItemType.LIST_ACTIVITY:
                holder.setText(com.example.mi.R.id.tv_multiple_activity_info, entity.getField(MultipleFields.TEXT).toString());
                holder.setText(com.example.mi.R.id.tv_multiple_activity_time, "从" + entity.getField(MultipleFields.START_TIME).toString() +
                        "开始 到" + entity.getField(MultipleFields.END_TIME).toString() + "结束"
                );

                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(com.example.mi.R.id.tv_multiple_activity_image));

                break;
            case IndexListItemType.LIST_HOT:

                holder.setText(com.example.mi.R.id.tv_list_hot_name, entity.getField(MultipleFields.NAME).toString());
                holder.setText(com.example.mi.R.id.tv_list_hot_info, entity.getField(MultipleFields.TEXT).toString());
                holder.setText(com.example.mi.R.id.tv_list_hot_price,entity.getField(MultipleFields.PRICE).toString()+"元/"+
                        entity.getField(MultipleFields.UNIT).toString()
                );


                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(com.example.mi.R.id.tv_list_hot_image));
                break;
            case IndexListItemType.LIST_NEW:

                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.tv_multiple_new_image));
                break;
            case IndexListItemType.LIST_NEWS:
                imageUrl=entity.getField(MultipleFields.NEWS_IMAGE_URL);
                holder.setText(R.id.tv_list_news_info, entity.getField(MultipleFields.NEWS_INFO).toString());

                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.tv_list_news_image));

                break;
            case IndexListItemType.LIST_STUDY:
                break;
            default:
                break;
        }

    }
}
