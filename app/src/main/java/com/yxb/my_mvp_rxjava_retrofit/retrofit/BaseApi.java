package com.yxb.my_mvp_rxjava_retrofit.retrofit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by yuxibing on 2018/10/10.
 * 描述：
 */

public interface BaseApi {
    Retrofit getRetrofit();

    OkHttpClient.Builder setInterceptor(Interceptor interceptor);

    Retrofit.Builder setConverterFactory(Converter.Factory factory);
}
