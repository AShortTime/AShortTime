package com.ast.www.constom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ast.www.R;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/22 10:14
 * Title:
 * Text:
 */

public class Userinfoview extends RelativeLayout {

    private TextView time_;
    private TextView user_name;
    private RelativeLayout user_info;
    private ImageView user_head;
    private int userhead;
    private String time;
    private String username;

    public Userinfoview(Context context) {
        super(context);
    }

    public Userinfoview(Context context, AttributeSet attrs) {
        super(context, attrs);

        //加载视图的布局
        LayoutInflater.from(context).inflate(R.layout.user_info,this,true);

        //加载自定义的属性
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.Userinfoview);
        time=a.getString(R.styleable.Userinfoview_time);
        userhead = a.getResourceId(R.styleable.Userinfoview_user_head, R.mipmap.ic_launcher);
        username = a.getString(R.styleable.Userinfoview_user_name);

        //回收资源，这一句必须调用
        a.recycle();
    }

    /**
     * 此方法会在所有的控件都从xml文件中加载完成后调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        //获取子控件
        time_= (TextView) findViewById(R.id.time);
        user_name= (TextView) findViewById(R.id.user_name);
        user_info= (RelativeLayout) findViewById(R.id.user_info);
        user_head= (ImageView) findViewById(R.id.user_head);


        //将从资源文件中加载的属性设置给子控件
        if (!TextUtils.isEmpty(time))
            setTime(time);
        if (!TextUtils.isEmpty(username))
            setUsername(username);
        setUserhead(userhead);

    }

    /**
     * 设置文字
     */
    public void setTime(String text) {
        time_.setText(text);
    }
    public void setUsername(String text) {
        user_name.setText(text);
    }


    /**
     * 设置图片
     */
    public void setUserhead(int src) {
        user_head.setImageResource(src);
    }

    /**
     * 设置按钮点击事件监听器
     * @param listener
     */
    public void setOnHeaderClickListener(OnClickListener listener) {
        user_info.setOnClickListener(listener);
    }
}
