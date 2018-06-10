package com.example.ec.main.personal.wallet;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.list.ListItemType;
import com.example.mi.ui.recycler.ItemType;


import java.util.ArrayList;

/**
 * Created by jian
 */
public class WalletDataConverter {
   String dataString;
int type=0;
    public WalletDataConverter(String dataString,int type) {
        this.dataString = dataString;
        this.type=type;
    }

    public ArrayList<ListBean> convert(){
        final ArrayList<ListBean> data =new ArrayList<>();
     JSONArray jsonArray = JSON.parseObject(dataString).getJSONArray("data");
                     if(type==ListItemType.ITEM_BLANCE)
               for(int i=0;i<jsonArray.size();i++){
                  final ListBean listBean = new ListBean.Builder()
                          .setItemType(ListItemType.ITEM_BLANCE)
                          .setValue(jsonArray.getJSONObject(i).getString("time")) //时间
                          .setNumber(jsonArray.getJSONObject(i).getString("cost")) //花费
                          .setText(jsonArray.getJSONObject(i).getString("content")) //内容
                          .build();

                   data.add(listBean);

               }
               else if(type==ListItemType.ITEM_BANKCARD)
                         for(int i=0;i<jsonArray.size();i++){
                             final ListBean listBean = new ListBean.Builder()
                                     .setItemType(ListItemType.ITEM_BANKCARD)

                                     .setNumber(jsonArray.getJSONObject(i).getString("number")) //银行卡号码
                                     .setText(jsonArray.getJSONObject(i).getString("mark")) //备注
                                     .build();

                             data.add(listBean);

                         }


        return data;
    }

}
