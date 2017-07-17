package com.ast.www.view.iview;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/12 19:34
 * Title:
 * Text:
 */


public interface IBaseView<T> {
    void onData(T t);
    void onError(Throwable throwable);
}
