package com.github.abdularis.piv.transformer

import android.graphics.Canvas
import android.widget.ImageView
import com.github.abdularis.piv.ScrollTransformImageView

/**
 * Created by abdularis 08/04/2018
 */
class HorizontalParallaxTransformer : ViewTransformer {

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

            // if this is true then the scaling must be based on the height of the
            // image view and the bitmap image itself
            if (imageWidth * viewHeight > viewWidth * imageHeight) {
                val scale = viewHeight.toFloat() / imageHeight.toFloat()
                val invisibleBitmapWidth = imageWidth * scale - viewWidth

                val deviceWidth = view.resources.displayMetrics.widthPixels
                val halfDeviceWidth = deviceWidth / 2

                view.getLocationInWindow(viewLocation)
                val x = when {
                    viewLocation[0] < -viewWidth -> -viewWidth
                    viewLocation[0] > deviceWidth -> deviceWidth
                    else -> viewLocation[0]
                }

                val translationScale = invisibleBitmapWidth / (deviceWidth + viewWidth)

                // make x to be horizontally centered
                val translateX = -(x - halfDeviceWidth + viewWidth / 2f) * translationScale
                canvas?.translate(translateX, 0f)
            }
        }
    }

}