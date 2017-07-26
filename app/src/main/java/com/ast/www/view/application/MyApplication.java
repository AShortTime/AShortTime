package com.ast.www.view.application;

import android.app.Application;

import com.ast.www.model.util.Constant;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/12 21:19
 * Title:
 * Text:
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Constant.init(this);
        Fresco.initialize(this);
    }
}
