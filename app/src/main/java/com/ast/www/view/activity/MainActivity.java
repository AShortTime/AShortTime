package com.ast.www.view.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.ast.www.R;
import com.ast.www.model.bean.ClassBean;
import com.ast.www.model.bean.UserLoginBean;
import com.ast.www.presenter.TestPreseneter;
import com.ast.www.view.iview.IBaseView;
import java.util.HashMap;

public class MainActivity extends BaseAvtivity<TestPreseneter> implements IBaseView<UserLoginBean> {


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
//
//                http://169.254.234.3/user/addUser
//
//                "userHead": "string",（先随便写）
//                "userName": "string",（用户名）
//                "userPassword": "string",（密码）
//                "userPhone": "string",（手机号）
//                "userSex": "string"（男|女）


//                HashMap<String, String> m = new HashMap<>();
//                m.put("userHead", "emmm");
//                m.put("userName", "郭凯奇");
//                m.put("userPassword", "123456");
//                m.put("userPhone", "17600887015");
//                m.put("userSex", "男");
//                mPresenter.post("user/addUser",m, ClassBean.class);
//               // mPresenter.upload();
//                http://169.254.234.3:8080/user/addLogin

//                "userPhone" : String ;//用户注册用的手机号
//                "userPassword" :  String ;//密码
                HashMap<String, String> m = new HashMap<>();
                m.put("userPhone", "17600887015");
                m.put("userPassword", "123456");
                mPresenter.post("user/addLogin",m, UserLoginBean.class);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void onData(UserLoginBean o) {
        Log.e("ondata",o.toString() );
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

