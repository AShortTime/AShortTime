package com.ast.www.model.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.ast.www.R;

/**
 * Created by Administrator on 2017/8/1.
 */

public class Utils {

    public static SharedPreferences getSharedPrefers(Context context){
        SharedPreferences user = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return user;
    }
    public static SharedPreferences.Editor getEdit(Context context){
        SharedPreferences user = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        return edit;
    }








}
