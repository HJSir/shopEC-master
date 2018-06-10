package com.example.mi.utils.callback;

import android.support.annotation.Nullable;

/**
 * Created by jian
 */

public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
