package com.lemayn.review.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


/**
 * author: ly
 * date  : 2019/6/22 11:37
 * desc  : [安卓自定义View进阶-分类与流程](https://www.gcssloop.com/customview/CustomViewProcess)
 */
class SloopView_3 : View {

    private var flag = 0
    private val paint = Paint()
    private var mWidth = 0
    private var mHeight = 0

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
        mWidth = measuredWidth
        mHeight = measuredHeight
        super.onLayout(changed, left, top, right, bottom)
    }

    // 实际执行绘制的函数
    @SuppressWarnings("unused")
    override fun onDraw(canvas: Canvas) {

        when (flag % 8) {
            0 -> drawScale(canvas, -2f, -2f) // 先根据缩放中心放大n倍，再根据中心轴进行翻转
            1 -> drawScale(canvas, -1f, -1f) // 根据缩放中心轴进行翻转
            2 -> drawScale(canvas, -.5f, -.5f) // 先根据缩放中心缩小到n，再根据中心轴进行翻转
            3 -> drawScale(canvas, 0f, 0f) // 不会显示，若sx为0，则宽度为0，不会显示，sy同理
            4 -> drawScale(canvas, .5f, .5f) // 根据缩放中心缩小到n
            5 -> drawScale(canvas, 1f, 1f) // 没有变化
            6 -> drawScale(canvas, 2f, 2f) // 根据缩放中心放大n倍
            7 -> drawSth(canvas)
        }

        postDelayed({
            flag++
            postInvalidateOnAnimation()
        }, 2000)
    }

    private fun drawScale(canvas: Canvas, sx: Float, sy: Float) {
        // 位移是基于当前位置移动，且多次位移是叠加的
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        canvas.translate(500f, 800f)
        canvas.drawCircle(0f, 0f, 100f, paint)

        // scale 只对之后绘制的内容起作用，且 scale 是可以叠加的
        canvas.scale(sx, sy)

        paint.color = Color.GREEN
        canvas.translate(200f, 200f)
        canvas.drawCircle(0f, 0f, 100f, paint)
    }

    private fun drawSth(canvas: Canvas) {
        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2f, mHeight / 2f)

        val rect = RectF(-400f, -400f, 400f, 400f)   // 矩形区域

        for (i in 0..20) {
            canvas.scale(0.9f, 0.9f)
            canvas.drawRect(rect, paint)
        }
    }

}
