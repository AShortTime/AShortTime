package com.ast.www.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.model.bean.AttenTionBean;
import com.ast.www.model.bean.ClassBean;
import com.ast.www.model.util.Constant;
import com.ast.www.model.util.IsUtils;
import com.ast.www.model.util.Utils;
import com.ast.www.presenter.TestPreseneter;
import com.ast.www.view.adapter.AttenTionAdapter;
import com.ast.www.view.iview.IBaseView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/28.
 */

public class AttenTionActivity extends BaseAvtivity<TestPreseneter> {

    private ImageView head_image_back;
    private LinearLayout head_back;
    private RecyclerView recyclerView;
    private ArrayList<AttenTionBean> list;
    private TextView attention_text_tishi;

    @Override
    protected void createmPresenter() {
            mPresenter=new TestPreseneter();
            mPresenter.attach(new IBaseView() {
                @Override
                public void onData(Object o) {
                    String s= (String) o;
                    try {
                        JSONObject jsonObject=new JSONObject(s);
                        String code = jsonObject.getString("code");
                        if(code.equals("200")){
                            AttenTionBean attenTionBean = Constant.GsonToBean(s, AttenTionBean.class);
                            list=new ArrayList<AttenTionBean>();
                            list.add(attenTionBean);
                            if(list!=null&&list.get(0).getUser()!=null&&list.get(0).getUser().size()>=1) {
                                attention_text_tishi.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                AttenTionAdapter attenTionAdapter = new AttenTionAdapter(list, AttenTionActivity.this);
                                recyclerView.setAdapter(attenTionAdapter);
                            }
                        }else{
                            IsUtils.Tos(AttenTionActivity.this,"请检查网络");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });

    }



    @Override
    protected void initUI() {
        attention_text_tishi = (TextView) findViewById(R.id.attention_text_tishi);
        head_back = (LinearLayout) findViewById(R.id.head_back);
        recyclerView = (RecyclerView) findViewById(R.id.attention_recy_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        Map<String,String> maps=new HashMap<String,String>();
        maps.put("Userid", Utils.getSharedPrefers(AttenTionActivity.this).getString("userId",""));
        mPresenter.get("user/myFollow",maps, AttenTionBean.class);

    }

    @Override
    protected void initListener() {
        head_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_attention;
    }
}
