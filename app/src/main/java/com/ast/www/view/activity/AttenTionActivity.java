package com.ast.www.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ast.www.R;

/**
 * Created by Administrator on 2017/7/28.
 */

public class AttenTionActivity extends BaseAvtivity {

    private ImageView head_image_back;
    private LinearLayout head_back;

    @Override
    protected void createmPresenter() {

    }

    @Override
    protected void initUI() {
        head_back = (LinearLayout) findViewById(R.id.head_back);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        head_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_attention;
    }
}
