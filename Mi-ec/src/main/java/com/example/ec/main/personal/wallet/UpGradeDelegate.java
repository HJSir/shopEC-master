package com.example.ec.main.personal.wallet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class UpGradeDelegate extends MiDelegate {
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }
    @BindView(R2.id.sp_up_grade)
    Spinner spinner;




    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    @OnClick(R2.id.bt_up_grade_submit)
    void submitInfo(){
       //提交申请，或者支付流程
        getSupportDelegate().pop();

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_up_grade;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("升级代理");


        //数据
        data_list = new ArrayList<String>();
        data_list.add("分销商");
        data_list.add("总代");


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
