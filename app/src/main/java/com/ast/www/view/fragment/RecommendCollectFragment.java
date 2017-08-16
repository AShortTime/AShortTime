package com.ast.www.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ast.www.R;
import com.ast.www.model.bean.RecommendHotBean;
import com.ast.www.model.util.Utils;
import com.ast.www.presenter.HomePresenter;
import com.ast.www.view.activity.DetailJokeActivity;
import com.ast.www.view.activity.DetailVideoActivity;
import com.ast.www.view.adapter.RlvAdapter;
import com.ast.www.view.iview.IBaseView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.superplayer.library.SuperPlayer;
import com.superplayer.library.SuperPlayerManage;
import com.superplayer.library.mediaplayer.IjkVideoView;

import java.util.HashMap;
import java.util.List;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/7/20 0020
 */

public class RecommendCollectFragment extends BaseFragment<HomePresenter> {
    private RecyclerView mrlv;
    private SuperPlayer player;
    private int lastPostion = -1;
    private int postion = -1;
    private LinearLayoutManager llm;
    private RlvAdapter adapter;
    SmartRefreshLayout smartl;

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
        player = SuperPlayerManage.getSuperManage().initialize(getActivity());
        player.setShowTopControl(false).setSupportGesture(false);
        player.setShowTopControl(false);
        player.setgkq(false);
        smartl = (SmartRefreshLayout) view.findViewById(R.id.smartLayout);
        mrlv = (RecyclerView) view.findViewById(R.id.rlv);
        llm = new LinearLayoutManager(getActivity());
        mrlv.setLayoutManager(llm);
        adapter = new RlvAdapter(getActivity());
        mrlv.setAdapter(adapter);

        mrlv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

    private void setItemOnclick(RlvAdapter adapter) {
        adapter.setOnItemClickListener(new RlvAdapter.OnItemClickListener() {


            @Override
            public void OnItemClick(View view, int position, RecommendHotBean.ResourceBean resourceBean) {
                if(resourceBean.getDictionaryValue().equals("1")){
                    Intent intent = new Intent(getActivity(), DetailVideoActivity.class);
                    intent.putExtra("detail", resourceBean);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(), DetailJokeActivity.class);
                    intent.putExtra("detail", resourceBean);
                    startActivity(intent);
                }

                if (player != null) {
                    player.stop();
                    player.release();
                    player.showView(R.id.adapter_player_control);
                }
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });
    }

