package com.ast.www.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ast.www.R;
import com.ast.www.view.CircleImageView;
import com.ast.www.view.activity.LogInActivity;


/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/7/19 0019
 */

public class DrawerFragment extends BaseFragment{


    private CircleImageView circleImageView;

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_drawerlayout;
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
        circleImageView = (CircleImageView) getActivity().findViewById(R.id.user_head);

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
         circleImageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent it=new Intent(getActivity(), LogInActivity.class);
                 getActivity().startActivity(it);
             }
         });
    }


}
