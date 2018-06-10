package com.example.ec.main.personal.list;

import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mi.app.Mi;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.utils.TipsUtil;
import com.example.miec.R;

import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by jian
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ListItemType.ITEM_AVATAR, R.layout.arrow_item_avatar);
        addItemType(ListItemType.ITEM_SWITCH, R.layout.arrow_switch_layout);
        addItemType(ListItemType.ITEM_BLANCE, R.layout.item_blance_detail);
        addItemType(ListItemType.ITEM_BANKCARD,R.layout.item_bind_bankcard);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ListBean item) {

        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_icon, item.getIconFont());
                helper.setText(R.id.tv_arrow_value, item.getValue());
                break;
            case ListItemType.ITEM_AVATAR:
                Glide.with(mContext)
                        .load(item.getImageUrl())
                        .apply(OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_arrow_avatar));
                break;
            case ListItemType.ITEM_SWITCH:
                helper.setText(R.id.tv_arrow_switch_text, item.getText());
                final SwitchCompat switchCompat = helper.getView(R.id.list_item_switch);
                switchCompat.setChecked(true);
                switchCompat.setOnCheckedChangeListener(item.getOnCheckedChangeListener());
                break;
            case ListItemType.ITEM_BLANCE:

                helper.setText(R.id.tv_blancedetail_content, item.getText());//内容
                helper.setText(R.id.tv_blancedetail_time, item.getValue()); //时间
                helper.setText(R.id.tv_blancedetail_cost, item.getNumber()); //花费
                break;

                case ListItemType.ITEM_BANKCARD:
                    helper.setText(R.id.tv_bankcard_number,item.getNumber());
                    helper.setText(R.id.tv_bankcard_mark,item.getText());


                helper.getView(R.id.tv_bankcard_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                           final MaterialDialog mMaterialDialog;
                        mMaterialDialog = new MaterialDialog(Mi.getApplicationContext());
                        mMaterialDialog.setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                                RestClient.builder()
                                        .url("index")
                                        .params("id", "")
                                        .success(new ISuccess() {
                                            @Override
                                            public void onSuccess(String response) {
                                                remove(helper.getLayoutPosition());
                                                //弹出删除成功或者失败信息
                                            }
                                        })
                                        .build()
                                        .post();

                            }
                        })
                                .setTitle("提示")
                                .setMessage("确认删除？")
                                .show();


                    }
                });
                    break;

            default:
                break;
        }
    }
}
