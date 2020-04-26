package com.yxb.okhttpdemo;

import java.io.IOException;

import okhttp3.Request;

/**
 * @author :   yuxibing
 * @date :   2020/4/11
 * Describe :
 */
public interface ResultCallback {
    void onError(Request request, Exception error);

    void onSuccess(String body) throws IOException;
}
