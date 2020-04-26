package com.yxb.okhttpdemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yxb.my_mvp_rxjava_retrofit.R;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

import okhttp3.Request;

/**
 * @author : yuxibing
 * @date : 2020-04-26
 * Describe : 安卓开发艺术探索Okhttp学习
 */
public class OKttpDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_demo);
        OkHttpEngine.getInstance().getSync("https://baidu.com", new ResultCallback() {
            @Override
            public void onError(Request request, Exception error) {
                error.printStackTrace();
            }

            @Override
            public void onSuccess(String body) throws IOException {
                Log.e("OKttpDemoActivity--->", body);
            }
        });
    }
}
