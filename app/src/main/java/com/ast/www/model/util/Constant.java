package com.ast.www.model.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.animation.AlphaAnimation;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


public class Constant {
    public static String SYSTEM_SHARE_NAME="config";
    public static final String LINK_MAIN = "http://169.254.1.100/quarter/";
    public static SharedPreferences mSharedPreferences;
    public static SharedPreferences.Editor mSharedPreferencesEditor;
    public static Gson gson;
    private static final int DEFAULT_TIMEOUT = 20;

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

    /**
     * okhttp拦截器
     */
    public static OkHttpClient getclient(){
//       File httpCacheDirectory=new File(context.getCacheDir(),"cache");
//       Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .cache(cache)
                .addNetworkInterceptor(new LoggingInterceptor())
//                .addInterceptor(new CaheInterceptor(context))
//                .addNetworkInterceptor(new CaheInterceptor(context))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15,TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .build();
        return okHttpClient;
    }
}