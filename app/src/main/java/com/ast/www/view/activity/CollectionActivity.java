package com.ast.www.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ast.www.R;

/**
 * Created by Administrator on 2017/7/28.
 */

public class CollectionActivity extends BaseAvtivity {

    private TextView head_text_te1;
    private TextView head_text_te2;
    private ImageView head_image_back;
    private LinearLayout head_back;

    @Override
    protected void createmPresenter() {

    }

    @Override
    protected void initUI() {
        head_text_te1 = (TextView) findViewById(R.id.head_text_te1);
        head_text_te2 = (TextView) findViewById(R.id.head_text_te2);
        head_back = (LinearLayout) findViewById(R.id.head_back);
    }

    @Override
    protected void initData() {
        head_text_te1.setText("我的收藏");
        head_text_te2.setText("删除");
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
        return R.layout.activity_collection;
    }
}
