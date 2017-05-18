package com.example.notificationdemo;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class TestActivity extends Activity {
    AccessibilityManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        mManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);

        Log.i("zyq","is Enabled = "+mManager.isEnabled());
    }
}
