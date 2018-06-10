package com.example.a12965.mishopec;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.ec.launcher.LauncherDelegate;
import com.example.ec.main.EcBottomDelegate;
import com.example.ec.main.personal.order.TruckFindDelegate;
import com.example.ec.settle.SettleDelegate;
import com.example.ec.sign.ISignListener;
import com.example.ec.sign.SignInDelegate;
import com.example.mi.activitys.ProxyActivity;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.ui.launcher.ILauncherListener;
import com.example.mi.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements ISignListener,ILauncherListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      final   ActionBar actionBar = getSupportActionBar();
       if(actionBar!=null)
        actionBar.hide();
    }

    @Override
    public MiDelegate setRootDelegate() {
        return new LauncherDelegate();

    }



//注册成功回调
    @Override
    public void onSignInSuccess() {

        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT);
        getSupportDelegate().pop();
        getSupportDelegate().start(new EcBottomDelegate());
    }
//注册成功回调
    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
        getSupportDelegate().pop();
        getSupportDelegate().start(new SignInDelegate());
    }
//启动图完成回调
    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        Toast.makeText(this,"启动成功",Toast.LENGTH_SHORT).show();
        switch (tag) {
            case SIGNED:
              Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
           getSupportDelegate().pop();
                getSupportDelegate().start(new EcBottomDelegate());

                Log.i("taa",  getSupportFragmentManager().getBackStackEntryCount()+" ");
                break;
            case NOT_SIGNED:
             Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().pop();
                getSupportDelegate().start(new SignInDelegate());
                break;
            default:
                break;
        }
    }

    //退出时的时间
    private long mExitTime;
    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0&&getSupportFragmentManager().getBackStackEntryCount()<=1) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(ExampleActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {

            finish();
            System.exit(0);
        }
    }

    @Override
    public void post(Runnable runnable) {

    }
}
