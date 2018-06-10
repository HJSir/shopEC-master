package com.example.ec.main.personal.order;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.ec.main.personal.address.AddressItemFields;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.ui.recycler.MultipleFields;
import com.example.mi.ui.recycler.MultipleItemEntity;
import com.example.mi.ui.recycler.MultipleRecyclerAdapter;
import com.example.mi.ui.recycler.MultipleViewHolder;
import com.example.miec.R;


import java.util.List;

/**
 * Created by jian
 */

public class OrderListAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();
    MiDelegate DELEGATE;

    public OrderListAdapter(List<MultipleItemEntity> data, MiDelegate delegate) {
        super(data);
        DELEGATE = delegate;
        addItemType(OrderListItemType.ITEM_ORDER_LIST_ALL_CONTENT, R.layout.order_all_item_content);
        addItemType(OrderListItemType.ITEM_ORDER_LIST_ALL_FOOTER, R.layout.order_doing_item_footer);
        addItemType(OrderListItemType.ITEM_ORDER_LIST_ALL_HEADER, R.layout.order_all_item_header);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {

        super.convert(holder, entity);
        switch (holder.getItemViewType()) {

            case OrderListItemType.ITEM_ORDER_LIST_ALL_CONTENT:
                LinearLayout ll = holder.getView(R.id.ll_item_allorder);
                ImageView mPic = holder.getView(R.id.iv_item_allorder_pic);
                TextView mName = holder.getView(R.id.tv_item_allorder_title);
                TextView mQuantity = holder.getView(R.id.tv_item_allorder_item_num);
                TextView mPrice = holder.getView(R.id.tv_item_allorder_item_price);

                Glide.with(mContext)
                        .load(entity.getField(OrderItemFields.GOODS_IMAGE))
                        .apply(OPTIONS)
                        .into(mPic);

                mName.setText(entity.getField(OrderItemFields.GOODS_NAME).toString());
                mQuantity.setText("x" + entity.getField(OrderItemFields.GOODS_QUANTITY).toString());
                mPrice.setText(entity.getField(OrderItemFields.GOODS_PRICE).toString() + " 元");
                //因为这个布局适配了两个地方 一个list一个detail 所以只有list才有点击事件 detail的需要过滤掉
                if (entity.getField(OrderItemFields.FLAG).equals("list"))
                    ll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DELEGATE.getParentDelegate().getSupportDelegate().start(new OrderDetailDelegate());
                        }
                    });
                break;
            case OrderListItemType.ITEM_ORDER_LIST_ALL_FOOTER:

                TextView mAllPrice = holder.getView(R.id.tv_item_allorder_total);
                TextView mAllGoodsRoot = holder.getView(R.id.tv_item_allorder_orderlogistics); //查询物流
                TextView mAllBackGoods = holder.getView(R.id.tv_item_allorder_backorder);           //退货
                mAllPrice.setText("共" + entity.getField(OrderItemFields.ORDER_QUANTITY) + "件商品 合计" + entity.getField(OrderItemFields.ALL_PAY).toString() + "元" +
                        "(含运费" + entity.getField(OrderItemFields.ORDER_FREIGHT) + "元）");
                int state = entity.getField(OrderItemFields.ORDER_STATUES);
                if (state <= 3) {
                    mAllGoodsRoot.setHeight(0);
                } else {
                    mAllGoodsRoot.setText("查看物流");
                    mAllGoodsRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DELEGATE.getParentDelegate().getSupportDelegate().start(new TruckFindDelegate());
                        }
                    });
                }
                mAllBackGoods.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DELEGATE.getParentDelegate().getSupportDelegate().start(new RefundDelegate());
                    }
                });

                break;
            case OrderListItemType.ITEM_ORDER_LIST_ALL_HEADER:
//                TextView shopName = holder .getView(R.id.tv_item_allorder_shopname);
                TextView mTime = holder.getView(R.id.tv_item_allorder_ordertime);
                TextView mState = holder.getView(R.id.tv_item_allorder_state);
                mTime.setText("时间：" + entity.getField(OrderItemFields.ORDER_CREATE_TIME).toString());
//                shopName.setText(entity.getField(OrderItemFields.ORDER_TYPE_NAME).toString());
                int state2 = entity.getField(OrderItemFields.ORDER_STATUES);
                if (state2 == 1) {
                    mState.setText("订单审核中");
                } else if (state2 == 2) {
                    mState.setText("订单审核失败");
                } else if (state2 == 3) {
                    mState.setText("订单审核通过");
                } else if (state2 == 4) {
                    mState.setText("订单已发货");
                }

                break;

            default:
                break;
        }
    }
}
