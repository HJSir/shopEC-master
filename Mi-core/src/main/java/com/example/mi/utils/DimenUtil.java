package com.example.mi.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.mi.app.Configurator;
import com.example.mi.app.Mi;

/**
 * Created by jian
 */

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Mi.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    public static int getScreenHeight() {
        final Resources resources = Mi.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
