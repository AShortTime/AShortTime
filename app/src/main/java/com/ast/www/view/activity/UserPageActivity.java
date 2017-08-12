package com.ast.www.view.activity;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ast.www.R;
import com.ast.www.view.adapter.UserPageAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/10.
 */

public class UserPageActivity extends BaseAvtivity {

    private ListView userpage_list;

    @Override
    protected void createmPresenter() {

    }

    @Override
    protected void initUI() {
        userpage_list = (ListView) findViewById(R.id.userpage_list);
    }

    @Override
    protected void initData() {
        ArrayList<String> list=new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            list.add("第"+i);
        }
        View view= LayoutInflater.from(UserPageActivity.this).inflate(R.layout.it_userpage_1,userpage_list,false);
        userpage_list.addHeaderView(view);
        UserPageAdapter userPageAdapter=new UserPageAdapter(UserPageActivity.this,list);
        userpage_list.setAdapter(userPageAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_userpage;
    }
}
