package com.ast.www.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
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

public class UserDrawMoudle extends RelativeLayout {

    private TextView mTextView;
    private RelativeLayout user_moudle;
    private ImageView left_image;
    private ImageView right_image;
    private int left_image_src;
    private int right_image_src;
    private String text;

    public UserDrawMoudle(Context context) {
        super(context);
    }

    public UserDrawMoudle(Context context, AttributeSet attrs) {
        super(context, attrs);

        //加载视图的布局
        LayoutInflater.from(context).inflate(R.layout.user_draw_moudle,this,true);

        //加载自定义的属性
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.UserDrawMoudle);
        text=a.getString(R.styleable.UserDrawMoudle_text);
        left_image_src = a.getResourceId(R.styleable.UserDrawMoudle_left_image, R.mipmap.ic_launcher);
        right_image_src = a.getResourceId(R.styleable.UserDrawMoudle_right_image, R.mipmap.ic_launcher);

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
        mTextView= (TextView) findViewById(R.id.text);
        user_moudle= (RelativeLayout) findViewById(R.id.user_moudle);
        left_image= (ImageView) findViewById(R.id.left_image);
        right_image= (ImageView) findViewById(R.id.right_image);

        //将从资源文件中加载的属性设置给子控件
        if (!TextUtils.isEmpty(text))
            setText(text);
        setleftimage(left_image_src);
        setrightimage(right_image_src);

    }

    /**
     * 设置文字
     */
    public void setText(String text) {
        mTextView.setText(text);
    }

    /**
     * 设置右边图片
     */
    public void setrightimage(int src) {
        right_image.setImageResource(src);
    }

    /**
     * 设置左边图片
     */
    public void setleftimage(int src) {
        left_image.setImageResource(src);
    }

    /**
     * 设置按钮点击事件监听器
     * @param listener
     */
    public void setOnHeaderClickListener(OnClickListener listener) {
        user_moudle.setOnClickListener(listener);
    }
}
