package com.example.ec.main.personal.wallet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.miec.R;
import com.example.miec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */
public class UserDepositDelegate extends MiDelegate {


    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }
    @BindView(R2.id.sp_remit_account)
    Spinner spinner;
    @BindView(R2.id.tv_remit_account_cuurentsize)
    TextView currentSize=null;

    double size=12.23;
    @BindView(R2.id.tv_remit_account_size)
    TextView submitSize=null;

    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    @OnClick(R2.id.bt_remit_account_submit)
    void submitInfo(){

        //检查金额数量是否大于现有
        if(Double.valueOf(submitSize.getText().toString())<=size){

            RestClient.builder().url("index").success(new ISuccess() {
                @Override
                public void onSuccess(String response) {

                    //再次检查服务器返回的是否可以提现
                    getSupportDelegate().pop();

                }
            }).loader(getContext()).build().get();

        }else
        {
            Toast.makeText(getContext(),"您输入的金额大于所能提现金额",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_deposit;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("提现");
        currentSize.setText("目前可提现金额："+size+" 元");

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
