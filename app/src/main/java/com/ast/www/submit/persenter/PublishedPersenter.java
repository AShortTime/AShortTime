package com.ast.www.submit.persenter;

import android.util.Log;
import com.ast.www.model.util.HttpUtil;
import com.ast.www.submit.activity.PublishedActivity;
import com.ast.www.submit.bean.CodeBean;
import com.google.gson.Gson;
import java.util.List;
import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;


/**
 * Effect :submit页面Persenter
 *  上传数据
 * <p>
 * Created by Administrator
 * Time by 2017/7/31 0031
 */

public class PublishedPersenter {

    public static final String TAG=PublishedPersenter.class.getSimpleName();

    private PublishedActivity pub;

    public PublishedPersenter(PublishedActivity pub){
        this.pub=pub;
    }


    public void uploadData(String url, List<MultipartBody.Part> part) {

        Log.i(TAG, "uploadPic: ");

        HttpUtil.filePost(url, part, new Consumer<String>() {
            @Override
            public void accept(String string) throws Exception {
                CodeBean codeBean = new Gson().fromJson(string, CodeBean.class);
                if (200==codeBean.getCode()){
                    pub.upLoad(codeBean);
                    Log.i(TAG, "accept: 200");
                }else {
                    Log.i(TAG, "accept: "+codeBean.getCode());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i(TAG, "accept: "+throwable.getMessage());
            }
        });

    }
}
