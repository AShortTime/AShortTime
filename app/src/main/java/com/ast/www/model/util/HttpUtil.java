package com.ast.www.model.util;

import android.os.Environment;
import android.util.Log;

import com.ast.www.view.iview.Api;

import org.reactivestreams.Subscriber;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.BuildConfig;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/12 19:24
 * Title:
 * Text:
 */

public class HttpUtil {

    private static Retrofit retrofit = new Retrofit.Builder()
            .client(Constant.getclient())
            .baseUrl(Constant.LINK_MAIN)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    //get封装
    public static void get(String url, Map<String, String> map,Consumer<String> onNext, Consumer<Throwable> onError) {
        Api api = retrofit.create(Api.class);
        Observable<String> observable = api.get(url,map);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onNext, onError);
    }

    //post封装
    public static void post(String url, Map<String, String> map,Consumer<String> onNext, Consumer<Throwable> onError) {
        Api api = retrofit.create(Api.class);
        Observable<String> observable = api.post(url,map);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onNext, onError);
    }

    public static void filePost(String url,List<MultipartBody.Part> parts,Consumer<String> onNext, Consumer<Throwable> onError) {
        Api api = retrofit.create(Api.class);
        Observable<String> observable = api.filePost(url,parts);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onNext, onError);
    }
}
