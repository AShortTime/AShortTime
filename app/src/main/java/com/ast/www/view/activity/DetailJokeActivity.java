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
import com.ast.www.model.bean.Codebean;
import com.ast.www.model.bean.DetailCommentBean;
import com.ast.www.model.bean.RecommendHotBean;
import com.ast.www.model.util.Utils;
import com.ast.www.presenter.HomePresenter;
import com.ast.www.view.adapter.DetailRlvadapter;
import com.ast.www.view.iview.IBaseView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.superplayer.library.SuperPlayer;
import com.superplayer.library.SuperPlayerManage;

import org.w3c.dom.Text;

import java.util.HashMap;

/**
 * 作者:郭凯奇
 * 时间: 2017/8/4 14:16
 * Title:
 * Text:
 */

public class DetailJokeActivity extends BaseAvtivity<HomePresenter> implements View.OnClickListener{

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
    private SimpleDraweeView image;
    private TextView joke;

    @Override
    protected void createmPresenter() {
        Intent intent = getIntent();
        resourceBean = (RecommendHotBean.ResourceBean) intent.getSerializableExtra("detail");

    }

    @Override
    protected void initUI() {

        joke = (TextView) findViewById(R.id.detail_joke);
        image = (SimpleDraweeView) findViewById(R.id.detail_image);
        if(resourceBean.getDictionaryValue().equals("2")){
            image.setVisibility(View.GONE);
        }
        joke.setText(resourceBean.getContent());
        image.setImageURI(resourceBean.getSrc());
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

                closeInputMethod();
                if(!TextUtils.isEmpty(Utils.getSharedPrefers(this).getString("userId",""))) {
                    if (!TextUtils.isEmpty(detailedit.getText())) {
                        HomePresenter commit = new HomePresenter();
                        commit.attach(new IBaseView() {
                            @Override
                            public void onData(Object o) {
                                detailedit.setText(null);
                                showShort("评论成功");
                                closeInputMethod();
                                initData();
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
                        commit.post("comment/addComment", map, Codebean.class);

                    } else {
                        showShort("评论为空");
                    }
                }else{
                    showShort("请先登录");
                    openActivity(LogInActivity.class);
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
        return R.layout.activity_image_detail;
    }

}
