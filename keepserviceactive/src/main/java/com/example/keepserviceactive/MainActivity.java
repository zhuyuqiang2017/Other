package com.example.keepserviceactive;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private boolean mIsBind = false;
    private boolean mIsConnected = false;
    private MainService mMainService;
    private final int NOTIFICATION_ID = 98;
    private boolean mIsForegroundService = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMainService = ((MainService.ServiceHelp)service).getMainService();
            if (mMainService != null){
                MainActivity.this.mIsConnected = true;
            }else{
                new Throwable("服务绑定失败");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            MainActivity.this.mIsConnected = false;
            Log.i("zyq","ServiceConnection:onServiceDisconnected");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(null == startService(new Intent(MainActivity.this,MainService.class))){
            new Throwable("无法启动服务");
        }
        mIsBind = bindService(new Intent(MainActivity.this,MainService.class),mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mIsConnected && mIsForegroundService){
            mMainService.stopForeground(true);
            mIsForegroundService = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mIsBind && mMainService != null && !mIsForegroundService){
            mMainService.startForeground(NOTIFICATION_ID,getNotification());
            mIsForegroundService = true;
        }
    }

    private Notification getNotification(){
        Notification.Builder mBuilder = new Notification.Builder(MainActivity.this);
        mBuilder.setShowWhen(false);
        mBuilder.setAutoCancel(false);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setLargeIcon(((BitmapDrawable)getDrawable(R.drawable.notification_drawable)).getBitmap());
        mBuilder.setContentText("this is content");
        mBuilder.setContentTitle("this is title");
        return mBuilder.build();
    }

}
