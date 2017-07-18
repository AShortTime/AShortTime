package com.ast.www.model.util;

import com.ast.www.view.iview.Api;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.BuildConfig;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
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
    public static void get(Consumer<String> onNext, Consumer<Throwable> onError, Map<String,String> map){
        Api api = retrofit.create(Api.class);
        Observable<String> observable = api.getTest(map);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onNext,onError);
    }
    //post封装
    public static void post(Consumer<String> onNext, Consumer<Throwable> onError, Map<String,String> map){
        Api api = retrofit.create(Api.class);
        Observable<String> observable = api.postTest(map);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onNext,onError);
    }
}
