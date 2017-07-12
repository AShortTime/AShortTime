package com.ast.www.view.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.ast.www.R;
import com.ast.www.model.bean.ClassBean;
import com.ast.www.presenter.TestPreseneter;
import com.ast.www.view.iview.IBaseView;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements IBaseView<ClassBean> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestPreseneter t=new TestPreseneter();
        t.attach(this);
        HashMap<String, String> m = new HashMap<>();
        m.put("act","goods_class");
        m.put("op","goods_detail");
        m.put("gc_id","4");

        t.get(m,ClassBean.class);
    }


    @Override
    public void onData(ClassBean classBean) {
        Log.e("shuju",classBean.getCode()+"" );
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("onerror", throwable.toString() );
    }
}
