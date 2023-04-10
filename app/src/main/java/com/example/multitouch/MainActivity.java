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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiTouchView myView = new MultiTouchView(this);
        setContentView(myView);

    }
    class MultiTouchView extends View {
        final int SIZE = 60;
        final int MAX_POINTS = 5;
        float[] x = new float[MAX_POINTS];
        float[] y = new float[MAX_POINTS];
        boolean[] touching = new boolean[MAX_POINTS];
        Paint mPaint;

        public MultiTouchView(Context context) {
            super(context);
            mPaint = new Paint();
            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(5f);
            mPaint.setTextSize(48f);
        }

        @Override
        protected void onDraw(Canvas canvas) { // 그림을 그려야 할 때 호출됨
            super.onDraw(canvas);
            String str = "포인터 ID : ";
            for(int i = 0; i < MAX_POINTS; i++) {
                if(touching[i] == true) {
                    canvas.drawCircle(x[i], y[i], SIZE, mPaint);
                    str += i + " ";
                }
            }
            canvas.drawText(str, 0, 50, mPaint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int index = event.getActionIndex();
            int id = event.getPointerId(index);
            int action = event.getActionMasked();

            switch(action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    x[id] = event.getX(index);
                    y[id] = event.getY(index);
                    touching[id] = true;
                case MotionEvent.ACTION_MOVE: break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL: touching[id] = false;
            }
            invalidate(); // View를 무효화시키는 메소드이다. (뷰를 다시 그려야 할 때 쓰임... 자동으로 onDraw 함수 호출)

            return true;
        }
    }

}