package com.ast.www.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.ast.www.R;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/7/20 0020
 */

public class RecommendCollectFragment extends BaseFragment {
    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_child;
    }

    /**
     * 该抽象方法就是 初始化view
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

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
