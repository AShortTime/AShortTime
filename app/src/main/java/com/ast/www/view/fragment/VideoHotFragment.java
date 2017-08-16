package com.ast.www.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.ast.www.R;
import com.ast.www.model.bean.RecommendHotBean;
import com.ast.www.model.bean.VideoRlvImageBean;
import com.ast.www.presenter.HomePresenter;
import com.ast.www.view.activity.DetailVideoActivity;
import com.ast.www.view.adapter.WaterFallAdapter;
import com.ast.www.view.iview.IBaseView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.HashMap;
import java.util.List;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/7/20 0020
 */

public class VideoHotFragment extends BaseFragment<HomePresenter> {
    private RecyclerView mrlv;
        private SmartRefreshLayout smartl;
        private WaterFallAdapter waterFallAdapter;
        private HomePresenter vidp;
        private List<VideoRlvImageBean.MediaBean> media;
        /**
         * 该抽象方法就是 onCreateView中需要的layoutID
         *
         * @return
         */
        @Override
        protected int getLayoutId() {
            return R.layout.fragment_main_child;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

        }


        /**
         * 该抽象方法就是 初始化view
         *
         * @param view
         * @param savedInstanceState
         */

        @Override
        protected void initView(View view, Bundle savedInstanceState) {
            smartl = (SmartRefreshLayout) view.findViewById(R.id.smartLayout);
            mrlv = (RecyclerView) view.findViewById(R.id.rlv);
            mrlv.setLayoutManager(new
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            waterFallAdapter = new WaterFallAdapter(getActivity());
            mrlv.setAdapter(waterFallAdapter);
            //设置条目监听
            setItemOnclick(waterFallAdapter);
        }

    private void setItemOnclick(WaterFallAdapter adapter) {
        Log.e("我曹","1111111" );
        adapter.setOnItemClickListener(new WaterFallAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int positionan) {
                Log.e("我曹","2222" );
                Intent intent = new Intent(getActivity(), DetailVideoActivity.class);
                RecommendHotBean.ResourceBean bean = new RecommendHotBean.ResourceBean();
                VideoRlvImageBean.MediaBean mediaBean = media.get(positionan);
                bean.setId(mediaBean.getMediaId());
                bean.setContent(mediaBean.getMediaDescription());
                bean.setDictionaryValue(mediaBean.getMediaDictionaryValue()+"");
                bean.setSrc(mediaBean.getMediaSrc());
                bean.setPictureSrc(mediaBean.getMediaPictureSrc());
                bean.setName(mediaBean.getMediaName());
                bean.setUptime(mediaBean.getMediaUptime());
                RecommendHotBean.ResourceBean.UserBean userBean = new RecommendHotBean.ResourceBean.UserBean();
                userBean.setUserName(mediaBean.getUser().getUserName());
                userBean.setUserHead(mediaBean.getUser().getUserHead());
                userBean.setUserId(mediaBean.getUser().getUserId());
                userBean.setUserSex(mediaBean.getUser().getUserSex());
                bean.setUser(userBean);
                intent.putExtra("detail",bean);
                startActivity(intent);
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });
    }


    /**
     * 执行数据的加载
     */
    @Override
    protected void initData() {
//        http://169.254.1.100/quarter/user/findHot;
        HashMap<String, String> map = new HashMap<>();
        vidp.get("media/showMedia", map, VideoRlvImageBean.class);

    }

    /**
     * 关联presenter
     */
    @Override
    protected void createmPresenter() {
        vidp = new HomePresenter();
        vidp.attach(new IBaseView<VideoRlvImageBean>() {
            @Override
            public void onData(VideoRlvImageBean rlib) {
                boolean b = rlib.getCode().equals("200");
                media = rlib.getMedia();
                waterFallAdapter.setmList(media);
                if (smartl.isRefreshing()) {
                    smartl.finishRefresh();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (smartl.isRefreshing()) {
                    smartl.finishRefresh();
                }
            }
        });
    }

    /**
     * 控件的监听
     */
    @Override
    protected void initListener() {

        smartl.setOnRefreshListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                initData();
            }
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initData();
            }
        });
    }
}
