package com.build.atmik
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils

object AnimationUtility {

    fun applyFillAnimation(view: View, context: Context) {
        val fillAnimation = AnimationUtils.loadAnimation(context, R.anim.fill_btn)
        view.startAnimation(fillAnimation)
    }
}