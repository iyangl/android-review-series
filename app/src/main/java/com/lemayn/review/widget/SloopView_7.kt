package com.lemayn.review.widget

import android.content.Context
import android.graphics.*
import android.graphics.BitmapFactory.decodeResource
import android.graphics.Color.RED
import android.util.AttributeSet
import android.view.View
import com.lemayn.review.R

/**
 * author: ly
 * date  : 2019/6/22 11:37
 * desc  :
 * [安卓自定义View进阶-Matrix Camera](https://www.gcssloop.com/customview/matrix-3d-camera)
 **/
class SloopView_7 : View {

    private val flag = 0
    private val delay = 2000L
    private val paint = Paint()
    private val path = Path()
    private var mWidth = 0
    private var mHeight = 0
    private var bitmap: Bitmap? = null

    // 一般在直接 new 一个 View 的时候调用
    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        // empty
        paint.strokeWidth = 3f
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = RED

        bitmap = decodeResource(context.resources, R.drawable.ic_launcher)
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
        canvas.scale(1f, -1f)
        val rect = Rect(-200, 200, 200, -200)
        canvas.drawBitmap(bitmap, rect, rect, paint)
    }
}
