package com.ast.www.presenter;

import android.util.Log;

import com.ast.www.model.util.Constant;
import com.ast.www.model.util.HttpUtil;
import com.ast.www.view.iview.IBaseView;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/12 20:24
 * Title:
 * Text:
 */

public class HomePresenter extends BasePresenter<IBaseView> {

//get请求
    public <T> void get(String url, Map<String, String> map, final Class<T> cla) {
        HttpUtil.get(url, map, new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("评论", s);
                if(cla!=null){
                    T t = Constant.GsonToBean(s, cla);
                    getiBaseView().onData(t);
                }

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("评论错误", throwable.toString());
                getiBaseView().onError(throwable);
            }
        });
    }


//post请求
    public <T> void post(String url, Map<String, String> map, final Class<T> cla) {
        HttpUtil.post(url, map, new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                    Log.e("postjson", s+"");
                if(cla!=null){
                    T t = Constant.GsonToBean(s, cla);
                    getiBaseView().onData(t);
                }else{
                    getiBaseView().onData(null);
                }

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                    Log.e("throwable", throwable.toString());
                    getiBaseView().onError(throwable);
            }
        });
    }


//文件上传
    public <T> void filePost(String url, List<MultipartBody.Part> parts,final Class<T> cla) {
        HttpUtil.filePost(url,parts, new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                JSONObject jsonObject=new JSONObject(s);
                String code = jsonObject.getString("code");
                Log.e("postjson", s+code);
//                T t = Constant.GsonToBean(s, cla);
//                getiBaseView().onData(t);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("throwable", throwable.toString());
//                getiBaseView().onError(throwable);
            }
        });
    }
}
