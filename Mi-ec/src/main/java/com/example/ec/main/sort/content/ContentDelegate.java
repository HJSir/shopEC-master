package com.example.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.ec.main.EcBottomDelegate;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.miec.R;
import com.example.miec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by jian
 */

public class ContentDelegate extends MiDelegate  {


    private static final String ARG_CONETENT_ID = "CONTETNT_ID";
    private List<SectionBean> mData = null;
    private int mContetntId = -1;

    @BindView(R2.id.rv_contetnt_delegate_list)
    RecyclerView mRecyclerView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContetntId = args.getInt(ARG_CONETENT_ID);
        }


    }

    public static ContentDelegate newInstance(int contentId) {
//        Log.i("tag",contentId+"");
        final Bundle arg = new Bundle();
        arg.putInt(ARG_CONETENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(arg);
        return delegate;

    }
    private void initData() {
        RestClient.builder()
//                .url("sort_content_list.php?contentId=" + mContentId)
                .url("shoplist")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        mData = new SectionDataConverter().convert(response);
                        final EcBottomDelegate ecBottomDelegate = getParentDelegate().getParentDelegate();
                        final SectionAdapter sectionAdapter =
                                new SectionAdapter(R.layout.item_section_content,
                                        R.layout.item_section_header, mData,ecBottomDelegate);
                        mRecyclerView.setAdapter(sectionAdapter);
                    }
                })
                .build()
                .get();
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_content_list;
    }

    @Override
    public void onBindView(Bundle saveInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        initData();
    }

    @Override
    public void post(Runnable runnable) {

    }

}
