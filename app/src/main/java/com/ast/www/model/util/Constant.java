package com.ast.www.model.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.animation.AlphaAnimation;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Constant {
    public static String SYSTEM_SHARE_NAME="config";
    public static final String LINK_MAIN = "http://169.254.157.229/mobile/";
    public static SharedPreferences mSharedPreferences;
    public static SharedPreferences.Editor mSharedPreferencesEditor;
    public static Gson gson;
    //初始化
    public static void init(Context context) {
        mSharedPreferences = context.getSharedPreferences(Constant.SYSTEM_SHARE_NAME, Activity.MODE_PRIVATE);
        mSharedPreferencesEditor = mSharedPreferences.edit();
        gson=new Gson();
    }
    //gson封装
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }
}