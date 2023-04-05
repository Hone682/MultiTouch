package com.example.multitouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final int SIZE = 60;
    final int MAX_POINTS = 5;
    float[] x = new float[MAX_POINTS];
    float[] y = new float[MAX_POINTS];
    boolean[] touching = new boolean[MAX_POINTS];
    Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiTouchView myView = new MultiTouchView(this);
        setContentView(myView);

    }
    class MultiTouchView extends View {

        public MultiTouchView(Context context) {
            super(context);
            mPaint = new Paint();
            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for(int i = 0; i < MAX_POINTS; i++) {
                if(touching[i] == true) canvas.drawCircle(x[i], y[i], SIZE, mPaint);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            return true;
        }
    }

}