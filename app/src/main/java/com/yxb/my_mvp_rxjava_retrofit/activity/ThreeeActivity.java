package com.yxb.my_mvp_rxjava_retrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yxb.my_mvp_rxjava_retrofit.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ThreeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threee);

        Request.Builder builder = new Request.Builder().url("https://baidu.com").method("GET", null);
        Request request = builder.build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
            }
        });


        OkHttpClient build = new OkHttpClient.Builder().build();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(new Runnable() {
            @Override
            public void run() {

            }
        }, 100, TimeUnit.MILLISECONDS);
    }
}
