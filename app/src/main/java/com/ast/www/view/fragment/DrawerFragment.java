package com.ast.www.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ast.www.R;
import com.ast.www.constom.CircleImageView;
import com.ast.www.constom.UserDrawMoudle;
import com.ast.www.view.activity.AttenTionActivity;
import com.ast.www.view.activity.InformationActivity;
import com.ast.www.view.activity.LogInActivity;
import com.ast.www.view.activity.SearchForActivity;

import ch.ielse.view.SwitchView;


/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/7/19 0019
 */

public class DrawerFragment extends BaseFragment{


    private CircleImageView circleImageView;
    private LinearLayout drawer_folder;
    private LinearLayout drawer_setup;
    private UserDrawMoudle drawer_attention,drawer_Collection,drawer_searchfor,drawer_information;
    private SwitchView drawer_swit;

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
        drawer_folder = (LinearLayout) getActivity().findViewById(R.id.drawer_folder);
        drawer_setup = (LinearLayout) getActivity().findViewById(R.id.drawer_setup);
        drawer_attention= (UserDrawMoudle) getActivity().findViewById(R.id.drawer_attention);
//        drawer_Collection= (UserDrawMoudle) getActivity().findViewById(R.id.drawer_Collection);
        drawer_searchfor= (UserDrawMoudle) getActivity().findViewById(R.id.drawer_searchfor);
        drawer_information= (UserDrawMoudle) getActivity().findViewById(R.id.drawer_information);
        drawer_swit = (SwitchView) getActivity().findViewById(R.id.drawer_swit);
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
         //登录
         circleImageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent it=new Intent(getActivity(), LogInActivity.class);
                 getActivity().startActivity(it);
             }
         });

        //关注
        drawer_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), AttenTionActivity.class);
                getActivity().startActivity(it);
            }
        });
//        //收藏
//        drawer_Collection.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it=new Intent(getActivity(), CollectionActivity.class);
//                getActivity().startActivity(it);
//            }
//        });
        //搜索
        drawer_searchfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), SearchForActivity.class);
                getActivity().startActivity(it);
            }
        });
        //信息
        drawer_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), InformationActivity.class);
                getActivity().startActivity(it);
            }
        });
        //文件夹
        drawer_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //设置
        drawer_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        drawer_swit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
    }




}
