package com.ast.www.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ast.www.R;

/**
 * Created by Administrator on 2017/7/28.
 */

public class OtherLogInActivity extends BaseAvtivity {

    private ImageView register_back;
    private LinearLayout register_lay;
    private TextView register_register;

    @Override
    protected void createmPresenter() {

    }

    @Override
    protected void initUI() {
        register_back = (ImageView) findViewById(R.id.register_back);
        register_lay = (LinearLayout) findViewById(R.id.register_lay);
        register_register = (TextView) findViewById(R.id.register_register);
    }

    @Override
    protected void initData() {
        register_lay.getBackground().setAlpha(90);
    }

    @Override
    protected void initListener() {
        //回退
        register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_from_right);
            }
        });
        //注册
        register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherLogInActivity.this,ReGisterActivity.class));
                //启动动画
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_otherlogin;
    }
}
