package com.ast.www.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ast.www.presenter.BasePresenter;

import java.util.Stack;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/13 15:26
 * Title:
 * Text:
 */

public abstract class BaseAvtivity<T extends BasePresenter> extends AppCompatActivity{
    /** 用来保存所有已打开的Activity */
    private static Stack<Activity> listActivity = new Stack<Activity>();

    /** 记录上次点击按钮的时间 **/
    private long lastClickTime;
    /** 按钮连续点击最低间隔时间 单位：毫秒 **/
    public final static int CLICK_TIME = 500;
    protected   T mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // 将activity推入栈中
        listActivity.push(this);
        createmPresenter();
        // 初始化ui
        initUI();
        // 初始化数据
        initData();
        // 事件监听
        initListener();


    }

    protected abstract void createmPresenter();

    /** 初始化ui **/
    protected abstract void initUI();

    /** 初始化数据 **/
    protected abstract void initData();

    /** 初始化监听 **/
    protected abstract void initListener();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    /** 保存activity状态 **/
    protected void saveInstanceState(Bundle outState) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onBack(View v) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 从栈中移除当前activity
        if (listActivity.contains(this)) {
            listActivity.remove(this);
        }
    }
    //展示布局
    public abstract int getLayout();

    /********************** activity跳转 **********************************/
    public void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }

    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void openActivityAndCloseThis(Class<?> targetActivityClass) {
        openActivity(targetActivityClass);
        this.finish();
    }

    public void openActivityAndCloseThis(Class<?> targetActivityClass, Bundle bundle) {
        openActivity(targetActivityClass, bundle);
        this.finish();
    }

    /***************************************************************/

    /** 验证上次点击按钮时间间隔，防止重复点击 */
    public boolean verifyClickTime() {
        if (System.currentTimeMillis() - lastClickTime <= CLICK_TIME) {
            return false;
        }
        lastClickTime = System.currentTimeMillis();
        return true;
    }

    /** 收起键盘 */
    public void closeInputMethod() {
        // 收起键盘
        View view = getWindow().peekDecorView();// 用于判断虚拟软键盘是否是显示的
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 获取string
     *
     * @param mRid
     * @return
     */
    public String getStringMethod(int mRid) {
        return this.getResources().getString(mRid);
    }

    /**
     * 获取demin
     *
     * @param mRid
     * @return
     */
    protected int getDemonIntegerMethod(int mRid) {
        return (int) this.getResources().getDimension(mRid);
    }

    /**
     * 关闭所有(前台、后台)Activity,注意：请已BaseActivity为父类
     */
    protected static void finishAll() {
        int len = listActivity.size();
        for (int i = 0; i < len; i++) {
            Activity activity = listActivity.pop();
            activity.finish();
        }
    }



    /***************** 双击退出程序 ************************************************/
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == keyCode) {
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                // 将系统当前的时间赋值给exitTime
                exitTime = System.currentTimeMillis();
            } else {
                finishAll();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    // short吐司
    public void showShort(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    // long吐司
    public void showLong(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }


}
