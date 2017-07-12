package com.ast.www.presenter;

import com.ast.www.view.iview.IBaseView;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/12 19:33
 * Title:
 * Text:
 */

public class BasePresenter<T extends IBaseView>{
    private T t;

    //关联activity
    public void attach(T t){
        this.t=t;
    }
    public IBaseView getiBaseView() {
        return t;
    }

    //取消关联activity
    public void detach(){
        if(this.t!=null){
            t=null;
        }
    }

}
