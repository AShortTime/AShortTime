package com.ast.www.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ast.www.R;
import com.ast.www.view.CircleImageView;
import com.ast.www.view.fragment.DrawerFragment;
import com.ast.www.view.fragment.JokeFragment;
import com.ast.www.view.fragment.RecommendFragment;
import com.ast.www.view.fragment.VideoFragment;

import java.util.ArrayList;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/7/20 0020
 */

public class HomeActivity extends BaseAvtivity  implements View.OnClickListener,BottomNavigationBar.OnTabSelectedListener{


    private CircleImageView homeCircleView;
    private TextView homeTitle;
    private ImageView homeEdit;
    private Toolbar homeToolbar;
    private RelativeLayout homeStartDrawerLayout;
    private ArrayList<Fragment> fragments;
    private DrawerLayout drawerLayout;
    private BottomNavigationBar homeBottombar;
    private RecommendFragment mrecommendFragment;
    private JokeFragment mjokeFragment;
    private VideoFragment mvideoFragment;

    @Override
    protected void createmPresenter() {

    }


    /**
     * 初始化ui
     **/
    @Override
    protected void initUI() {
        homeStartDrawerLayout = (RelativeLayout) findViewById(R.id.home_startDrawerLayout);
        homeToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        homeEdit = (ImageView) findViewById(R.id.home_edit);
        homeTitle = (TextView) findViewById(R.id.home_title);
        homeCircleView = (CircleImageView) findViewById(R.id.home_circleView);
        homeBottombar = (BottomNavigationBar) findViewById(R.id.home_bottombar);
        homeCircleView.setOnClickListener(this);

    }

    /**
     * 初始化数据
     **/
    @Override
    protected void initData() {
        setSupportActionBar(homeToolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.layout_left_drawer);
        mrecommendFragment = new RecommendFragment();
        mjokeFragment = new JokeFragment();
        mvideoFragment = new VideoFragment();
        fragments = new ArrayList<>();
        fragments.add(mrecommendFragment);
        fragments.add(mjokeFragment);
        fragments.add(mvideoFragment);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        /**
         * 初始化 底部导航栏
         */
        initBottomNavigationBar();
        /**
         * 初始化 侧滑页面
         */
        initDrawerFragment();
    }

    /**
     * 初始化侧滑页面
     */
    private void initDrawerFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_startDrawerLayout,new DrawerFragment())
                .commitAllowingStateLoss();
    }

    /**
     * 初始化 底部导航栏
     */
    private void initBottomNavigationBar() {
        //获取 BottomNavigationBar
        //设置显示模式 ：普通模式
        homeBottombar.setMode(BottomNavigationBar.MODE_FIXED);
        //设置背景风格 ：背景颜色波纹改变
        homeBottombar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        //初始化 子导航页
        BottomNavigationItem recommendItem = new BottomNavigationItem(R.mipmap.chat_press, "推荐");
        BottomNavigationItem crossTalkItem = new BottomNavigationItem(R.mipmap.discovery_press, "段子");
        BottomNavigationItem videoItem = new BottomNavigationItem(R.mipmap.me_press, "视频");

        //添加子导航页
        homeBottombar.addItem(recommendItem);
        homeBottombar.addItem(crossTalkItem);
        homeBottombar.addItem(videoItem);
        //设置默认选中条目
        homeBottombar.setFirstSelectedPosition(0);
        //未活动 图标颜色
        homeBottombar.setInActiveColor(R.color.dimgrey);
        //活动时 图标颜色
        homeBottombar.setActiveColor(R.color.appBlue);
        //初始化
        homeBottombar.initialise();

        homeTitle.setText("推荐");

//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.home_rl_parentview,fragments.get(0))
//                .commitAllowingStateLoss();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_rl_parentview, fragments.get(0))
                .add(R.id.home_rl_parentview, fragments.get(1))
                .add(R.id.home_rl_parentview, fragments.get(2))
                .hide(fragments.get(1))
                .hide(fragments.get(2))
                .commitAllowingStateLoss();

        /**
         * BottomNavigationBar 点击监听
         */
        homeBottombar.setTabSelectedListener(this);
    }


    /**
     * 初始化监听
     **/
    @Override
    protected void initListener() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    int tag=0;

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                homeTitle.setText("推荐");
                break;
            case 1:
                homeTitle.setText("段子");
                break;
            case 2:
                homeTitle.setText("视频");
                break;
        }
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.home_rl_parentview,fragments.get(position))
//                .commitAllowingStateLoss();
        if (tag != position) {
            getSupportFragmentManager().beginTransaction()
                    .show(fragments.get(position))
                    .hide(fragments.get(tag))
                    .commitAllowingStateLoss();
            tag = position;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_circleView:
                if (drawerLayout.isDrawerOpen(Gravity.START)){
                    drawerLayout.closeDrawers();
                }else {
                    drawerLayout.openDrawer(Gravity.START);
                }
                break;
        }
    }
}
