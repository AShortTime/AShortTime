package com.ast.www.view.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.ast.www.R;
import com.ast.www.model.bean.ClassBean;
import com.ast.www.presenter.TestPreseneter;
import com.ast.www.view.iview.IBaseView;
import java.util.HashMap;

public class MainActivity extends BaseAvtivity<TestPreseneter> implements IBaseView<ClassBean> {


    private Button mget;
    private Button mpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void createmPresenter() {
        mPresenter=new TestPreseneter();
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
        //userName	用户名	var char(20)
//        userPassword	密码	var char(20)
//        userPhone	电话	var char(11)
//        userSex	性别	char(5)
//        userHead	头像（待定）	var char(255)	存放用户头像地址

        mpost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                        "character_content": "string",
//                        "character_dictionary_value": "string",
//                        "character_user_id": "string",

                HashMap<String, String> m = new HashMap<>();
                m.put("character_content", "我擦 大没了");
                m.put("character_dictionary_value", "2");
                m.put("character_user_id", "1");
                mPresenter.post(m, ClassBean.class);
               // mPresenter.upload();
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void onData(ClassBean o) {

    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("onerror", throwable.toString());
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.e("dispatch",  b+"");
        return b;
    }
}

