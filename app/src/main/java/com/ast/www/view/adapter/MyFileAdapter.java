package com.ast.www.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/7.
 */

public class MyFileAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private ArrayList<String> list1;

    public MyFileAdapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<String> list1) {
        super(fm);
        this.list = list;
        this.list1 = list1;
    }

    public MyFileAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list1.get(position);
    }
}
