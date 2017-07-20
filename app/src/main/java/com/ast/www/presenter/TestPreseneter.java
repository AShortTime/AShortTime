package com.ast.www.presenter;

import android.util.Log;

import com.ast.www.model.bean.ClassBean;
import com.ast.www.model.util.Constant;
import com.ast.www.model.util.HttpUtil;
import com.ast.www.view.iview.IBaseView;
import com.google.gson.Gson;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/12 20:24
 * Title:
 * Text:
 */

public class TestPreseneter extends BasePresenter<IBaseView<ClassBean>>{


    public <T>void get(Map<String,String> map, final Class<T> cla) {
        HttpUtil.get(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("getjson", s );
                T t = Constant.GsonToBean(s, cla);
                getiBaseView().onData(t);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("throwable", throwable.toString() );
                getiBaseView().onError(throwable);
            }
        }, map);
    }
    public <T>void post(Map<String,String> map, final Class<T> cla) {
        HttpUtil.post(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("postjson", s );
                T t = Constant.GsonToBean(s, cla);
                getiBaseView().onData(t);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("throwable", throwable.toString() );
//                getiBaseView().onError(throwable);
            }
        }, map);
    }
    public <T>void upload() {
        HttpUtil.upLoad(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("postjson", s );
//                T t = Constant.GsonToBean(s, cla);
//                getiBaseView().onData(t);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("throwable", throwable.toString() );
//                getiBaseView().onError(throwable);
            }
        });
    }
}
