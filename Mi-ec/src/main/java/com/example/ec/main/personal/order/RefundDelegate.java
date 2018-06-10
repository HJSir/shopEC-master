package com.example.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mi.delegates.MiDelegate;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by jian
 */

//检查是否绑定银行卡
public class RefundDelegate extends MiDelegate {
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    //头部 **
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;
    MaterialDialog mMaterialDialog = null;
    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }
    @BindView(R2.id.sp_refund_bankcardchoose)
    Spinner spinner;
    //**
    @Override
    public Object setLayout() {
        return R.layout.delegate_refund;
    }
    @OnClick(R2.id.bt_refund_submit)
    void btRefundClick(){
        //一系列表单检查 检查完后弹出确认框
        mMaterialDialog = new MaterialDialog(getContext());
        mMaterialDialog.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();
                //执行网络操作
                Toast.makeText(getContext(),"提交成功，退货进度会在推送信息中告知",Toast.LENGTH_SHORT).show();
            }
        }).setPositiveButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();

            }
        })
                .setTitle("提示").setMessage("确认提交退货信息？");


    }
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("退款退货");
        //数据
        data_list = new ArrayList<String>();
        data_list.add("中国工商银行");
        data_list.add("鬼知道什么银行");
        data_list.add("我自己加的备注");
        data_list.add("西里古怪");

        //适配器
        arr_adapter= new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
    }

    @Override
    public void post(Runnable runnable) {

    }
}
