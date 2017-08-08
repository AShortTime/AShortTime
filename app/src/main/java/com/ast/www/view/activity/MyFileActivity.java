package com.ast.www.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.view.adapter.MyFileAdapter;
import com.ast.www.view.fragment.MyFileFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/7.
 */

public class MyFileActivity extends BaseAvtivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<String> list1;
    private ArrayList<Fragment> list;
    private LinearLayout head_back;
    private TextView head_text_te2;
    private TextView head_text_te1;

    @Override
    protected void createmPresenter() {

    }

    @Override
    protected void initUI() {
        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.view);
        head_text_te1 = (TextView) findViewById(R.id.head_text_te1);
        head_text_te2 = (TextView) findViewById(R.id.head_text_te2);
        head_back = (LinearLayout) findViewById(R.id.head_back);
    }

    @Override
    protected void initData() {
        head_text_te1.setText("我的作品");
        head_text_te2.setVisibility(View.GONE);
        getIo();
        viewPager.setAdapter(new MyFileAdapter(getSupportFragmentManager(),list,list1));
        tabLayout.setupWithViewPager(viewPager);

    }

    private void getIo() {
        //复用Fragment
        list = new ArrayList<Fragment>();
        for (int i = 0; i < 2; i++) {
            MyFileFragment myFileFragment=new MyFileFragment();
            list.add(myFileFragment);
        }
        //添加标题头数据
        list1 = new ArrayList<String>();
        list1.add("我的作品");
        list1.add("已上传");
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
        return R.layout.activity_myfile;
    }
}
