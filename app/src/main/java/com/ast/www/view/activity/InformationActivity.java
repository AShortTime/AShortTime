package com.ast.www.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.model.util.IsUtils;
import com.ast.www.model.util.Utils;
import com.ast.www.view.adapter.InformationAdapter;
import com.ast.www.view.fragment.InformationFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/31.
 */

public class InformationActivity extends BaseAvtivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> list;
    private ArrayList<String> list1;
    private TextView head_text_te1;
    private TextView head_text_te2;
    private LinearLayout head_back;

    @Override
    protected void createmPresenter() {

        if(Utils.getSharedPrefers(this).getString("userName","").equals("")){

        }
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
        head_text_te1.setText("信息通知");
        head_text_te2.setVisibility(View.GONE);
        list = new ArrayList<>();
        for (int i = 0; i <2 ; i++) {
            InformationFragment informationFragment=new InformationFragment();
            list.add(informationFragment);
        }

        list1 = new ArrayList<String>();
        list1.add("信息");
        list1.add("私信");
        InformationAdapter informationAdapter=new InformationAdapter(getSupportFragmentManager(),list,list1);
        viewPager.setAdapter(informationAdapter);
        tabLayout.setupWithViewPager(viewPager);

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
        return R.layout.activity_information;
    }
}
