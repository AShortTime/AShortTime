package com.ast.www.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.IdRes;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ast.www.R;
import com.ast.www.model.bean.DetailCommentBean;
import com.ast.www.model.bean.RecommendHotBean;
import com.ast.www.presenter.HomePresenter;
import com.ast.www.view.adapter.DetailRlvadapter;
import com.ast.www.view.iview.IBaseView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.superplayer.library.SuperPlayer;
import com.superplayer.library.SuperPlayerManage;

import java.util.HashMap;

/**
 * 作者:郭凯奇
 * 时间: 2017/8/4 14:16
 * Title:
 * Text:
 */

public class DetailActivity extends BaseAvtivity<HomePresenter> implements View.OnClickListener, SuperPlayer.OnNetChangeListener {

    private SuperPlayer player;
    private ImageView detailback;
    private RadioGroup detailrg;
    private ImageView detailshare;
    private TextView detailusername;
    private SimpleDraweeView detailuserhead;
    private TextView detailtitle;
    private TextView detailtime;
    private TextView detiallooknum;
    private RecyclerView detailrlv;
    private EditText detailedit;
    private TextView detailcommit;
    private RadioButton detailtop;
    private RadioButton detailfoot;
    private RecommendHotBean.ResourceBean resourceBean;
    private SimpleDraweeView detailpicture;
    private RelativeLayout playercontrol;
    private RelativeLayout rl;
    private int i = 0;
    private FrameLayout frameLayout;
    private DetailRlvadapter adapter;

    @Override
    protected void createmPresenter() {
        Intent intent = getIntent();
        resourceBean = (RecommendHotBean.ResourceBean) intent.getSerializableExtra("detail");

    }

