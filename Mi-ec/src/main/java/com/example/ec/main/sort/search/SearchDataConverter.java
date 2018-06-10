package com.example.ec.main.sort.search;

import com.alibaba.fastjson.JSONArray;
import com.example.mi.ui.recycler.DataConverter;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.mi.utils.storage.MiPreference;


import java.util.ArrayList;

/**
 * Created by jian
 */

public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr =
                MiPreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!jsonStr.equals("")) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }

        return ENTITIES;
    }
}
