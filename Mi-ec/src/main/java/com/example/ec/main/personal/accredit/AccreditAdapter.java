package com.example.ec.main.personal.accredit;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import com.example.ec.main.personal.address.AddressItemFields;
import com.example.ec.main.personal.address.AddressItemType;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.mi.ui.recycler.MultipleRecyclerAdapter;
import com.example.mi.ui.recycler.MultipleViewHolder;
import com.example.miec.R;

import java.util.List;

/**
 * Created by jian
 */

public class AccreditAdapter extends MultipleRecyclerAdapter {

    protected AccreditAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(AccreditItemType.ACCREDIT_TYPE_NORMAL, R.layout.item_search_result);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case AccreditItemType.ACCREDIT_TYPE_NORMAL:
                final String name = entity.getField(AccreditItemFields.NAME);
                final String phone = entity.getField(AccreditItemFields.PHONE);
                final String higher = entity.getField(AccreditItemFields.HIGHER);
                final String level = entity.getField(AccreditItemFields.LEVEL);
                final String id = entity.getField(AccreditItemFields.ID);
                final String brand=entity.getField(AccreditItemFields.BRAND);

                final TextView nameText = holder.getView(R.id.tv_item_search_name);
                final TextView phoneText = holder.getView(R.id.tv_item_search_phone);
                final TextView levelText = holder.getView(R.id.tv_item_search_level);
                final TextView higherText = holder.getView(R.id.tv_item_search_higher);
                final TextView brandText = holder.getView(R.id.tv_item_search_brand);
                final TextView idText = holder.getView(R.id.tv_item_search_id);
                higherText.setText(higher);
                brandText.setText(brand);
                idText.setText(id);

                nameText.setText(name);
                phoneText.setText(phone);
                levelText.setText(level);
                break;
            default:
                break;
        }
    }
}
