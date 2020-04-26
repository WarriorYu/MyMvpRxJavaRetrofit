package com.yxb.my_mvp_rxjava_retrofit.retrofit;

import android.util.Log;

import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by yuxibing on 2018/10/10.
 * 描述：
 */

public class BaseApiImpl implements BaseApi {
    private volatile static Retrofit retrofit = null;
    protected Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
    protected OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

    public BaseApiImpl(String baseUrl) {
        retrofitBuilder.addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpBuilder.addInterceptor(getLoggerInterceptor()).build())
                .baseUrl(baseUrl);
    }

    /**
     * 构建retroft
     *
     * @return Retrofit对象
     */
    @Override
    public Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (BaseApiImpl.this) {
                if (retrofit == null) {
                    retrofit = retrofitBuilder.build();
                }
            }
        }
        return retrofit;
    }

    @Override
    public OkHttpClient.Builder setInterceptor(Interceptor interceptor) {
        return httpBuilder.addInterceptor(interceptor);
    }

    @Override
    public Retrofit.Builder setConverterFactory(Converter.Factory factory) {
        return retrofitBuilder.addConverterFactory(factory);
    }

    /**
     * 日志拦截器
     * 将你访问的接口信息
     *
     * @return 拦截器
     */

    public HttpLoggingInterceptor getLoggerInterceptor() {
        //新建log拦截器
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("Tag-----", message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.HEADERS); //日志显示级别
    }
}
