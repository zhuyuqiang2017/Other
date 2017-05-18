package com.example.appwidgetdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class ConfigureActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("zyq","ConfigureActivity:onCreate");
        setResult(RESULT_OK);
        finish();
    }
}
