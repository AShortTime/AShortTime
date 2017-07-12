package com.ast.www.view.iview;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/12 19:25
 * Title:
 * Text:
 */

public interface Api {

    @GET("index.php")
    Observable<String> getTest(@QueryMap Map<String,String> map);

    @FormUrlEncoded
    @POST("index.php")
    Observable<String> postTest(@FieldMap Map<String,String> map);


}
