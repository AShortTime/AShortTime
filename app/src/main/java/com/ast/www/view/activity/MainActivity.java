package com.ast.www.view.activity;


import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.ast.www.R;
import com.ast.www.model.bean.ClassBean;
import com.ast.www.model.bean.UserLoginBean;
import com.ast.www.presenter.TestPreseneter;
import com.ast.www.view.iview.IBaseView;

import java.io.File;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends BaseAvtivity<TestPreseneter> implements IBaseView<UserLoginBean> {


    private Button mget;
    private Button mpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void createmPresenter() {
        mPresenter = new TestPreseneter();
        mPresenter.attach(this);
    }

    @Override
    protected void initUI() {
        mget = (Button) findViewById(R.id.get);
        mpost = (Button) findViewById(R.id.post);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mpost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                userName	用户名	varchar
//                userPassword	密码	varchar
//                userPhone	电话号码	varchar
                // userSex	性别	char
//                m.put("userName","仇海涛");
//                m.put("userSex","男");0
//                m.put("userPassword","123456");
//                m.put("userPhone","17600887015");
//
//                mPresenter.post("user/addLogin",m,null);
                List<File> pathList = new ArrayList<>();
                pathList.add(new File(Environment.getExternalStorageDirectory() + "/oppo.mp4"));
                MultipartBody.Builder builder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("mediaDescription", "一个视频")
                        .addFormDataPart("mediaDictionaryValue", "1")
                        .addFormDataPart("mediaUserId", "1");
//                上传文件名还是    file
//
//                参数：
//“mediaName”：String //视频名称
//“mediaDescription”：String //视频描述
//“mediaDictionaryValue”： 1//视频为1，图片为3，文字为2
//“mediaUserId”： int（1，2，3挑一个）  //上传用户id（我这里有1，2，3）
                for (int i = 0; i < pathList.size(); i++) {
                    RequestBody  imageBody  = RequestBody.create(MediaType.parse("multipart/form-data"), pathList.get(i));
                    builder.addFormDataPart("file", "guo.mp4", imageBody);
                }
                List<MultipartBody.Part> parts = builder.build().parts();
                //http://169.254.234.3:8080/media/uploadMedia
                mPresenter.filePost("media/uploadMedia", parts, UserLoginBean.class);
            }
        }
        );
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void onData(UserLoginBean o) {
        Log.e("ondata", o.toString());
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("onerror", throwable.toString());
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.e("dispatch", b + "");
        return b;
    }
}

