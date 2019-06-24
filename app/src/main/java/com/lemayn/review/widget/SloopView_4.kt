package com.lemayn.review.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.PictureDrawable
import android.util.AttributeSet
import android.view.View


/**
 * author: ly
 * date  : 2019/6/22 11:37
 * desc  :
 * [安卓自定义View进阶-Canvas之图片文字](https://www.gcssloop.com/customview/Canvas_PictureText)
 **/
class SloopView_4 : View {

    private var mPicture = Picture()

    private var flag = 0
    private var delay = 2000L
    private val paint = Paint()
    private var mWidth = 0
    private var mHeight = 0

    // 一般在直接 new 一个 View 的时候调用
    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        // 关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        paint.apply {
            style = Paint.Style.FILL
            color = Color.BLACK
            strokeWidth = 10F
            isAntiAlias = true
        }

        recording()
    }

    // 一般在 layout 文件中使用的时候会调用，关于它的所有属性(包括自定义属性)都会包含在 attrs 中传递进来
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 宽度的测量模式
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        // 宽度的确切值
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        // 高度的测量模式
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        // 高度的确切值
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

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
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        drawPicture(canvas)
        when (flag % 2) {
            0 -> drawPicture(canvas)
        }

        postDelayed({
            flag++
            postInvalidateOnAnimation()
        }, delay)
    }

    private fun recording() {
        // 开始录制
        val canvas = mPicture.beginRecording(500, 500)

        // canvas.drawColor(Color.argb(.3f, 0f, 0f, 0f))

        canvas.translate(500f, 500f)
        canvas.drawCircle(0f, 0f, 200f, paint)

        // 结束录制
        mPicture.endRecording()

    }

    private fun drawPicture(canvas: Canvas) {
        // notice 此时设置画笔颜色就不起作用了
        paint.color = Color.RED
        mPicture.draw(canvas)

        // 这里的 rect 绘制出的内容，是对 beginRecording 宽高的等比例压缩
        canvas.drawPicture(mPicture, Rect(0, 0, 100, 100))

        // 包装成为Drawable
        val drawable = PictureDrawable(mPicture)
        // 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
        // 向下偏移 300px，因为会被遮挡
        drawable.setBounds(0, 500, mPicture.width, mPicture.height + 500)
        // 绘制
        drawable.draw(canvas)
    }

    private fun drawBitmap(canvas: Canvas) {
        // 图片和文字经常用到，这里就不写了
    }

}
