package com.ast.www.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ast.www.R;
import com.ast.www.model.bean.Codebean;
import com.ast.www.model.bean.RecommendHotBean;
import com.ast.www.presenter.HomePresenter;
import com.ast.www.view.adapter.AuditAdapter;
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
 * 作者:郭凯奇
 * 时间: 2017/8/15 10:20
 * Title:
 * Text:
 */

public class AuditActivity extends BaseAvtivity<HomePresenter> {
    private RecyclerView mrlv;
    private SuperPlayer player;
    private int lastPostion = -1;
    private int postion = -1;
    private LinearLayoutManager llm;
    private AuditAdapter adapter;
    private SmartRefreshLayout smartl;
    private ProgressDialog dialog;
    private List<RecommendHotBean.ResourceBean> data;
    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     *
     * @return
     */

    private void setItemOnclick(AuditAdapter adapter){
        adapter.setOnItemClickListener(new AuditAdapter.OnItemClickListener() {

            @Override
            public void OnItemClick(View view, int position) {
                switch (view.getId()){
                    case R.id.pass:{
                        HomePresenter hp=new HomePresenter();
                        hp.attach(new IBaseView<Codebean>() {
                            @Override
                            public void onData(Codebean o) {
                                showShort(o.getCode());
                                initData();
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                showShort(throwable.toString());
                                initData();
                            }
                        });
                        HashMap<String, String> map = new HashMap<>();
                        map.put("dictionaryValue",data.get(position).getDictionaryValue());
                        map.put("id",data.get(position).getId()+"");
                        map.put("status","1");
                        hp.get("admain/update",map,Codebean.class);
                    }break;
                    case R.id.unpass:{
                        HomePresenter hp=new HomePresenter();
                        hp.attach(new IBaseView<Codebean>() {
                            @Override
                            public void onData(Codebean o) {
                                showShort(o.getCode());
                                initData();
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                showShort(throwable.toString());
                                initData();
                            }
                        });
                        HashMap<String, String> map = new HashMap<>();
                        map.put("dictionaryValue",data.get(position).getDictionaryValue());
                        map.put("id",data.get(position).getId()+"");
                        map.put("status","2");
                        hp.get("admain/update",map,Codebean.class);
                    }break;
                }
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });
    }

    private void setSuperplayer(AuditAdapter adapter) {
//        player = SuperPlayerManage.getSuperManage().initialize(this);
//        player = SuperPlayerManage.getSuperManage().initialize(this);
        //player.setShowTopControl(false).setSupportGesture(false);
//        player.setShowTopControl(false);
        player.setgkq(false);
//        player.setFullScreenOnly(false);
        adapter.setPlayClick(new AuditAdapter.onPlayClick() {
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
                Log.e("videosrc", videosrc + "11111");
//                player.play(Environment.getExternalStorageDirectory().getPath() + "/oppo.mp4");
                player.play(videosrc);
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


    /**
     * 执行数据的加载
     */
    @Override
    protected void initData() {
        dialog = ProgressDialog.show(this, "提示", "正在提交", false);
//        http://169.254.1.100/quarter/user/findHot;
        HashMap<String, String> map = new HashMap<>();
        mPresenter.get("admain/unpass", map, RecommendHotBean.class);

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
                dialog.dismiss();
                dialog=null;

                boolean b = recommendHotBean.getCode().equals("200");
                Log.e("1111",b+"" );
                if (b) {
                    data = recommendHotBean.getResource();
                    adapter.setList(data);
                }
                if (smartl.isRefreshing()) {
                    smartl.finishRefresh();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                dialog.dismiss();
                dialog=null;
                if (smartl.isRefreshing()) {
                    smartl.finishRefresh();
                }
            }
        });
    }

    @Override
    protected void initUI() {
        player = SuperPlayerManage.getSuperManage().initialize(this);
        player.setShowTopControl(false).setSupportGesture(false);
        player.setgkq(false);

        mrlv = (RecyclerView) findViewById(R.id.rlv);
        smartl = (SmartRefreshLayout) findViewById(R.id.smartLayout);
        llm = new LinearLayoutManager(this);
        mrlv.setLayoutManager(llm);
        adapter = new AuditAdapter(this);
        mrlv.setAdapter(adapter);
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

    @Override
    public int getLayout() {
        return R.layout.activity_audit;
    }

}
