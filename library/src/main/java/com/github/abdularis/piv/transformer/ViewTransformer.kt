package com.github.abdularis.piv.transformer

import android.graphics.Canvas
import com.github.abdularis.piv.ScrollTransformImageView

/**
 * Created by abdularis 07/04/2018
 */
interface ViewTransformer {

    /**
     * This will be called when it's being set into the ScrollTransformImageView
     */
    fun onAttached(view : ScrollTransformImageView)

    /**
     * This will be called when it's being removed or replaced by other viewTransformer
     * from the ScrollTransformImageView
     */
    fun onDetached(view : ScrollTransformImageView)

    /**
     * apply will be called every time the view scrolled and before rendered
     */
    fun apply(view: ScrollTransformImageView, canvas: Canvas?)
}