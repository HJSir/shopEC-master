package com.example.mi.delegates;

/**
 * Created by jian
 */

public abstract class MiDelegate extends PermissionCheckerDelegate {
    public <T extends MiDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
