package com.github.abdularis.piv.transformer

import android.graphics.Canvas
import com.github.abdularis.piv.ScrollTransformImageView


class HorizontalScaleTransformer(minScale : Float) : ViewTransformer {

    private val viewLocation : IntArray = IntArray(2)
    var minScale : Float = minScale
        set (value) {
            field = Math.min(minScale, 1f)
        }

    override fun onAttached(view: ScrollTransformImageView) {}

    override fun onDetached(view: ScrollTransformImageView) {}

    override fun apply(view: ScrollTransformImageView, canvas: Canvas?) {

        val maxDScale = 1f - minScale
        if (maxDScale > 0f) {
            val viewWidth = view.width - view.paddingLeft - view.paddingRight
            val viewHeight = view.height - view.paddingTop - view.paddingBottom

            val deviceWidth = view.resources.displayMetrics.widthPixels

            view.getLocationInWindow(viewLocation)
            val x = when {
                viewLocation[0] < -viewWidth -> return
                viewLocation[0] > deviceWidth -> return
                else -> viewLocation[0]
            }

            val center = (deviceWidth - viewWidth) / 2f
            val scaleFactor = maxDScale / ((deviceWidth + viewWidth) / 2f)
            val scale = 1f - (Math.abs(x - center) * scaleFactor)

            canvas?.scale(scale, scale, viewWidth / 2f, viewHeight / 2f)
        }
    }

}