package com.ast.www.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.model.bean.ClassBean;
import com.ast.www.model.bean.RegisteredBean;
import com.ast.www.model.util.Constant;
import com.ast.www.model.util.IsUtils;
import com.ast.www.model.util.Utils;
import com.ast.www.presenter.TestPreseneter;
import com.ast.www.view.iview.IBaseView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/28.
 */

public class OtherLogInActivity extends BaseAvtivity<TestPreseneter> {

    private ImageView register_back;
    private LinearLayout register_lay;
    private TextView register_register;
    private Button otherlogin_but_login;
    private EditText otherlogin_edtext_phone,otherlogin_edtext_password;
    private TextView otherlogin_text_tourists;

    @Override
    protected void createmPresenter() {
      mPresenter=new TestPreseneter();
      mPresenter.attach(new IBaseView() {
          @Override
          public void onData(Object o) {
              String s= (String)o;
              try {
                  JSONObject jsonObject=new JSONObject(s);
                  String code = jsonObject.getString("code");
                  if(code.equals("200")){

                      RegisteredBean registeredBean = Constant.GsonToBean(s, RegisteredBean.class);
                      IsUtils.Tos(OtherLogInActivity.this,"登录成功");

                      //获取当前系统时间
                      SimpleDateFormat formatter=new SimpleDateFormat   ("yyyy.MM.dd.HH.mm.ss");
                      Date curDate =  new Date(System.currentTimeMillis());
                      String  strDate   =   formatter.format(curDate);

                      //将用户信息存入SharedPreferences中
                      Utils.getEdit(OtherLogInActivity.this)
                              .putString("userName",registeredBean.getUser().getUserName())
                              .putString("userId",registeredBean.getUser().getUserId()+"")
                              .putString("userSex",registeredBean.getUser().getUserSex())
                              .putString("strDate",strDate)
                              .commit();

                      setResult(1,getIntent());

                      finish();
                  }else {
                      IsUtils.Tos(OtherLogInActivity.this,"账号密码错误");
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
        register_back = (ImageView) findViewById(R.id.register_back);
        register_lay = (LinearLayout) findViewById(R.id.register_lay);
        register_register = (TextView) findViewById(R.id.register_register);
        otherlogin_but_login = (Button) findViewById(R.id.otherlogin_but_login);
        otherlogin_edtext_phone= (EditText) findViewById(R.id.otherlogin_edtext_phone);
        otherlogin_edtext_password= (EditText) findViewById(R.id.otherlogin_edtext_password);
        otherlogin_text_tourists = (TextView) findViewById(R.id.otherlogin_text_Tourists);
    }

    @Override
    protected void initData() {
        register_lay.getBackground().setAlpha(90);
    }

    @Override
    protected void initListener() {
        //回退
        register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_from_right);
            }
        });
        //注册
        register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(OtherLogInActivity.this,ReGisterActivity.class),0);
                //启动动画
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
            }
        });
        //登录
        otherlogin_but_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sphone = otherlogin_edtext_phone.getText().toString();
                String spass= otherlogin_edtext_password.getText().toString();
                if(IsUtils.isNull(sphone)&&IsUtils.isNull(spass)){
                    Map<String, String> maps = new HashMap<String, String>();
                    maps.put("userPassword",spass);
                    maps.put("userPhone",sphone);
                    mPresenter.post("user/addLogin",maps, ClassBean.class);
                }else{
                    IsUtils.Tos(OtherLogInActivity.this,"请输入账号或密码");
                }
            }
        });
        otherlogin_text_tourists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1,getIntent());
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==1){
            setResult(1,getIntent());
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_otherlogin;
    }
}
