package com.example.ec.main.personal.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import com.example.mi.delegates.MiDelegate;
import com.example.miec.R;
import com.example.miec.R2;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.lljjcoder.style.citythreelist.ProvinceActivity;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by jian
 */

public class AddAdressDelegate extends MiDelegate {
    MaterialDialog mMaterialDialog = null;
    private String address="";
    private String addressDetail="";
    private String name="";
    private String phone="";
    @BindView(R2.id.tv_add_address_name)
    TextView nameText = null;
    @BindView(R2.id.tv_add_address_phone)
    TextView phoneText = null;
    @BindView(R2.id.tv_add_address_adressdetail)
    TextView detailText = null;
    CityPickerView cityPickerView;
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_add_adress;
    }

    @OnClick(R2.id.rl_add_adress_chooseCity)
    void chooseCity() {

        cityPickerView.showCityPicker();

    }

    @BindView(R2.id.tv_add_adress_chooseCity)
    TextView chooseCity = null;

    @OnClick(R2.id.bt_add_adress_submit)
    void submit() {
        //检查表单，无误后弹出提示再次确认

        addressDetail= detailText.getText().toString();
        name=nameText.getText().toString();
        phone=phoneText.getText().toString();
        boolean isPass =true;
        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            phoneText.setError("错误的电话格式");
            isPass = false;
        } else{
            phoneText.setError(null);
        }
        if(name.isEmpty()){
            nameText.setError("姓名为空");
            isPass = false;
        } else{
            nameText.setError(null);
        }

        if(address.isEmpty()){
            chooseCity.setError("请选择地址");
            isPass = false;
        }else{
            chooseCity.setError(null);
        }
        if(addressDetail.isEmpty()){
            detailText.setError("地址为空");
            isPass = false;
        }else{
            detailText.setError(null);
        }

        if(isPass){
            mMaterialDialog = new MaterialDialog(getContext());
            mMaterialDialog.setTitle("提示").setMessage("确定提交？").setPositiveButton("确认", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMaterialDialog.dismiss();
getSupportDelegate().pop();
                    //执行提交操作
                }
            }).setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMaterialDialog.dismiss();
                }
            }).show();




        }


    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("新增地址");
        CityConfig cityConfig = new CityConfig.Builder()
                .title("选择城市")//标题
                .titleTextSize(18)//标题文字大小
                .titleTextColor("#585858")//标题文字颜  色
                .titleBackgroundColor("#E9E9E9")//标题栏背景色
                .confirTextColor("#585858")//确认按钮文字颜色
                .confirmText("确定")//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#585858")//取消按钮文字颜色
                .cancelText("取消")//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(true)//是否显示半透明背景
                .visibleItemsCount(5)//显示item的数量
                .province("湖南省")//默认显示的省份
                .city("长沙市")//默认显示省份下面的城市
                .district("芙蓉区")//默认显示省市下面的区县数据
                .provinceCyclic(true)//省份滚轮是否可以循环滚动
                .cityCyclic(true)//城市滚轮是否可以循环滚动
                .districtCyclic(true)//区县滚轮是否循环滚动
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_city_name_tv)//自定义item布局里面的textViewid
                .drawShadows(false)//滚轮不显示模糊效果
                .setLineColor("#03a9f4")//中间横线的颜色
                .setLineHeigh(5)//中间横线的高度
                .setShowGAT(true)//是否显示港澳台数据，默认不显示
                .build();

//设置自定义的属性配置
        cityPickerView = new CityPickerView();
        cityPickerView.init(getContext());
        cityPickerView.setConfig(cityConfig);
        cityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                //省份
                if (province != null) {

                }

                //城市
                if (city != null) {

                }

                //地区
                if (district != null) {

                }

                address = "" + province + city.toString() + district.toString();
                chooseCity.setText(address);
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(getContext(), "已取消");
            }
        });


    }

    @Override
    public void post(Runnable runnable) {

    }


}
