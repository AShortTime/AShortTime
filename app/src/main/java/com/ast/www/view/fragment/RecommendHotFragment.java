package com.ast.www.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ast.www.R;
import com.ast.www.constom.Userinfoview;
import com.ast.www.view.adapter.RlvAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/7/20 0020
 */

public class RecommendHotFragment extends BaseFragment {

    private RecyclerView mrlv;

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_child;
    }

    /**
     * 该抽象方法就是 初始化view
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mrlv = (RecyclerView) view.findViewById(R.id.rlv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mrlv.setLayoutManager(llm);
        RlvAdapter adapter = new RlvAdapter(getActivity());
        List<String> l=new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            l.add(i+"");
        }
        adapter.setList(l);
        mrlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new RlvAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

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

    }
}
