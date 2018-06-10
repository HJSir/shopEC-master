package com.example.mi.app;

import com.example.mi.utils.storage.MiPreference;


/**
 * Created by jian
 */
public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state) {
        MiPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return MiPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
