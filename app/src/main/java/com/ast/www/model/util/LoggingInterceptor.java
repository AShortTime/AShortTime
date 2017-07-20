package com.ast.www.model.util;

import android.util.Log;

import java.io.IOException;

import io.reactivex.android.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/18 15:24
 * Title:
 * Text:
 */


public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        MultipartBody body = (MultipartBody) request.body();
//        Log.e("拦截器", body.parts().size()+"");

        return chain.proceed(request);
    }
}