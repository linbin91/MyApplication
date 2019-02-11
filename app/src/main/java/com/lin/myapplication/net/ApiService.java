package com.lin.myapplication.net;

import com.lin.request.core.BaseResponseEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by OneXzgj on 2018/4/3:09:56.
 * des:网络请求接口
 */

public interface ApiService {


    /**
     * 注册用户的方法
     * http://www.wanandroid.com/user/register
     *
     * @param username   用户名
     * @param password   密码
     * @param repassword 确认密码
     * @return
     */
    @POST("/user/register")
    @FormUrlEncoded
    Observable<BaseResponseEntity> register(@Field("username") String username,
                                            @Field("password") String password,
                                            @Field("repassword") String repassword);



}
