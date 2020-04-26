package com.yxb.my_mvp_rxjava_retrofit;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.logging.Handler;

/**
 * Author   :   yuxibing
 * Create   :   2019-09-14
 * Describe :
 */
public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
