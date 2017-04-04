package com.example.dialogdemo;

import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private Dialog mDialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.GREEN);
    }

    private void getDialog(){
        //设置全屏将会覆盖状态栏
//         mDialog = new Dialog(MainActivity.this,R.style.Dialog_Fullscreen);
        mDialog = new Dialog(MainActivity.this);
        Window mWindow = mDialog.getWindow();
        WindowManager.LayoutParams mParams = mWindow.getAttributes();
        mParams.alpha = 1f;//主题中设置的参数优先级大于使用mParams设置的参数
        mParams.gravity = Gravity.CENTER_HORIZONTAL;
        mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mParams.height = getResources().getDisplayMetrics().heightPixels/2;
        mParams.y = -200;
        mParams.x = 0;
        mWindow.setAttributes(mParams);
        mWindow.getDecorView().setBackgroundColor(Color.MAGENTA);
        mWindow.getDecorView().setPadding(0,0,0,0);
        mWindow.getDecorView().setMinimumWidth(getResources().getDisplayMetrics().widthPixels);
        mDialog.setContentView(R.layout.dialog_layout);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setTitle("this is title");
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Window mWindow = getWindow();
                WindowManager.LayoutParams mParams = mWindow.getAttributes();
                mParams.alpha = 1.0f;
                mWindow.setAttributes(mParams);
            }
        });
        mDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getDialog();
    }

    public void showCustomDialog(View view){
        getDialog();
    }
}
