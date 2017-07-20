package com.ast.www.model.util;

import android.os.Environment;
import android.util.Log;

import com.ast.www.view.iview.Api;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
    public static void get(Consumer<String> onNext, Consumer<Throwable> onError, Map<String, String> map) {
        Api api = retrofit.create(Api.class);
        Observable<String> observable = api.getTest(map);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onNext, onError);
    }

    //post封装
    public static void post(Consumer<String> onNext, Consumer<Throwable> onError, Map<String, String> map) {
        Api api = retrofit.create(Api.class);
        Observable<String> observable = api.postTest(map);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onNext, onError);
    }

    public static void upLoad(Consumer<String> onNext, Consumer<Throwable> onError) {
//构建要上传的文件
//        String path = Environment.getExternalStorageDirectory().getPath() + "/a.jpg";
//        Log.e("path", path);
//        File file = new File(path);//filePath 图片地址
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("application/otcet-stream"), file);
//
//        MultipartBody.Part body = MultipartBody.Part.createFormData("File", file.getName(), requestFile);
//
//        String descriptionString = "This is a description";
//        RequestBody description =
//                RequestBody.create(
//                        MediaType.parse("multipart/form-data"), descriptionString);
//
        String path = Environment.getExternalStorageDirectory().getPath() + "/a.jpg";
        Log.e("path", path);
        File file = new File(path);//filePath 图片地址
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//ParamKey.TOKEN 自定义参数key常量类，即参数名
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("file", file.getName(), imageBody);//imgfile 后台接收图片流的参数名
        List<MultipartBody.Part> parts = builder.build().parts();
        Api api = retrofit.create(Api.class);
        Observable<String> observable = api.upload(parts);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onNext, onError);
    }
}
