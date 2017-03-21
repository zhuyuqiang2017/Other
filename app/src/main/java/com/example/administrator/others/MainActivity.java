package com.example.administrator.others;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("zyq","MainActivity:ThreadId="+Thread.currentThread().getId());
    }

    public void startCustomService(View view){
        Intent intent = new Intent(MainActivity.this,CustomService.class);
        startService(intent);
    }

    public void startCustomIntentService(View view){
        Intent intent = new Intent(MainActivity.this,CustomIntentService.class);
        startService(intent);
    }
}
