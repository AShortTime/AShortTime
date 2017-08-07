package com.ast.www.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ast.www.presenter.BasePresenter;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/19 14:40
 * Title:
 * Text:
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment{

    protected   T mPresenter;

    protected Activity mActivity;

    /**
     * 获得全局的，防止使用getActivity()为空
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {

        View view = LayoutInflater.from(mActivity)
                .inflate(getLayoutId(), container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        createmPresenter();
        initView(getView(),savedInstanceState);
        initData();
        initListener();
    }



    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 该抽象方法就是 初始化view
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 执行数据的加载
     */
    protected abstract void initData();

    /**
     * 关联presenter
     */
    protected abstract void createmPresenter();

    /**
     * 控件的监听
     */
    protected abstract void initListener();

    

}
