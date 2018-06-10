package com.example.ec.main.message;

import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.miec.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * Created by jian
 */

public class MessageListAdapter extends BaseMultiItemQuickAdapter<MessageListBean, BaseViewHolder> {

    public MessageListAdapter(List<MessageListBean> data) {
        super(data);
        addItemType(MessageListItemType.MEESAGE_LIST, R.layout.arrow_item_avatar_layout);
        addItemType(MessageListItemType.MEESAGE_NEWS,R.layout.news_item_all);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageListBean item) {

        switch (helper.getItemViewType()){

            case MessageListItemType.MEESAGE_LIST:
                IconTextView iconTextView = helper.getView(R.id.tv_arrow_layout_icon);
                AppCompatTextView name=helper.getView(R.id.tv_arrow_layout_name);
                AppCompatTextView text=helper.getView(R.id.tv_arrow_layout_text);
                AppCompatTextView num=helper.getView(R.id.icon_arrow_num);

                iconTextView.setText(item.getIconFont());
                name.setText(item.getValue());
                text.setText(item.getText());
                num.setText(item.getNumber());
                break;

            case MessageListItemType.MEESAGE_NEWS:

                TextView time=helper.getView(R.id.news_item_time);
                TextView content=helper.getView(R.id.news_item_content);



                time.setText(item.getValue().toString()); //时间
                content.setText(item.getText().toString());//内容

                break;
                default:
                    break;


        }

    }
}
