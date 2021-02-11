package com.example.customview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class CustomButtonView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var isCenter = false
    var centerOfX = 0F
    var centerOfY = 0F
    var radiusOfCircleView = 0F

    init {
        var attributeArray: TypedArray? = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.customButtonView, 0, 0
        )
        paint.color = attributeArray?.getColor(
                R.styleable.customButtonView_circleColor,
                ContextCompat.getColor(context, android.R.color.background_dark)
        ) ?: android.R.color.black
        isCenter = attributeArray?.getBoolean(R.styleable.customButtonView_onCenter, false) ?: false;
        //radiusOfCircleView = attributeArray?.getDimension(R.styleable.customButtonView_circle_radius, 150F) ?: 150F
        radiusOfCircleView = resources.getDimension(R.dimen.max_circle_radius)
        paint.strokeWidth = 1f
        paint.color = resources.getColor(R.color.color_1)
        paint.style = Paint.Style.FILL

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var width: Int
        var height: Int

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = resources.getDimension(R.dimen.base_button_width).toInt()
            width = Math.max(width, getSuggestedMinimumWidth());
            if (widthMode == MeasureSpec.AT_MOST)
                width = Math.min(widthSize, width);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = resources.getDimension(R.dimen.base_button_height).toInt()
            height = Math.max(height, getSuggestedMinimumHeight());
            if (heightMode == MeasureSpec.AT_MOST)
                height = Math.min(height, heightSize);
        }

        setMeasuredDimension(width, height);
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.e("TAG", "onLayout method was started")
    }


    override fun onDraw(canvas: Canvas) {
        isCenter?.let {
            centerOfX = (width / 2).toFloat()
            centerOfY = (height / 2).toFloat()
        }
        canvas?.drawCircle(centerOfX, centerOfY, radiusOfCircleView, paint)

        super.onDraw(canvas)
    }


    fun swapColor(){
        when(paint.color){
            resources.getColor(R.color.color_1) -> paint.setColor(resources.getColor(R.color.color_2))
            resources.getColor(R.color.color_2) -> paint.setColor(resources.getColor(R.color.color_3))
            resources.getColor(R.color.color_3) -> paint.setColor(resources.getColor(R.color.color_4))
            resources.getColor(R.color.color_4) -> paint.setColor(resources.getColor(R.color.color_5))
            resources.getColor(R.color.color_5) -> paint.setColor(resources.getColor(R.color.color_6))
            resources.getColor(R.color.color_6) -> paint.setColor(resources.getColor(R.color.color_7))
            resources.getColor(R.color.color_7) -> paint.setColor(resources.getColor(R.color.color_1))
        }
        if (radiusOfCircleView > resources.getDimension(R.dimen.min_circle_radius))
            radiusOfCircleView = (radiusOfCircleView - resources.getDimension(R.dimen.radius_reduction)).toFloat()
        else radiusOfCircleView = resources.getDimension(R.dimen.max_circle_radius)

        postInvalidate()
    }


}