package com.example.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ec.main.personal.PersonalDelegate;
import com.example.mi.delegates.MiDelegate;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.ec.main.personal.PersonalDelegate.ORDER_TYPE;

/**
 * Created by jian
 */

public class BaseOrderListDelegate extends MiDelegate {

    Bundle args = null;
    int mCurrent = 0;
    private List<OrderListDelegate> list;
    private MyAdapter adapter;
    private String[] titles = {"全部", "审核中", "已取消", "已审核", "已发货"};
    @BindView(R2.id.vp_delegate_baseorder)
    ViewPager mViewPager = null;
    @BindView(R2.id.tablayout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_base_order_list;
    }

    //    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        if (view != null) {
//            ViewGroup parent = (ViewGroup) view.getParent();
//            if (parent != null) {
//                parent.removeView(view);
//            }
//            return view;
//        }
//        view = inflater.inflate(R.layout.delegate_base_order_list, container, false);
//        return view;
//    }
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("订单中心");
     //viewpager中内容
        list = new ArrayList<>();
        OrderListDelegate list1 = new OrderListDelegate();
        OrderListDelegate list2 = new OrderListDelegate();
        OrderListDelegate list3 = new OrderListDelegate();
        OrderListDelegate list4 = new OrderListDelegate();
        OrderListDelegate list5 = new OrderListDelegate();
        args.putString(ORDER_TYPE, "all");
        list1.setArguments(args);
        list.add(list1);

        args.putString(ORDER_TYPE, "doing");
        list2.setArguments(args);
        list.add(list2);

        args.putString(ORDER_TYPE, "out");
        list3.setArguments(args);
        list.add(list3);

        args.putString(ORDER_TYPE, "in");
        list4.setArguments(args);
        list.add(list4);

        args.putString(ORDER_TYPE, "truck");
        list5.setArguments(args);
        list.add(list5);
        //ViewPager的适配器
        adapter = new MyAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(mCurrent);
        mViewPager.setOffscreenPageLimit(5);
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        mCurrent = args.getInt(PersonalDelegate.ORDER_CURRENT);
    }



    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
