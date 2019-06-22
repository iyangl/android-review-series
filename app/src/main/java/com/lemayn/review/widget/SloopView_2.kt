package com.lemayn.review.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 * author: ly
 * date  : 2019/6/22 11:37
 * desc  : x
 * [安卓自定义View进阶-Canvas之绘制图形<a>
</a>](https://www.gcssloop.com/customview/Canvas_BasicGraphics)
 */
class SloopView_2 : View {

    private var flag = 0
    private val paint = Paint()

    // 一般在直接 new 一个 View 的时候调用
    constructor(context: Context) : super(context) {
        init()
    }

    // 一般在 layout 文件中使用的时候会调用，关于它的所有属性(包括自定义属性)都会包含在 attrs 中传递进来
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private fun init() {
        paint.apply {
            style = Paint.Style.FILL
            color = Color.BLACK
            strokeWidth = 10F
            isAntiAlias = true
        }
    }

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
    @SuppressWarnings("unused")
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // w、h 为 view 的最终宽高
        super.onSizeChanged(w, h, oldw, oldh)
    }

    /**
     * 自定义 ViewGroup 中会用到
     * 循环取出子 View，计算子 View 的坐标。调用 child.layout
     */
    @SuppressWarnings("unused")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    // 实际执行绘制的函数
    @SuppressWarnings("unused")
    override fun onDraw(canvas: Canvas) {
        when (flag % 5) {
            0 -> drawPoints(canvas)
            1 -> drawLines(canvas)
            2 -> drawRect(canvas)
            3 -> drawArc(canvas)
            4 -> drawPie(canvas)
        }

        postDelayed({
            flag++
            postInvalidate()
        }, 2000)
    }

    private fun drawPoints(canvas: Canvas) {
        canvas.drawPoint(100F, 100F, paint)
        // 绘制一组点，坐标位置由float数组指定
        canvas.drawPoints(floatArrayOf(
                500f, 500f, 500f, 600f, 500f, 700f), paint)
    }

    private fun drawLines(canvas: Canvas) {
        canvas.drawLine(300F, 300F, 500F, 600F, paint)    // 在坐标(300,300)(500,600)之间绘制一条直线
        canvas.drawLines(floatArrayOf(// 绘制一组线 每四数字(两个点的坐标)确定一条线
                100f, 200f, 200f, 200f, 100f, 300f, 200f, 300f), paint)
    }

    private fun drawRect(canvas: Canvas) {
        // 第一种
        canvas.drawRect(100F, 100F, 800F, 400F, paint)

        // 第二种
        val rect = Rect(100, 100, 800, 400)
        canvas.drawRect(rect, paint)

        // 第三种
        val rectF = RectF(100f, 100f, 800f, 400f)
        canvas.drawRect(rectF, paint)
    }

    private fun drawArc(canvas: Canvas) {
        val rectF = RectF(100f, 100f, 800f, 400f)
        // 绘制背景矩形
        paint.color = Color.GRAY
        canvas.drawRect(rectF, paint)

        // 绘制圆弧
        paint.color = Color.BLUE
        canvas.drawArc(rectF, 0F, 90F, false, paint)

        //-------------------------------------

        val rectF2 = RectF(100f, 600f, 800f, 900f)
        // 绘制背景矩形
        paint.color = Color.GRAY
        canvas.drawRect(rectF2, paint)

        // 绘制圆弧
        paint.color = Color.BLUE
        canvas.drawArc(rectF2, 0F, 90F, true, paint)
    }

    private fun drawPie(canvas: Canvas) {
        paint.apply {
            style = Paint.Style.STROKE
            strokeWidth = 3f
        }

        canvas.drawCircle(500f, 500f, 300f, paint)

        paint.apply {
            color = Color.GRAY
            style = Paint.Style.FILL
        }
        val rectF = RectF(200f, 200f, 800f, 800f)
        canvas.drawArc(rectF, 270f, 220f, true, paint)

        paint.color = Color.RED
        canvas.drawArc(rectF, 270f, -75f, true, paint)

        paint.color = Color.MAGENTA
        canvas.drawArc(rectF, 210f, -45f, true, paint)
    }

}
