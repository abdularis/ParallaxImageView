package com.github.abdularis.piv.transformer

import android.graphics.Canvas
import com.github.abdularis.piv.ScrollTransformImageView


class HorizontalScaleTransformer(minScale : Float) : ViewTransformer() {

    var minScale : Float = minScale
        set (value) {
            field = Math.min(minScale, 1f)
        }

    override fun onAttached(view: ScrollTransformImageView) {}

    override fun onDetached(view: ScrollTransformImageView) {}

    override fun apply(view: ScrollTransformImageView, canvas: Canvas, viewX : Int, viewY : Int) {
        // TODO: Change the algorithm so that it uses screen centered coordinate

        val maxDScale = 1f - minScale
        if (maxDScale > 0f) {
            val viewWidth = view.width - view.paddingLeft - view.paddingRight
            val viewHeight = view.height - view.paddingTop - view.paddingBottom

            val deviceWidth = view.resources.displayMetrics.widthPixels

            val x = when {
                viewX < -viewWidth -> return
                viewX > deviceWidth -> return
                else -> viewX
            }

            val center = (deviceWidth - viewWidth) / 2f
            val scaleFactor = maxDScale / ((deviceWidth + viewWidth) / 2f)
            val scale = 1f - (Math.abs(x - center) * scaleFactor)

            canvas.scale(scale, scale, viewWidth / 2f, viewHeight / 2f)
        }
    }

}