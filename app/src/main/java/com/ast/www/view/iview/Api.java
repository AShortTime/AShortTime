package com.ast.www.view.iview;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/12 19:25
 * Title:
 * Text:
 */

public interface Api {

    @GET("postTest")
    Observable<String> getTest(@QueryMap Map<String,String> map);

    @FormUrlEncoded
    @POST("postTest")
    Observable<String> postTest(@FieldMap Map<String,String> map);

    @Multipart
    @POST("picture/pictureUpload")
    Observable<String> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);
    @Multipart
    @POST("picture/pictureUpload")
    Observable<String> upload(@Part List<MultipartBody.Part> partList);
}
