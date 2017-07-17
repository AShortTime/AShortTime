package com.ast.www.view.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import com.ast.www.R;
import com.ast.www.model.bean.ClassBean;
import com.ast.www.presenter.TestPreseneter;
import com.ast.www.view.iview.IBaseView;
import java.util.HashMap;

public class MainActivity extends BaseAvtivity<TestPreseneter> implements IBaseView<ClassBean> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void createmPresenter() {
        mPresenter=new TestPreseneter();
        mPresenter.attach(this);
    }

    @Override
    protected void initUI() {
    }

    @Override
    protected void initData() {
        HashMap<String, String> m = new HashMap<>();
        m.put("act", "goods_class");
        m.put("op", "goods_detail");
        m.put("gc_id", "4");
        mPresenter.get(m, ClassBean.class);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void onData(ClassBean o) {

    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("onerror", throwable.toString());
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.e("dispatch",  b+"");
        return b;
    }
}