    @Override
    protected void initUI() {
        //视频的相关控件
//        player = (SuperPlayer) findViewById(R.id.super_player);\
        detailpicture = (SimpleDraweeView) findViewById(R.id.detail_picture);
        detailpicture.setImageURI(resourceBean.getPictureSrc());
        rl = (RelativeLayout) findViewById(R.id.adapter_super_video_layout);
        playercontrol = (RelativeLayout) findViewById(R.id.adapter_player_control);


        //toolbar 上的按钮们

        detailback = (ImageView) findViewById(R.id.item_detail_back);
        detailrg = (RadioGroup) findViewById(R.id.detail_rg);
        detailtop = (RadioButton) findViewById(R.id.detail_top);
        detailfoot = (RadioButton) findViewById(R.id.detail_foot);

        detailshare = (ImageView) findViewById(R.id.detail_share);
        detailusername = (TextView) findViewById(R.id.user_name_detail);
        detailusername.setText(resourceBean.getUser().getUserName());
        detailuserhead = (SimpleDraweeView) findViewById(R.id.user_head_detail);
        if (!TextUtils.isEmpty(resourceBean.getUser().getUserHead())) {
            detailuserhead.setImageURI(resourceBean.getUser().getUserHead());
        }

        //页面内的View

//        restart = (TextView) findViewById(R.id.restart);
        detailtitle = (TextView) findViewById(R.id.detail_title);
        detailtitle.setText(resourceBean.getDescription());
        detiallooknum = (TextView) findViewById(R.id.detail_looknum);
        detiallooknum.setText(resourceBean.getPlaytimes());
        detailtime = (TextView) findViewById(R.id.detail_time);
        detailtime.setText(resourceBean.getUptime());
        frameLayout = (FrameLayout) findViewById(R.id.adapter_super_video);
        frameLayout.removeAllViews();
        //评论
        detailrlv = (RecyclerView) findViewById(R.id.detail_rlv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        detailrlv.setLayoutManager(linearLayoutManager);
        detailrlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new DetailRlvadapter(this);
        detailrlv.setAdapter(adapter);
        //发表评论
        detailedit = (EditText) findViewById(R.id.detail_edit);
        detailcommit = (TextView) findViewById(R.id.detail_commit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.item_detail_back: {
                this.finish();
            }
            break;
            //发表人头像姓名
            case R.id.user_name_detail:
            case R.id.user_head_detail: {

            }
            break;
            //发表评论
            case R.id.detail_commit: {
                if(!TextUtils.isEmpty(detailedit.getText())){
                    HomePresenter commit = new HomePresenter();
                    commit.attach(new IBaseView() {
                        @Override
                        public void onData(Object o) {
                            showShort("评论成功");
                            detailedit.setText("");
                            closeInputMethod();
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            showShort("评论失败,未知错误");
                        }
                    });
                    HashMap<String, String> map = new HashMap<>();
                    map.put("commentUserId", "1");
                    map.put("commentContent", detailedit.getText().toString());
                    map.put("commentDictionaryValue", resourceBean.getDictionaryValue());
                    map.put("commentCharacterPictureMediaId", resourceBean.getId() + "");
                    map.put("commentPid", "1");
                    commit.post("comment/addComment", map, null);
                    initData();
                }else{
                    showShort("评论为空");
                }
            }
            break;

        }
    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("CommentCharacterPictureMediaId", resourceBean.getId() + "");
        map.put("CommentDictionaryValue", resourceBean.getDictionaryValue());
        HomePresenter pl = new HomePresenter();
        pl.attach(new IBaseView<DetailCommentBean>() {

            @Override
            public void onData(DetailCommentBean detailCommentBean) {
               if(detailCommentBean.getCode()==200){
                   adapter.setList(detailCommentBean.getComment());
                   adapter.notifyDataSetChanged();
               }else {
                   showShort("评论显示未知错误");
               }

            }
            @Override
            public void onError(Throwable throwable) {

            }
        });
        pl.get("comment/selectAllComment", map, DetailCommentBean.class);
    }

    @Override
    protected void initListener() {
        player = SuperPlayerManage.getSuperManage().initialize(this);
        player.setShowTopControl(false).setSupportGesture(false);
        player.setgkq(true);
        player.setScaleType(SuperPlayer.SCALETYPE_FITXY);
        player.setPlayerWH(0, player.getMeasuredHeight());//设置竖屏的时候屏幕的高度，如果不设置会切换后按照16:9的高度重置
        player.setNetChangeListener(true)//设置监听手机网络的变化
                .setOnNetChangeListener(this)
                .onPrepared(new SuperPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared() {
                        playercontrol.setVisibility(View.GONE);
                    }
                });
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playercontrol.setVisibility(View.GONE);
                if (player.getParent() != null) {
                    ((ViewGroup) player.getParent()).removeAllViews();
                    frameLayout.removeAllViews();
                }
                frameLayout.removeAllViews();
                frameLayout.addView(player);
                player.play(resourceBean.getSrc());
            }
        });

        detailback.setOnClickListener(this);
        detailshare.setOnClickListener(this);
        detailusername.setOnClickListener(this);
        detailuserhead.setOnClickListener(this);
        detailcommit.setOnClickListener(this);

        //发表评论
        detailedit = (EditText) findViewById(R.id.detail_edit);
        detailcommit = (TextView) findViewById(R.id.detail_commit);

        detailrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    //赞
                    case R.id.detail_top: {
                        detailtop.setEnabled(false);
                        detailfoot.setEnabled(false);
                    }
                    break;
                    //踩
                    case R.id.detail_foot: {
                        detailtop.setEnabled(false);
                        detailfoot.setEnabled(false);
                    }
                    break;
                }
            }
        });

    }


    // 1视频 2文字 3图片
    @Override
    public int getLayout() {
        return R.layout.activity_video_detail;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onWifi() {
        Toast.makeText(this, "当前网络环境是WIFI", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMobile() {
        Toast.makeText(this, "当前网络环境是手机网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnect() {
        Toast.makeText(this, "网络链接断开", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoAvailable() {
        Toast.makeText(this, "无网络链接", Toast.LENGTH_SHORT).show();
    }

}
