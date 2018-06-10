package com.example.mi.delegates.bottom;

import android.widget.Toast;

import com.example.mi.R;
import com.example.mi.app.Mi;
import com.example.mi.delegates.MiDelegate;


/**
 * Created by jian
 */

public abstract class BottomItemDelegate extends MiDelegate {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Mi.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
