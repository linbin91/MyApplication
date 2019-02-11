package com.lin.request.core;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


public interface BaseApiService {

    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> params);

    @GET
    Observable<ResponseBody> get(@Url String url);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url, @FieldMap Map<String, String> params);

    @POST
    Observable<ResponseBody> post(@Url String url);

    @POST
    Observable<ResponseBody> postBody(@Url String url, @Body RequestBody requestBody);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postWithHeader(@Url String url, @HeaderMap Map<String, String> headerMap, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @PUT
    Observable<ResponseBody> put(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @PATCH
    Observable<ResponseBody> patch(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @DELETE
    Observable<ResponseBody> delete(@Url String url, @FieldMap Map<String, String> params);

    @Multipart
    @POST
    Observable<ResponseBody> upload(@Url String url, @QueryMap Map<String, String> params, @Part List<MultipartBody.Part> parts);

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
