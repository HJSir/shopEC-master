package com.example.ec.main.index;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mi.app.Mi;
import com.example.mi.ui.banner.BannerCreator;
import com.example.mi.ui.recycler.ItemType;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.mi.ui.recycler.MultipleRecyclerAdapter;
import com.example.mi.ui.recycler.MultipleViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.example.mi.ui.recycler.IdexIconFont.ICON_FONT;
import static com.example.mi.ui.recycler.IdexIconFont.ICON_TEXT;

/**
 * Created by jian
 */

public class IndexAdapter extends MultipleRecyclerAdapter  implements
        BaseQuickAdapter.SpanSizeLookup {

    public void setTitleItemListener(IIndexItemListener titleItemListener) {
        mTitleItemListener = titleItemListener;
    }

    IIndexItemListener mTitleItemListener =null;
    //确保初始化一次Banner，防止重复Item加载
    private boolean mIsInitBanner = false;
    //设置图片加载策略
    protected IndexAdapter(List<MultipleItemEntity> data) {

        super(data);

        //设置宽度监听
        setSpanSizeLookup(this);
        addItemType(ItemType.ICON, com.example.mi.R.layout.bottom_item_icon_text_layout);
        addItemType(ItemType.NEW, com.example.mi.R.layout.item_multiple_new_goods);
        addItemType(ItemType.ACTIVITY, com.example.mi.R.layout.item_multiple_activity_goods);
        addItemType(ItemType.HOT, com.example.mi.R.layout.item_multiple_hot_goods);
        addItemType(ItemType.TITLE, com.example.mi.R.layout.item_multiple_title_goods);
        addItemType(ItemType.BANNER, com.example.mi.R.layout.item_multiple_banner);
    }
    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {


        final String imageUrl;

        final ArrayList<String> bannerImages;
        final ArrayList<String> bannerSkip;
        imageUrl=entity.getField(MultipleFields.GOODS_IMAGE_URL);
        switch (holder.getItemViewType()) {
            case ItemType.NEW:

//                holder.setText(R.id.tv_multiple_new_info, entity.getField(MultipleFields.TEXT).toString());
//                holder.setText(R.id.tv_multiple_new_price, entity.getField(MultipleFields.PRICE).toString());

                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(com.example.mi.R.id.tv_multiple_new_image));
                break;
            case ItemType.ICON:

                holder.setText(com.example.mi.R.id.icon_bottom_item, ICON_FONT[(int) entity.getField(MultipleFields.ICON)]);
                holder.setText(com.example.mi.R.id.tv_bottom_item, ICON_TEXT[(int) entity.getField(MultipleFields.ICON)]);
                break;
            case ItemType.ACTIVITY:

                holder.setText(com.example.mi.R.id.tv_multiple_activity_info, entity.getField(MultipleFields.TEXT).toString());
                holder.setText(com.example.mi.R.id.tv_multiple_activity_time,"从"+ entity.getField(MultipleFields.START_TIME).toString()+
                        "开始 到"+entity.getField(MultipleFields.END_TIME).toString()+"结束"
                );

                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(com.example.mi.R.id.tv_multiple_activity_image));
                break;
            case ItemType.TITLE:
                holder.setText(com.example.mi.R.id.tv_multiple_title, entity.getField(MultipleFields.TITLE).toString());
                AppCompatTextView textView = holder.getView(com.example.mi.R.id.tv_multiple_title_all);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mTitleItemListener.onItemClick(entity.getField(MultipleFields.ITEM_CONTENT_TYPE).toString());
                    }
                });
                break;
            case ItemType.HOT:
                holder.setText(com.example.mi.R.id.tv_multiple_hot_name, entity.getField(MultipleFields.NAME).toString());
                holder.setText(com.example.mi.R.id.tv_multiple_hot_info, entity.getField(MultipleFields.TEXT).toString());
                holder.setText(com.example.mi.R.id.tv_multiple_hot_price,entity.getField(MultipleFields.PRICE).toString()+"元/"+
                        entity.getField(MultipleFields.UNIT).toString()
                );


                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(com.example.mi.R.id.tv_multiple_hot_image));
                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerSkip=entity.getField(MultipleFields.BANNERS_SKIP);
                    bannerImages = entity.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(com.example.mi.R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            mTitleItemListener.onBannerItemClick(bannerSkip.get(position));
//                            Toast.makeText(Mi.getApplicationContext()," "+bannerImages.get(position),Toast.LENGTH_SHORT).show();
                        }
                    });
                    mIsInitBanner = true;
                }
                break;
            default:
                break;
        }
    }
    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

}
