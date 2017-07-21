package com.ast.www.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/21 10:57
 * Title:
 * Text:
 */

public class FragmentVpAdapter extends FragmentPagerAdapter{
    List<Fragment> fragments=new ArrayList<>();

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public FragmentVpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
