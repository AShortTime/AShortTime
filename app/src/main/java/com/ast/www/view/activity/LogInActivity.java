package com.ast.www.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ast.www.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import static com.ast.www.model.util.UtilsConstant.APP_ID;

/**
 * Created by Administrator on 2017/7/27.
 */

public class LogInActivity extends BaseAvtivity {

    private Button login_but_qq;
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private static final String TAG = "MainActivity";
    private TextView login_text_qita;
    private ImageView register_back;
    private Button login_but_wx;

    @Override
    protected void createmPresenter() {

    }

    @Override
    protected void initUI() {
        login_but_qq = (Button) findViewById(R.id.login_but_qq);
        login_text_qita = (TextView) findViewById(R.id.login_text_qita);
        register_back = (ImageView) findViewById(R.id.register_back);
        login_but_wx = (Button) findViewById(R.id.login_but_wx);
    }

    @Override
    protected void initData() {
        //QQ登录获得数据,传入appid
        mTencent = Tencent.createInstance(APP_ID,LogInActivity.this.getApplicationContext());

    }

    @Override
    protected void initListener() {
        //微信登录
        login_but_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //QQ登录
         login_but_qq.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 mIUiListener = new BaseUiListener();
                 //all表示获取所有权限
                 mTencent.login(LogInActivity.this,"all", mIUiListener);
             }
         });
        //其他登录
        login_text_qita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this,OtherLogInActivity.class));
                //启动结束动画
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
            }
        });
        //回退
        register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_from_right);
            }
        });
    }


    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    //qq登录回调方法
    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            Toast.makeText(LogInActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                    }
                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.errorCode);
                    }
                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LogInActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(LogInActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * QQ登录
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
