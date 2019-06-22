package com.lemayn.review.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: ly
 * date  : 2019/6/22 11:37
 * desc  :
 * <a href="https://www.gcssloop.com/customview/CustomViewProcess">安卓自定义View进阶-分类与流程<a>
 */
public class SloopView_1 extends View {

    // 一般在直接 new 一个 View 的时候调用
    public SloopView_1(Context context) {
        super(context);
    }

    // 一般在 layout 文件中使用的时候会调用，关于它的所有属性(包括自定义属性)都会包含在 attrs 中传递进来
    public SloopView_1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SloopView_1(Context context, @Nullable AttributeSet attrs,
                       int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SloopView_1(Context context, @Nullable AttributeSet attrs,
                       int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 宽度的测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // 宽度的确切值
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        // 高度的测量模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // 高度的确切值
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 对宽高进行修改后，不能调用 super 方法
        setMeasuredDimension(widthSize, heightSize);
    }

    // 在视图大小发生改变的时候调用
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // w、h 为 view 的最终宽高
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 自定义 ViewGroup 中会用到
     * 循环取出子 View，计算子 View 的坐标。调用 child.layout
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    // 实际执行绘制的函数
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
