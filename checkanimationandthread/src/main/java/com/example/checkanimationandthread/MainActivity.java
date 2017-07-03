package com.example.checkanimationandthread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private ScaleAnimation mAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mImageView = (ImageView)findViewById(R.id.image_view);
//        mAnimation = new ScaleAnimation(1.0f,2.0f,1.0f,2.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        mAnimation.setDuration(100*1000);
//        mAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                android.util.Log.e("zyq","onAnimationStart");
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                android.util.Log.e("zyq","onAnimationEnd");
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
    }

    public void startAnimation(View view){
        if (mImageView != null){
//            mImageView.startAnimation(mAnimation);
            android.util.Log.e("zyq","after start Animation");
        }
    }
}
