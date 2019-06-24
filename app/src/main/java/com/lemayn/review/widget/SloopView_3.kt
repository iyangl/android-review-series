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
 * desc  : [安卓自定义View进阶-Canvas之画布操作](https://www.gcssloop.com/customview/Canvas_Convert)
 */
class SloopView_3 : View {

    private var flag = 0
    private var delay = 2000L
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
        // 使用 save、restore 包裹绘制内容，防止污染状态栈
        canvas.save()

        when (flag % 12) {
            0 -> drawScale(canvas, -2f, -2f) // 先根据缩放中心放大n倍，再根据中心轴进行翻转
            1 -> drawScale(canvas, -1f, -1f) // 根据缩放中心轴进行翻转
            2 -> drawScale(canvas, -.5f, -.5f) // 先根据缩放中心缩小到n，再根据中心轴进行翻转
            3 -> drawScale(canvas, 0f, 0f) // 不会显示，若sx为0，则宽度为0，不会显示，sy同理
            4 -> drawScale(canvas, .5f, .5f) // 根据缩放中心缩小到n
            5 -> drawScale(canvas, 1f, 1f) // 没有变化
            6 -> drawScale(canvas, 2f, 2f) // 根据缩放中心放大n倍
            7 -> drawSthScale(canvas) // 叠加缩放
            8 -> drawRotate(canvas, 0f, 0f) // 旋转中心为原点
            9 -> drawRotate(canvas, 200f, -50f) // 旋转中心为 200,50
            10 -> drawSthRotate(canvas) // 叠加旋转
            11 -> drawSkew(canvas) // 错切
        }

        canvas.restore()

        postDelayed({
            flag++
            postInvalidateOnAnimation()
        }, delay)
    }

    private fun drawScale(canvas: Canvas, sx: Float, sy: Float) {
        delay = 500

        // 位移是基于当前位置移动，且多次位移是叠加的
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2f, mHeight / 2f)
        canvas.drawCircle(0f, 0f, 100f, paint)

        // scale 只对之后绘制的内容起作用，且 scale 是可以叠加的
        canvas.scale(sx, sy)

        paint.color = Color.GREEN
        canvas.translate(200f, 200f)
        canvas.drawCircle(0f, 0f, 100f, paint)
    }

    private fun drawSthScale(canvas: Canvas) {
        delay = 2000

        paint.style = Paint.Style.STROKE

        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2f, mHeight / 2f)

        val rect = RectF(-400f, -400f, 400f, 400f)   // 矩形区域

        for (i in 0..20) {
            canvas.scale(0.9f, 0.9f)
            canvas.drawRect(rect, paint)
        }
    }

    private fun drawRotate(canvas: Canvas, px: Float, py: Float) {
        delay = 500

        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2f, mHeight / 2f)

        val rect = RectF(0f, -400f, 400f, 0f) // 矩形区域

        paint.color = Color.BLACK // 绘制黑色矩形
        canvas.drawRect(rect, paint)

        canvas.rotate(180f, px, py) // 旋转180度 <-- 默认旋转中心为 px py

        paint.color = Color.BLUE // 绘制蓝色矩形
        canvas.drawRect(rect, paint)
    }

    private fun drawSthRotate(canvas: Canvas) {
        delay = 2000

        paint.style = Paint.Style.STROKE

        canvas.translate(mWidth / 2f, mHeight / 2f)

        canvas.drawCircle(0f, 0f, 500f, paint)
        canvas.drawCircle(0f, 0f, 480f, paint)

        for (i in 1..36) {
            canvas.rotate(10f)
            canvas.drawLine(0f, 480f, 0f, 500f, paint)
        }
    }

    /**
     * float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
     * float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值.
     * 错切
     * X = x + sx * y
     * Y = sy * x + y
     */
    private fun drawSkew(canvas: Canvas) {
        delay = 500

        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2f, mHeight / 2f)

        val rect = RectF(0f, 0f, 200f, 200f) // 矩形区域

        paint.color = Color.BLACK // 绘制黑色矩形
        canvas.drawRect(rect, paint)

        canvas.skew(1f, 0f) // 水平错切 <- 45度

        paint.color = Color.BLUE // 绘制蓝色矩形
        canvas.drawRect(rect, paint)
    }
}
