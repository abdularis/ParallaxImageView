package com.github.abdularis.piv

import android.content.Context
import android.util.AttributeSet
import com.github.abdularis.piv.transformer.HorizontalParallaxTransformer

/**
 * Created by abdularis 08/04/2018
 */
class HorizontalParallaxImageView : ScrollTransformImageView {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attributeSet: AttributeSet) : super(ctx, attributeSet)

    init {
        super.viewTransformer = HorizontalParallaxTransformer()
    }
}