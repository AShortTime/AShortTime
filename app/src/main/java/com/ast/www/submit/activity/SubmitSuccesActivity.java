package com.ast.www.submit.activity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ast.www.R;
import com.ast.www.view.activity.BaseAvtivity;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/7/31 0031
 */

public class SubmitSuccesActivity extends BaseAvtivity implements View.OnClickListener {

    @Override
    protected void createmPresenter() {

    }

    /**
     * 初始化ui
     **/
    @Override
    protected void initUI() {

        TextView left_back= (TextView) findViewById(R.id.item_title_left3);
        left_back.setText("返回");
        left_back.setOnClickListener(this);
        findViewById(R.id.rl_submit_succes_icon01).setOnClickListener(this);
        findViewById(R.id.rl_submit_succes_icon02).setOnClickListener(this);
        findViewById(R.id.rl_submit_succes_icon03).setOnClickListener(this);
        findViewById(R.id.rl_submit_succes_icon04).setOnClickListener(this);
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
        return R.layout.activity_submit_succes;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_title_left3:
                finish();
                break;
            case R.id.rl_submit_succes_icon01:
                Toast.makeText(this, "QQ好友", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_submit_succes_icon02:
                Toast.makeText(this, "QQ空间", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_submit_succes_icon03:
                Toast.makeText(this, "朋友圈", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_submit_succes_icon04:
                Toast.makeText(this, "微信", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
