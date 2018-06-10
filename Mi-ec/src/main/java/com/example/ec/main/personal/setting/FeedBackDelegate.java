package com.example.ec.main.personal.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.mi.delegates.MiDelegate;
import com.example.miec.R;
/**
 * Created by jian
 */

public class FeedBackDelegate extends MiDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_feedback;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
