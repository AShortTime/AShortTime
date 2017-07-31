package com.ast.www.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/31.
 */

public class InformationAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private ArrayList<String> list1;
    public InformationAdapter(FragmentManager fm) {
        super(fm);
    }

    public InformationAdapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<String> list1) {
        super(fm);
        this.list = list;
        this.list1 = list1;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    //设置标题头的文字
    @Override
    public CharSequence getPageTitle(int position) {
        return list1.get(position);
    }
}
