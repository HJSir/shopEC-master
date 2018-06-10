package com.example.ec.sign;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.ec.database.DatabaseManager;
import com.example.ec.database.UserProfile;
import com.example.mi.app.AccountManager;
import com.example.mi.ui.loader.MiLoader;


/**
 * Created by jian
 */
public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener) {
        Log.i("Tag",response);
        //解析登录后返回的json数据
        final JSONObject profileJson = JSON.parseObject(response);
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().
                getDao().
                insert(profile);
//
        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }


    public static void onSignUp(String response, ISignListener signListener) {
        //解析登录后返回的json数据
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        final long userId = profileJson.getLong("userId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");
//      //存入数据库
//        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
//        DatabaseManager.getInstance().getDao().insert(profile);
//
//        //已经注册并登录成功了
//        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
        MiLoader.stopLoading();
    }
}
