package com.example.notificationdemo;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class TestService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i("zyq","event = "+event.describeContents());
        Log.i("zyq","event:package = "+event.getPackageName());
        Log.i("zyq","event:text = "+event.getText());
        Log.i("zyq","event:source = "+event.getSource());
    }

    @Override
    public void onInterrupt() {

    }
}
