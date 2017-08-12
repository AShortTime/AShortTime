package com.ast.www.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.constom.CircleImageView;
import com.ast.www.constom.UserDrawMoudle;
import com.ast.www.model.util.FirstEvent;
import com.ast.www.model.util.Utils;
import com.ast.www.view.activity.AttenTionActivity;
import com.ast.www.view.activity.InformationActivity;
import com.ast.www.view.activity.LogInActivity;
import com.ast.www.view.activity.MyFileActivity;
import com.ast.www.view.activity.SearchForActivity;
import com.ast.www.view.activity.SetUpActivity;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private RelativeLayout drawer_not_logged_in_rela;
    private TextView user_name;
    private ImageView user_sex;
    private TextView user_text;

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
        drawer_not_logged_in_rela = (RelativeLayout) getActivity().findViewById(R.id.drawer_Not_logged_in_rela);
        user_name = (TextView) getActivity().findViewById(R.id.user_name);
        user_sex = (ImageView) getActivity().findViewById(R.id.user_sex);
        user_text = (TextView) getActivity().findViewById(R.id.user_text);

        EventBus.getDefault().register(this);

        InItLogged();


    }



    private void InItLogged() {
        SharedPreferences sharedPrefers = Utils.getSharedPrefers(getActivity());
        String userName = sharedPrefers.getString("userName","");
        String userSex = sharedPrefers.getString("userSex","男");
        String userSignature = sharedPrefers.getString("userSignature","编辑个性签名");
        String userHead = sharedPrefers.getString("userHead","");
        if(!userName.equals("")&&userName!=null){
            drawer_not_logged_in_rela.setVisibility(View.GONE);

            if(userHead.length()>5&&userHead!=null){
                Glide.with(getActivity())
                        .load(userHead)
                        .crossFade()
                        .into(circleImageView);
            }
            user_name.setText(userName);
            if(userSex.equals("男")){
                DisplayMetrics dm = getResources().getDisplayMetrics();
                int width = dm.widthPixels;
                int height = dm.heightPixels;
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,height);
                params.width=80;
                params.height=80;
                params.setMargins(650,190,0,0);
                user_sex.setLayoutParams(params);
                user_sex.setBackgroundResource(R.mipmap.sexnan);
            }
            user_text.setText(userSignature);
            circleImageView.setVisibility(View.VISIBLE);
            user_name.setVisibility(View.VISIBLE);
            user_sex.setVisibility(View.VISIBLE);
            user_text.setVisibility(View.VISIBLE);
        }else{
            drawer_not_logged_in_rela.setVisibility(View.VISIBLE);
            circleImageView.setVisibility(View.GONE);
            user_name.setVisibility(View.GONE);
            user_sex.setVisibility(View.GONE);
            user_text.setVisibility(View.GONE);
        }
    }

    /**
     * 关联presenter
     */
    @Override
    protected void createmPresenter() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
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
         drawer_not_logged_in_rela.setOnClickListener(new View.OnClickListener() {
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
                Intent it=new Intent(getActivity(), MyFileActivity.class);
                getActivity().startActivity(it);
            }
        });
        //设置
        drawer_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), SetUpActivity.class);
                getActivity().startActivity(it);
            }
        });
        //黑夜间
        drawer_swit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FirstEvent event) {
        InItLogged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
