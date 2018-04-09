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
class VerticalParallaxTransformer : ViewTransformer() {

    override fun onAttached(view: ScrollTransformImageView) {
        view.scaleType = ImageView.ScaleType.CENTER_CROP
    }

    override fun apply(view: ScrollTransformImageView, canvas: Canvas, viewX : Int, viewY : Int) {
        if (view.scaleType == ImageView.ScaleType.CENTER_CROP) {
            val imageWidth = view.drawable.intrinsicWidth
            val imageHeight = view.drawable.intrinsicHeight

            val viewWidth = view.width - view.paddingLeft - view.paddingRight
            val viewHeight = view.height - view.paddingTop - view.paddingBottom

            val deviceHeight = view.resources.displayMetrics.heightPixels

            if (viewY < -viewHeight || viewY > deviceHeight) return

            // if this is true then the scaling must be based on the width of the
            // image view and the bitmap image itself
            if (imageWidth * viewHeight < viewWidth * imageHeight) {
                val scale = viewWidth.toFloat() / imageWidth.toFloat()
                val invisibleBitmapHeight = imageHeight * scale - viewHeight

                val y = centeredY(viewY, viewHeight, deviceHeight)
                val translationScale = invisibleBitmapHeight / (deviceHeight + viewHeight)
                canvas.translate(0f, y * translationScale)
            }
        }
    }
}