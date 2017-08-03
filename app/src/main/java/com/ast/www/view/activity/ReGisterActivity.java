package com.ast.www.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ast.www.R;
import com.ast.www.model.bean.ClassBean;
import com.ast.www.model.bean.RegisteredBean;
import com.ast.www.model.util.Constant;
import com.ast.www.model.util.IsUtils;
import com.ast.www.presenter.TestPreseneter;
import com.ast.www.view.iview.IBaseView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.ast.www.model.util.Constant.LINK_MAIN;

/**
 * Created by Administrator on 2017/7/28.
 */

public class ReGisterActivity extends BaseAvtivity<TestPreseneter> {

    private LinearLayout register_lay;
    private ImageView register_back;
    private Button register_but_register;
    private EditText register_edText_username, register_edText_password, register_edText_phone_number, register_edText_gender;

    @Override
    protected void createmPresenter() {
        mPresenter = new TestPreseneter();
        mPresenter.attach(new IBaseView() {
            @Override
            public void onData(Object o) {
              String s= (String)o;
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    String code = jsonObject.getString("code");
                    if(code.equals("200")){
                        RegisteredBean registeredBean = Constant.GsonToBean(s, RegisteredBean.class);
                        IsUtils.Tos(ReGisterActivity.this,"注册成功");
                        finish();
                    }else {
                       IsUtils.Tos(ReGisterActivity.this,"手机号已注册");
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
        register_lay = (LinearLayout) findViewById(R.id.register_lay);
        register_back = (ImageView) findViewById(R.id.register_back);
        register_but_register = (Button) findViewById(R.id.register_but_register);
        register_edText_username = (EditText) findViewById(R.id.register_edText_username);
        register_edText_password = (EditText) findViewById(R.id.register_edText_password);
        register_edText_phone_number = (EditText) findViewById(R.id.register_edText_phone_number);
        register_edText_gender = (EditText) findViewById(R.id.register_edText_gender);
    }

    @Override
    protected void initData() {
        register_lay.getBackground().setAlpha(100);
    }

    @Override
    protected void initListener() {
        //回退
        register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_from_right);
            }
        });
        //注册
        register_but_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = register_edText_username.getText().toString();
                String spass = register_edText_password.getText().toString();
                String sphone = register_edText_phone_number.getText().toString();
                String sgender = register_edText_gender.getText().toString();
                if (IsUtils.isNull(sname) && IsUtils.isNull(spass) && IsUtils.isNull(sphone) && IsUtils.isNull(sgender)) {
                    if (IsUtils.validatePass(spass) ) {
                        if (IsUtils.validatePhoneNumber(sphone)) {
//                         if(IsUtils.IsSex(sgender)){
                             Map<String, String> maps = new HashMap<String, String>();
                             maps.put("userName", sname);
                             maps.put("userPassword",spass);
                             maps.put("userPhone",sphone);
                             maps.put("userSex",sgender);
                             mPresenter.post("user/addUser",maps, RegisteredBean.class);
//                         }else{
//                             IsUtils.Tos(ReGisterActivity.this, "性别格式不正确");
//                         }
                        } else {
                            IsUtils.Tos(ReGisterActivity.this, "手机格式不正确");
                        }
                    } else {
                        IsUtils.Tos(ReGisterActivity.this, "密码格式不正确");
                    }
                } else {
                    IsUtils.Tos(ReGisterActivity.this, "请补充完整信息!");
                }
            }
        });
    }


    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }
}
