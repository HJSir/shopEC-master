package com.example.ec.settle;

import com.example.mi.ui.recycler.DataConverter;
import com.example.mi.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by jian
 */

public class SettleDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        return ENTITIES;
    }
}
