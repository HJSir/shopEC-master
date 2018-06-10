package com.example.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;


import com.example.mi.app.AccountManager;
import com.example.mi.app.IUserChecker;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.ui.launcher.ILauncherListener;
import com.example.mi.ui.launcher.OnLauncherFinishTag;
import com.example.mi.ui.launcher.ScrollLauncherTag;
import com.example.mi.utils.storage.MiPreference;
import com.example.mi.utils.timer.BaseTimerTask;
import com.example.mi.utils.timer.ITimerListener;
import com.example.miec.R;
import com.example.miec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class LauncherDelegate extends MiDelegate implements ITimerListener {
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;
    private Timer mTimer = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onCliclTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }



    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(Bundle saveInstanceState, View rootView) {
        initTimer();
    }


    private void checkIsShowScroll(){
        if(!MiPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            getSupportDelegate().startWithPop(new LauncherScrollDelegate());
        }else {

            //检查用户是否已经登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {

                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }

    }
    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void post(Runnable runnable) {

    }
}
