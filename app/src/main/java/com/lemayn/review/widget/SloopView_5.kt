package com.lemayn.review.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * author: ly
 * date  : 2019/6/22 11:37
 * desc  :
 * [安卓自定义View进阶-Path之基本操作](https://www.gcssloop.com/customview/Path_Basic/)
 **/
class SloopView_5 : View {

    private val flag = 0
    private val delay = 2000L
    private val paint = Paint()
    private var mWidth = 0
    private var mHeight = 0
    private val path = Path()

    // 一般在直接 new 一个 View 的时候调用
    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        // 关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null)

        paint.strokeWidth = 3f
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = RED
    }

    // 一般在 layout 文件中使用的时候会调用，关于它的所有属性(包括自定义属性)都会包含在 attrs 中传递进来
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 宽度的测量模式
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        // 宽度的确切值
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        // 高度的测量模式
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        // 高度的确切值
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // 对宽高进行修改后，不能调用 super 方法
        setMeasuredDimension(widthSize, heightSize)
    }

    // 在视图大小发生改变的时候调用
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // w、h 为 view 的最终宽高
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    /**
     * 自定义 ViewGroup 中会用到
     * 循环取出子 View，计算子 View 的坐标。调用 child.layout
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        // empty
    }

    // 实际执行绘制的函数
    override fun onDraw(canvas: Canvas) {
        canvas.translate(mWidth / 2f, mHeight / 2f)
        canvas.drawLine(-mWidth / 2f, 0f, mWidth / 2f, 0f, paint)
        canvas.drawLine(0f, -mHeight / 2f, 0f, mHeight / 2f, paint)

        paint.color = Color.BLACK
        paint.strokeWidth = 10f
        path.reset()

        path.lineTo(200f, 200f)
        // 将最后一个点的位置强制修改为 200,100
        path.setLastPoint(200f, 100f)
        path.lineTo(200f, 0f)
        // 连接第一个点和最后一个点，形成一个封闭的图形
        // 如果连接后不能形成封闭的图形，则什么都不做
        path.close()
        canvas.drawPath(path, paint)
    }
}
