package com.myxh.coolshopping.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by asus on 2016/7/29.
 */

public class SidebarView extends View {

    //画笔
    private Paint mPaint;
    //
    private boolean isShowBg = false;
    //当前选中位置
    private int selected = -1;
    //展示字母
    private TextView showText;
    //滑动监听
    private OnSlidingListener mOnSlidingListener;
    //数据
    private String[] sideTitles = { "$", "#", "*", "A", "B", "C", "D",
            "E", "F", "G", "H","J", "K", "L", "M", "N","P", "Q",
            "R", "S", "T","W", "X", "Y", "Z" };

    public void setOnSlidingListener(OnSlidingListener onSlidingListener) {
        mOnSlidingListener = onSlidingListener;
    }

    public SidebarView(Context context) {
        super(context);
    }

    public SidebarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public SidebarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(26);
        mPaint.setColor(Color.parseColor("#8c8c8c"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //被按下时是否显示背景色
        if (isShowBg) {
            canvas.drawColor(Color.parseColor("#40000000"));
        }
        //计算每个字符所占的高度
        float charHeight = getHeight() / sideTitles.length;
        int width = getWidth();
        for (int i = 0; i < sideTitles.length; i++) {
            String text = sideTitles[i];
            float x = width/2 - mPaint.measureText(text)/2;
            float y = charHeight * (i+1);
            canvas.drawText(text,x,y,mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int position = (int) (event.getY()/getHeight() * sideTitles.length);
        int oldSelected = selected;
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                isShowBg = true;
                if (oldSelected != position && mOnSlidingListener != null) {
                    if (position > 0 && position < sideTitles.length) {
                        //将滑动到的字母传递到activity中  
                        mOnSlidingListener.onSliding(sideTitles[position]);
                        selected=position;
                        if(showText!=null){
                            showText.setVisibility(View.VISIBLE);
                            showText.setText(sideTitles[position]);
                        }
                    }
                    //重新绘制
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_MOVE:

                isShowBg = true;
                if (oldSelected != position && mOnSlidingListener != null) {
                    if (position >=0 && position < sideTitles.length) {
                        mOnSlidingListener.onSliding(sideTitles[position]);
                        selected = position;
                        if(showText!=null){
                            showText.setVisibility(View.VISIBLE);
                            showText.setText(sideTitles[position]);
                        }
                    }
                    //重新绘制
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                isShowBg = false;
                selected = -1;
                if(showText!=null){
                    showText.setVisibility(View.GONE);
                }
                invalidate();
                break;
        }
        return true;
    }

    public interface OnSlidingListener {
        void onSliding(String selectedStr);
    }

    public void setShowText(TextView showText) {
        this.showText = showText;
    }
}
