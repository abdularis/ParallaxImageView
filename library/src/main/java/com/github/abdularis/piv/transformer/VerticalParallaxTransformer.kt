package com.github.abdularis.piv.transformer

import android.graphics.Canvas
import android.widget.ImageView
import com.github.abdularis.piv.ScrollTransformImageView


/**
 * This viewTransformer will create parallax effect on image view
 * vertically when it's being scroll and if the image height is
 * higher than the view height
 *
 * Created by abdularis 07/04/2018
 */
class VerticalParallaxTransformer : ViewTransformer {

    private val viewLocation : IntArray = IntArray(2)

    override fun onAttached(view: ScrollTransformImageView) {
        view.scaleType = ImageView.ScaleType.CENTER_CROP
    }

    override fun onDetached(view: ScrollTransformImageView) {
    }

    override fun apply(view: ScrollTransformImageView, canvas: Canvas?) {
        if (view.scaleType == ImageView.ScaleType.CENTER_CROP) {
            val imageWidth = view.drawable.intrinsicWidth
            val imageHeight = view.drawable.intrinsicHeight

            val viewWidth = view.width - view.paddingLeft - view.paddingRight
            val viewHeight = view.height - view.paddingTop - view.paddingBottom

            // if this is true then the scaling must be based on the width of the
            // image view and the bitmap image itself
            if (imageWidth * viewHeight < viewWidth * imageHeight) {
                val scale = viewWidth.toFloat() / imageWidth.toFloat()
                val invisibleBitmapHeight = imageHeight * scale - viewHeight

                val deviceHeight = view.resources.displayMetrics.heightPixels
                val halfDeviceHeight = deviceHeight / 2

                view.getLocationInWindow(viewLocation)
                val y = when {
                    viewLocation[1] < -viewHeight -> -viewHeight
                    viewLocation[1] > deviceHeight -> deviceHeight
                    else -> viewLocation[1]
                }

                val translationScale = invisibleBitmapHeight / (deviceHeight + viewHeight)

                // make y to be vertically centered
                val translateY = -(y - halfDeviceHeight + viewHeight / 2f) * translationScale
                canvas?.translate(0f, translateY)
            }
        }
    }
}