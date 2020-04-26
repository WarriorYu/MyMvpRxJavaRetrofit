package com.yxb.okhttpdemo;

import android.os.Handler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author :   yuxibing
 * @date :   2020/4/11
 * Describe :
 */
public class OkHttpEngine {

    private final OkHttpClient mClient;
    private static OkHttpEngine mInstance;
    private final Handler mHandler;

    public static OkHttpEngine getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpEngine.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpEngine();
                }
            }
        }
        return mInstance;
    }

    private OkHttpEngine() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
        mClient = builder.build();
        mHandler = new Handler();
    }


    public void getSync(String url, final ResultCallback callback) {
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).get()/*method("GET", null)*/.build();
        Call mCall = mClient.newCall(request);

        //同步请求流程
        try {
            mCall.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //异步请求流程
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                if (callback != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(call.request(), e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (callback != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callback.onSuccess(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }
        });
    }
}
