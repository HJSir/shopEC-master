package com.example.ec.main.personal.wallet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.IError;
import com.example.mi.net.callback.IFailure;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.utils.check.BankCardNumberCheck;
import com.example.miec.R;
import com.example.miec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * Created by jian
 */

public class AddBankCardDelegate extends MiDelegate {


    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @BindView(R2.id.tv_add_bankcard_number)
    EditText editNumber = null;
    @BindView(R2.id.tv_add_bankcard_mark)
    EditText editMark = null;


    @OnClick(R2.id.bt_add_bankcard_submit)
    void submitClick() {
        if (BankCardNumberCheck.checkBankCard(editNumber.getText() + "")) {

            if (editMark.getText().length() > 15) {
                Toast.makeText(getContext(), "您输入的备注信息超过15字了", Toast.LENGTH_SHORT).show();
            } else {
                RestClient.builder().loader(getContext()).url("index").success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        //提交信息返回成功 则回退 否则不回退
                        getSupportDelegate().pop();

                    }
                }).error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "请求错误", Toast.LENGTH_SHORT).show();
                    }
                }).failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                    }
                }).build().post();

            }


        } else {
            Toast.makeText(getContext(), "银行卡格式错误", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_add_bankcard;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("添加银行卡");

    }

    @Override
    public void post(Runnable runnable) {

    }
}
