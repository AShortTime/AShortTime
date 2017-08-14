package com.ast.www.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ast.www.R;
import com.ast.www.view.adapter.FragmentVpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/7/20 0020
 */

public class VideoFragment extends BaseFragment {
    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    /**
     * 该抽象方法就是 初始化view
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        TabLayout tab = (TabLayout) view.findViewById(R.id.tab_main);
        final ViewPager vp = (ViewPager) view.findViewById(R.id.vp_main);
        tab.setTabTextColors(Color.BLACK,Color.RED);
        //初始化tablayout
        tab.addTab(tab.newTab().setText("热门"));
        tab.addTab(tab.newTab().setText("附近"));
        FragmentVpAdapter recommendFVpAdapter = new FragmentVpAdapter(getChildFragmentManager());
       //热门与关注的Fragment集合
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new VideoHotFragment());
        fragments.add(new VideoNearbyFragment());
//        添加到外层viewpager
        recommendFVpAdapter.setFragments(fragments);
        vp.setAdapter(recommendFVpAdapter);
//        tablayout与viewpager关联互动
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
//        tablayout监听
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                vp.setCurrentItem(position);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    /**
     * 执行数据的加载
     */
    @Override
    protected void initData() {

    }


    /**
     * 关联presenter
     */
    @Override
    protected void createmPresenter() {

    }

    /**
     * 控件的监听
     */
    @Override
    protected void initListener() {

    }
}
