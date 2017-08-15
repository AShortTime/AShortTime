package com.ast.www.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ast.www.R;
import com.ast.www.model.bean.RecommendHotBean;
import com.ast.www.presenter.HomePresenter;
import com.ast.www.view.adapter.RlvAdapter;
import com.ast.www.view.iview.IBaseView;
import com.superplayer.library.SuperPlayer;
import com.superplayer.library.SuperPlayerManage;

import java.util.HashMap;
import java.util.List;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/7/20 0020
 */

public class JokeFragment extends BaseFragment<HomePresenter> {
    private RecyclerView mrlv;
    private SuperPlayer player;
    private int lastPostion = -1;
    private int postion = -1;
    private LinearLayoutManager llm;
    private RlvAdapter adapter;

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

        mrlv = (RecyclerView) view.findViewById(R.id.rlv);
        llm = new LinearLayoutManager(getActivity());
        mrlv.setLayoutManager(llm);
        adapter = new RlvAdapter(getActivity());
        mrlv.setAdapter(adapter);

        mrlv.addItemDecoration( new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

    private void setItemOnclick(RlvAdapter adapter) {
        adapter.setOnItemClickListener(new RlvAdapter.OnItemClickListener() {


            @Override
            public void OnItemClick(View view, int position, RecommendHotBean.ResourceBean resourceBean) {

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
        mPresenter.get("user/selectpiccha",map,RecommendHotBean.class);

    }

    /**
     * 关联presenter
     */
    @Override
    protected void createmPresenter() {
        mPresenter=new HomePresenter();
        mPresenter.attach(new IBaseView<RecommendHotBean>() {
            @Override
            public void onData(RecommendHotBean recommendHotBean) {
                boolean b = recommendHotBean.getCode().equals("200");
                if(b){
                    List<RecommendHotBean.ResourceBean> data = recommendHotBean.getResource();
                    adapter.setList(data);
                }
            }
            @Override
            public void onError(Throwable throwable) {

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
    }

    @Override
    public void onPause() {
        Log.e("11","onpause" );

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
