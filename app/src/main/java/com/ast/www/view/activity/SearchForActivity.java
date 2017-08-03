package com.ast.www.view.activity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.model.bean.ClassBean;
import com.ast.www.model.bean.SearchForBean;
import com.ast.www.model.util.Constant;
import com.ast.www.model.util.IsUtils;
import com.ast.www.presenter.TestPreseneter;
import com.ast.www.view.adapter.SearchForAdapter;
import com.ast.www.view.iview.IBaseView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/31.
 */

public class SearchForActivity extends BaseAvtivity<TestPreseneter> {

    private TextView head_text_te2;
    private TextView head_text_te1;
    private LinearLayout head_back;
    private EditText search_for_edtext;
    private ImageView search_for_sousuo;
    private ListView search_for_list;
    private RecyclerView search_for_recy;
    private ArrayList<SearchForBean> list;

    @Override
    protected void createmPresenter() {
        mPresenter = new TestPreseneter();
        mPresenter.attach(new IBaseView() {
            @Override
            public void onData(Object o) {
                String s = (String) o;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String code = jsonObject.getString("code");
                    if (code.equals("200")) {
                        SearchForBean searchForBean = Constant.GsonToBean(s, SearchForBean.class);
                        list = new ArrayList<SearchForBean>();
                        list.add(searchForBean);
                        if (list!=null&&list.get(0).getUser().size()>=1) {
                            search_for_list.setVisibility(View.VISIBLE);
                            SearchForAdapter searchForAdapter=new SearchForAdapter(SearchForActivity.this,list);
                            search_for_list.setAdapter(searchForAdapter);
                        }else{
                            IsUtils.Tos(SearchForActivity.this,"无此用户信息");
                        }
                    } else {
                        IsUtils.Tos(SearchForActivity.this, "请检查网络");
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
        head_text_te1 = (TextView) findViewById(R.id.head_text_te1);
        head_text_te2 = (TextView) findViewById(R.id.head_text_te2);
        head_back = (LinearLayout) findViewById(R.id.head_back);

        search_for_edtext = (EditText) findViewById(R.id.search_for_edtext);
        search_for_sousuo = (ImageView) findViewById(R.id.search_for_sousuo);
        search_for_list = (ListView) findViewById(R.id.search_for_list);
        search_for_recy = (RecyclerView) findViewById(R.id.search_for_recy);
    }

    @Override
    protected void initData() {
        head_text_te1.setText("搜索好友");
        head_text_te2.setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {
        head_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        search_for_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsUtils.isNull(search_for_edtext.getText().toString())) {
                    Map<String, String> maps = new HashMap<String, String>();
                    maps.put("value", search_for_edtext.getText().toString());
                    mPresenter.get("user/findUserBy", maps, ClassBean.class);
                } else {
                    IsUtils.Tos(SearchForActivity.this, "请输入搜索信息");
                }
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search_for;
    }
}
