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
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/12 19:25
 * Title:
 * Text:
 */

public interface Api {

    @GET()
    Observable<String> getTest(@Url String url,@QueryMap Map<String,String> map);

    @FormUrlEncoded
    @POST()
    Observable<String> postTest(@Url String url, @FieldMap Map<String,String> map);

    @Multipart
    @POST()
    Observable<String> filePost(@Url String url,@Part List<MultipartBody.Part> partList);
}
