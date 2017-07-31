package com.ast.www.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ast.www.R;

/**
 * Created by Administrator on 2017/7/28.
 */

public class ReGisterActivity extends BaseAvtivity {

    private LinearLayout register_lay;
    private ImageView register_back;
    private Button register_but_register;

    @Override
    protected void createmPresenter() {

    }

    @Override
    protected void initUI() {
        register_lay = (LinearLayout) findViewById(R.id.register_lay);
        register_back = (ImageView) findViewById(R.id.register_back);
        register_but_register = (Button) findViewById(R.id.register_but_register);
    }

    @Override
    protected void initData() {
         register_lay.getBackground().setAlpha(100);
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
        register_but_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }
}
