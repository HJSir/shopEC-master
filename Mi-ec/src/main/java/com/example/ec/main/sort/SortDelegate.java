package com.example.ec.main.sort;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;


import com.blankj.utilcode.util.ToastUtils;
import com.example.ec.main.sort.content.ContentDelegate;
import com.example.ec.main.sort.list.VerticalListDelegate;
import com.example.ec.main.sort.search.SearchDelegate;
import com.example.mi.delegates.bottom.BottomItemDelegate;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.mi.utils.DimenUtil.getScreenWidth;

/**
 * Created by 12965 on 2018/3/19.
 */

public class SortDelegate extends BottomItemDelegate implements  AdapterView.OnItemSelectedListener, View.OnClickListener {
    @BindView(R2.id.sp_sort_choose_band)
    AppCompatSpinner mSpinner = null;

    @OnClick(R2.id.ll_delegate_search)
    void serchClick() {
        getParentDelegate().getSupportDelegate().start(new SearchDelegate());
    }

    @BindView(R2.id.et_sort_search)
    AppCompatEditText sortSearch = null;
    List<Map<String, String>> data_list = new ArrayList<>();

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(Bundle saveInstanceState, View rootView) {
        sortSearch.setOnClickListener(this);

        //数据
        data_list = new ArrayList<>();
        String[] menuStr = new String[]{"好而优系列", "糖小希系列"};
        Map<String, String> map;
        int len = menuStr.length;
        for (int i = 0; i < len; i++) {
            map = new HashMap<String, String>();
            map.put("name", menuStr[i]);
            data_list.add(map);
        }

        SimpleAdapter menuAdapter1 = new SimpleAdapter(getContext(), data_list,
                R.layout.item_spinner_sort, new String[]{"name"},
                new int[]{R.id.text9});


        mSpinner.setAdapter(menuAdapter1);
        mSpinner.setOnItemSelectedListener(this);


    }

    @Override
    public void post(Runnable runnable) {

    }

//    @Override
//    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
//        super.onLazyInitView(savedInstanceState);
//        final VerticalListDelegate listDelegate = new VerticalListDelegate();
//        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
//        //设置右侧第一个分类显示，默认显示分类一
//        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
//    }


    @Override
    public void onClick(View view) {
        getParentDelegate().getSupportDelegate().start(new SearchDelegate());
    }



    @Override
    public void onItemSelected(AdapterView<?> view, View view1, int i, long l) {
        ToastUtils.showShort(data_list.get(i).get("name")+"");

        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }

    @Override
    public void onNothingSelected(AdapterView<?> view) {

    }
}
