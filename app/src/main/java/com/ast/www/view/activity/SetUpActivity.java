package com.ast.www.view.activity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.model.util.FirstEvent;
import com.ast.www.model.util.Utils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/8/4.
 */

public class SetUpActivity extends BaseAvtivity {

    private Button set_up_sign_out_but;
    private TextView head_text_te1;
    private TextView head_text_te2;
    private LinearLayout head_back;

    @Override
    protected void createmPresenter() {

    }

    @Override
    protected void initUI() {
        set_up_sign_out_but = (Button) findViewById(R.id.set_up_sign_out_but);
        head_text_te1 = (TextView) findViewById(R.id.head_text_te1);
        head_text_te2 = (TextView) findViewById(R.id.head_text_te2);
        head_back = (LinearLayout) findViewById(R.id.head_back);
        head_text_te1.setText("设置");
        head_text_te2.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initListener() {
        //退出登录
        set_up_sign_out_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = Utils.getEdit(SetUpActivity.this);
                edit.clear().commit();
                //发送一条信息
                EventBus.getDefault().post(new FirstEvent("FirstEvent btn clicked"));

                finish();
            }
        });
        head_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_setup;
    }
}
