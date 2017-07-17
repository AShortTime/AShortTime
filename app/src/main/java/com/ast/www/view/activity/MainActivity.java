package com.ast.www.view.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.model.bean.ClassBean;
import com.ast.www.presenter.TestPreseneter;
import com.ast.www.view.iview.IBaseView;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class MainActivity extends BaseAvtivity implements IBaseView<ClassBean> {

    private TextView mUserNameTxt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void initUI() {
        mUserNameTxt= (TextView) findViewById(R.id.text1111);
        LoadDataHandler lh=new LoadDataHandler(this);
        lh.sendEmptyMessage(0);
    }

    @Override
    protected void initData() {
        TestPreseneter t = new TestPreseneter();
        t.attach(this);
        HashMap<String, String> m = new HashMap<>();
        m.put("act", "goods_class");
        m.put("op", "goods_detail");
        m.put("gc_id", "4");
        t.get(m, ClassBean.class);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void onData(ClassBean classBean) {
        showShort(classBean.getCode() + "");
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("onerror", throwable.toString());
    }

    static class LoadDataHandler extends Handler {
        private SoftReference<MainActivity> activitySRF = null;

        public LoadDataHandler(MainActivity activity) {
            activitySRF = new SoftReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 因为Handler是异步的，存在退出当前类之后才接收到handler消息的情况，
            // 并且软引用持有的对象会在堆内存不足时存在被回收的可能，所以这里需要判空处理
            if (null == activitySRF || null == activitySRF.get()) {
                return;
            }
            switch (msg.what) {
                case 0: {
                    activitySRF.get().mUserNameTxt.setText("123");
                }
                break;
                default:break;
            }
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.e("dispatch",  b+"");
        return b;
    }
}

