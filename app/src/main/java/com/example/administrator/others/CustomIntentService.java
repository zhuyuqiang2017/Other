package com.example.administrator.others;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class CustomIntentService extends IntentService {

    public CustomIntentService(){
        this("CustomIntentService");
    }

    public CustomIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("zyq","CustomIntentService:ThreadId:"+Thread.currentThread().getId());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("zyq","CustomIntentService:onHandleIntent:ThreadId:"+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