    private void setSuperplayer(RlvAdapter adapter) {
        adapter.setPlayClick(new RlvAdapter.onPlayClick() {
            @Override
            public void onPlayclick(int position, RelativeLayout image, String videosrc) {
                image.setVisibility(View.GONE);
                if (player.isPlaying() && lastPostion == position) {
                    return;
                }

                postion = position;
                if (player.getVideoStatus() == IjkVideoView.STATE_PAUSED) {
                    if (position != lastPostion) {
                        player.stopPlayVideo();
                        player.release();
                    }
                }
                if (lastPostion != -1) {
                    player.showView(R.id.adapter_player_control);
                }

                View view = mrlv.findViewHolderForAdapterPosition(position).itemView;
                FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.adapter_super_video);
                frameLayout.removeAllViews();
                player.showView(R.id.adapter_player_control);
                //player.release();
                frameLayout.addView(player);
//                player.play(Environment.getExternalStorageDirectory().getPath() + "/oppo.mp4");
                player.play("http://169.254.1.100/a.flv");
                Toast.makeText(getActivity(), "position:" + position, Toast.LENGTH_SHORT).show();
                lastPostion = position;
            }
        });
        player.onComplete(new Runnable() {
            @Override
            public void run() {
                ViewGroup last = (ViewGroup) player.getParent();//找到videoitemview的父类，然后remove
                if (last != null && last.getChildCount() > 0) {
                    last.removeAllViews();
                    View itemView = (View) last.getParent();
                    if (itemView != null) {
                        itemView.findViewById(R.id.adapter_player_control).setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        mrlv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                int index = mrlv.getChildAdapterPosition(view);
                View controlview = view.findViewById(R.id.adapter_player_control);
                if (controlview == null) {
                    return;
                }
                view.findViewById(R.id.adapter_player_control).setVisibility(View.VISIBLE);
                if (index == postion) {
                    FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.adapter_super_video);
                    frameLayout.removeAllViews();
                    if (player != null &&
                            ((player.isPlaying()) || player.getVideoStatus() == IjkVideoView.STATE_PAUSED)) {
                        view.findViewById(R.id.adapter_player_control).setVisibility(View.GONE);
                    }
                    if (player.getVideoStatus() == IjkVideoView.STATE_PAUSED) {
                        if (player.getParent() != null)
                            ((ViewGroup) player.getParent()).removeAllViews();
                        frameLayout.addView(player);
                        return;
                    }
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                int index = mrlv.getChildAdapterPosition(view);
                if ((index) == postion) {
                    if (true) {
                        if (player != null) {
                            player.stop();
                            player.release();
                            player.showView(R.id.adapter_player_control);
                        }
                    }
                }
            }
        });
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if (player != null) {
//            player.onConfigurationChanged(newConfig);
//            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//                fullScreen.setVisibility(View.GONE);
//                fullScreen.removeAllViews();
//                mrlv.setVisibility(View.VISIBLE);
//                if (postion <= llm.findLastVisibleItemPosition()
//                        && postion >= llm.findFirstVisibleItemPosition()) {
//                    View view = mrlv.findViewHolderForAdapterPosition(postion).itemView;
//                    FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.adapter_super_video);
//                    frameLayout.removeAllViews();
//                    ViewGroup last = (ViewGroup) player.getParent();//找到videoitemview的父类，然后remove
//                    if (last != null) {
//                        last.removeAllViews();
//                    }
//                    frameLayout.addView(player);
//                }
//                int mShowFlags =
//                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//                fullScreen.setSystemUiVisibility(mShowFlags);
//            } else {
//                ViewGroup viewGroup = (ViewGroup) player.getParent();
//                if (viewGroup == null)
//                    return;
//                viewGroup.removeAllViews();
//                fullScreen.addView(player);
//                fullScreen.setVisibility(View.VISIBLE);
//                int mHideFlags =
//                        View.SYSTEM_UI_FLAG_LOW_PROFILE
//                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                                | View.SYSTEM_UI_FLAG_IMMERSIVE
//                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//                fullScreen.setSystemUiVisibility(mHideFlags);
//            }
//        } else {
//            fullScreen.setVisibility(View.GONE);
//        }
//    }

    /**
     * 执行数据的加载
     */
    @Override
    protected void initData() {
//        http://192.168.1.100/quarter/collection/selectcollection?userId=3
        HashMap<String, String> map = new HashMap<>();
        String id = Utils.getSharedPrefers(getActivity()).getString("userId", "1");
        map.put("userId", id);
        mPresenter.get("collection/selectcollection", map, RecommendHotBean.class);

    }

    /**
     * 关联presenter
     */
    @Override
    protected void createmPresenter() {
        mPresenter = new HomePresenter();
        mPresenter.attach(new IBaseView<RecommendHotBean>() {
            @Override
            public void onData(RecommendHotBean recommendHotBean) {
                boolean b = recommendHotBean.getCode().equals("200");
                if (b & recommendHotBean.getResource().size() != 0) {
                    List<RecommendHotBean.ResourceBean> data = recommendHotBean.getResource();
                    adapter.setList(data);

                }
                smartl.finishRefresh();
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
        //设置条目监听
        setItemOnclick(adapter);
        //设置播放器播放
        setSuperplayer(adapter);
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

    @Override
    public void onPause() {
        Log.e("11", "onpause");

        if (player != null) {
            player.onPause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        if (player != null) {
            player.onResume();
        }

        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (player != null) {
            player.onDestroy();
        }
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }
}
