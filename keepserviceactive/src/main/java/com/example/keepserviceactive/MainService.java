package com.example.keepserviceactive;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class MainService extends Service {

    private ServiceHelp mHelper = new ServiceHelp();
    public class ServiceHelp extends Binder{
        public MainService getMainService(){
            return MainService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mHelper;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }
}
