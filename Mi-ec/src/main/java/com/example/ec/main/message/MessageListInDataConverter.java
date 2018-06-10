package com.example.ec.main.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;

/**
 * Created by jian
 */

public class MessageListInDataConverter {



    public ArrayList<MessageListBean> convert(String data) {
        ArrayList<MessageListBean> ENTITS=new ArrayList<>();
        JSONArray jsonArray = JSON.parseObject(data).getJSONArray("data");
        for(int i=0;i<jsonArray.size();i++){

            String  time=jsonArray.getJSONObject(i).getString("time");
            String  content=jsonArray.getJSONObject(i).getString("content");
           ENTITS.add(new MessageListBean.Builder()
                   .setText(content)
                   .setValue(time)
                   .setItemType(MessageListItemType.MEESAGE_NEWS)
                   .build());
        }

        return ENTITS;
    }

}
