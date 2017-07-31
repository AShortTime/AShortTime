package com.ast.www.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ast.www.R;
import com.ast.www.submit.activity.PublishedActivity;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/7/31 0031
 */

public class SubmitActivity extends BaseAvtivity implements View.OnClickListener {


    private TextView left_tv;
    private TextView title;

    @Override
    protected void createmPresenter() {

    }

    /**
     * 初始化ui
     **/
    @Override
    protected void initUI() {

        findViewById(R.id.iv_submit_capture).setOnClickListener(this);
        findViewById(R.id.iv_submit_text).setOnClickListener(this);

        left_tv = (TextView) findViewById(R.id.item_title_left);
        title = (TextView) findViewById(R.id.item_title);

        left_tv.setText("取消");
        title.setText("创作");

        left_tv.setOnClickListener(this);
    }

    /**
     * 初始化数据
     **/
    @Override
    protected void initData() {

    }

    /**
     * 初始化监听
     **/
    @Override
    protected void initListener() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_submit;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_title_left:
                finish();
                break;
            case R.id.iv_submit_capture:
                Toast.makeText(this, "视频", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_submit_text:
                startActivity(new Intent(this, PublishedActivity.class));
                finish();
                break;
        }
    }
}
