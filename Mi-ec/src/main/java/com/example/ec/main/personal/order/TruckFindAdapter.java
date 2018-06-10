package com.example.ec.main.personal.order;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.mi.ui.recycler.MultipleRecyclerAdapter;
import com.example.mi.ui.recycler.MultipleViewHolder;
import com.example.miec.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * Created by jian
 */

public class TruckFindAdapter extends MultipleRecyclerAdapter {

//    private static final RequestOptions OPTIONS = new RequestOptions()
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .centerCrop()
//            .dontAnimate();

    protected TruckFindAdapter(List<MultipleItemEntity> data) {
        super(data);

        addItemType(TruckFindFields.TRUCK_TYPE, R.layout.item_find_truck_info);
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);


        switch (holder.getItemViewType()) {

            case TruckFindFields.TRUCK_TYPE:

              TextView remark= holder.getView(R.id.tv_item_truck_remark);
                TextView time= holder.getView(R.id.tv_item_truck_time);
                IconTextView icon=holder.getView(R.id.tv_item_truck_icon);
                remark.setText(entity.getField(TruckFindListType.TIME).toString());
                time.setText(entity.getField(TruckFindListType.CONTENT).toString());
                break;
                default:
                    break;

        }

    }
}
