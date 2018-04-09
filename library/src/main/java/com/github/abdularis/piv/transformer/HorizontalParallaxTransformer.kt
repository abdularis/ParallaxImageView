package com.github.abdularis.piv.transformer

import android.graphics.Canvas
import android.widget.ImageView
import com.github.abdularis.piv.ScrollTransformImageView

/**
 * Created by abdularis 08/04/2018
 */
class HorizontalParallaxTransformer : ViewTransformer() {

    override fun onAttached(view: ScrollTransformImageView) {
        view.scaleType = ImageView.ScaleType.CENTER_CROP
    }

    override fun apply(view: ScrollTransformImageView, canvas: Canvas, viewX : Int, viewY : Int) {
        if (view.scaleType == ImageView.ScaleType.CENTER_CROP) {
            val imageWidth = view.drawable.intrinsicWidth
            val imageHeight = view.drawable.intrinsicHeight

            val viewWidth = view.width - view.paddingLeft - view.paddingRight
            val viewHeight = view.height - view.paddingTop - view.paddingBottom

            val deviceWidth = view.resources.displayMetrics.widthPixels

            if (viewX < -viewWidth || viewX > deviceWidth) return

            // if this is true then the scaling must be based on the height of the
            // image view and the bitmap image itself
            if (imageWidth * viewHeight > viewWidth * imageHeight) {
                val scale = viewHeight.toFloat() / imageHeight.toFloat()
                val invisibleBitmapWidth = imageWidth * scale - viewWidth

                val x = centeredX(viewX, viewWidth, deviceWidth)
                val translationScale = invisibleBitmapWidth / (deviceWidth + viewWidth)
                canvas.translate(-x * translationScale, 0f)
            }
        }
    }

}