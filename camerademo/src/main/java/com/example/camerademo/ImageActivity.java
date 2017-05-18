package com.example.camerademo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class ImageActivity extends Activity {
//    private ImageView mImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blur_layout);
    }

    private void createBitmap(Bitmap source){
        int width = source.getWidth();
        int height = source.getHeight();
        int[][] pixels = new int[width][height];
        for(int i = 0;i<width;i++){
            for(int j = 0;j<height;j++){

            }
        }
        Bitmap map = source.copy(Bitmap.Config.ARGB_8888,true);
        Canvas c = new Canvas(map);
    }
}
