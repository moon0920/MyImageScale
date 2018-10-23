package com.mmj.www.myimagescale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class MyImageView extends View {
    private Drawable image;
    private ScaleGestureDetector gestureDetector;
    private float scale = 1.0f;

    public MyImageView(Context context) {
        super(context);
        image = context.getDrawable(R.drawable.dog2);
        setFocusable(true);
        image.setBounds(0,0,image.getIntrinsicHeight(),image.getIntrinsicWidth());
        gestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener(){
            @Override//디텍팅 되었을때 처리하는 부분
            public boolean onScale(ScaleGestureDetector detector) {
                scale *= detector.getScaleFactor();//*=는 배수로 확되됨을 의미
                if(scale < 0.1f) scale = 0.1f;
                if(scale > 10.0f) scale = 10.0f;
                return true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scale, scale);
        image.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);//터치이벤트 호출시 제스쳐디텍트 내용이 실행된다
        invalidate();
        return true;
    }
}
