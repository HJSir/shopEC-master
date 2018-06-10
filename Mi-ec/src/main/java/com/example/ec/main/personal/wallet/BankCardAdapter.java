package com.example.ec.main.personal.wallet;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ec.main.personal.address.AddressItemFields;
import com.example.ec.main.personal.address.AddressItemType;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.list.ListItemType;
import com.example.mi.app.Mi;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.mi.ui.recycler.MultipleRecyclerAdapter;
import com.example.mi.ui.recycler.MultipleViewHolder;
import com.example.miec.R;

import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by jian
 */

public class BankCardAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();
    BindBankCardDelegate mDelegate;

    public BankCardAdapter(List<ListBean> data, BindBankCardDelegate context) {
        super(data);
        mDelegate=context;
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ListItemType.ITEM_AVATAR, R.layout.arrow_item_avatar);
        addItemType(ListItemType.ITEM_SWITCH, R.layout.arrow_switch_layout);
        addItemType(ListItemType.ITEM_BLANCE, R.layout.item_blance_detail);
        addItemType(ListItemType.ITEM_BANKCARD,R.layout.item_bind_bankcard);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ListBean item) {

        switch (helper.getItemViewType()) {

            case ListItemType.ITEM_BANKCARD:
                helper.setText(R.id.tv_bankcard_number,item.getNumber());
                helper.setText(R.id.tv_bankcard_mark,item.getText());


                helper.getView(R.id.tv_bankcard_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final MaterialDialog mMaterialDialog;
                        mMaterialDialog = new MaterialDialog(mDelegate.getContext());
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
