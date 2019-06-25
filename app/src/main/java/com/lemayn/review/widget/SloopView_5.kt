package com.lemayn.review.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View

/**
 * author: ly
 * date  : 2019/6/22 11:37
 * desc  :
 * [安卓自定义View进阶-Path之基本操作](https://www.gcssloop.com/customview/Path_Basic/)
 **/
class SloopView_5 : View {

    private var flag = 5
    private var delay = 2000L
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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDraw(canvas: Canvas) {
        paint.strokeWidth = 3f
        paint.style = Paint.Style.STROKE
        paint.color = RED

        canvas.translate(mWidth / 2f, mHeight / 2f)
        if (flag % 6 < 5) {
            canvas.drawLine(-mWidth / 2f, 0f, mWidth / 2f, 0f, paint)
            canvas.drawLine(0f, -mHeight / 2f, 0f, mHeight / 2f, paint)
        }

        paint.color = Color.BLACK
        paint.strokeWidth = 10f
        path.reset()

        when (flag % 6) {
            0 -> drawLines(canvas)
            1 -> drawShapes(canvas, Path.Direction.CW, 0f, 0f)
            2 -> drawShapes(canvas, Path.Direction.CCW, 0f, 0f)
            3 -> drawShapes(canvas, Path.Direction.CW, 0f, -200f)
            4 -> drawArc(canvas)
            5 -> drawCobweb(canvas)
        }

        postDelayed({
            flag++
            postInvalidateOnAnimation()
        }, delay)
    }

    private fun drawLines(canvas: Canvas) {
        delay = 500

        path.lineTo(200f, 200f)
        // 将最后一个点的位置强制修改为 200,100
        path.setLastPoint(200f, 100f)
        path.lineTo(200f, 0f)
        // 连接第一个点和最后一个点，形成一个封闭的图形
        // 如果连接后不能形成封闭的图形，则什么都不做
        path.close()
        canvas.drawPath(path, paint)
    }

    private fun drawShapes(canvas: Canvas, dir: Path.Direction, fx: Float, fy: Float) {
        // CW：顺时针  CWW：逆时针
        path.addRect(-200f, -200f, 200f, 200f, dir)
        // 重置最后一个点的坐标，会影响 CW、CCW 绘制出的图形
        path.setLastPoint(-300f, 300f)

        path.addPath(path, fx, fy)
        canvas.drawPath(path, paint)
    }

    private fun drawArc(canvas: Canvas) {
//        path.addArc(0f, 0f, 200f, 200f, 270f, -234f)
        path.lineTo(120f, 80f)
        path.arcTo(0f, 0f, 200f, 200f, 270f, -234f, false)
        canvas.drawPath(path, paint)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun drawCobweb(canvas: Canvas) {

        delay = 2000

        paint.strokeWidth = 3f
        paint.color = Color.argb(.3f, 0f, 0f, 0f)

        canvas.save()

        for (i in 1..6) {
            path.moveTo(-30f * i, 52f * i)
            path.lineTo(30f * i, 52f * i)
        }
        path.moveTo(-60f * 6, 0f)
        path.lineTo(0f, 0f)

        for (i in 0..5) {
            canvas.drawPath(path, paint)
            canvas.rotate(60f)
        }

        canvas.restore()

        paint.color = Color.argb(.5f, 0f, 0f, 255f)
        paint.style = Paint.Style.FILL

        path.reset()

        path.moveTo(-60f * 3, 0f * 3)
        path.lineTo(-30f * 1, 52f * 1)
        path.lineTo(0f * 6, 52f * 6)
        path.lineTo(30f * 2, 52f * 2)
        path.lineTo(60f * 0, 0f)
        path.lineTo(30f * 4, -52f * 4)
        path.lineTo(-30f * 5, -52f * 5)
        path.close()

        canvas.drawPath(path, paint)
    }

}
