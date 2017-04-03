package com.example.velocitytrackerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private VelocityTracker mTracker =VelocityTracker.obtain();
    private CustomView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (CustomView) findViewById(R.id.cv);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mTracker.computeCurrentVelocity(1000);
                mTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        Log.i("zyq","X="+mTracker.getXVelocity());
        Log.i("zyq","Y="+mTracker.getYVelocity());
        return super.onTouchEvent(event);
    }

}
