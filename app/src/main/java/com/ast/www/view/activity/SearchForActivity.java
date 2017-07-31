package com.ast.www.view.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ast.www.R;

/**
 * Created by Administrator on 2017/7/31.
 */

public class SearchForActivity extends BaseAvtivity {

    private TextView head_text_te2;
    private TextView head_text_te1;
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
        head_text_te1.setText("搜索好友");
        head_text_te2.setVisibility(View.GONE);
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
        return R.layout.activity_search_for;
    }
}
