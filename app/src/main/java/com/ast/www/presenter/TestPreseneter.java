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

/**
 * 作者:郭凯奇
 * 时间: 2017/7/12 20:24
 * Title:
 * Text:
 */

public class TestPreseneter extends BasePresenter<IBaseView<ClassBean>>{


    public <T>void get(Map<String,String> map, final Class<T> cla) {
        HttpUtil.get(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(String value) {
                Log.e("json", value );
                T t = Constant.GsonToBean(value, cla);
                getiBaseView().onData(t);
            }
            @Override
            public void onError(Throwable e) {
                getiBaseView().onError(e);
            }
            @Override
            public void onComplete() {
            }
        }, map);
    }
}
