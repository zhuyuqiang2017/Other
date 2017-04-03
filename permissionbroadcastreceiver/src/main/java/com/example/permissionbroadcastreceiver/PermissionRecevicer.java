package com.example.permissionbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class PermissionRecevicer extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("zyq","a Broadcast from another app");
        Toast.makeText(context,"Recevice a broadcast",Toast.LENGTH_LONG).show();
    }
}
