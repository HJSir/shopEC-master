package com.example.mi.app;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by jian
 */

public final class Mi {
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getMiConfigs()
                .put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());

        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getSingleConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }



    public static Handler getHandler() {
        return getConfiguration(ConfigType.HANDLER);
    }

}
