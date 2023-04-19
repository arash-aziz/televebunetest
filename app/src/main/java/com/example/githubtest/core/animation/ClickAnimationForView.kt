package com.example.githubtest.core.animation

import android.view.View

object ClickAnimationForView {

    fun setClickListener(view : View , clickListener : View.OnClickListener) {
        val bounceClickEffectAnimator = BounceClickEffectAnimator(view)
        bounceClickEffectAnimator.setClickListener(clickListener)
    }

    fun setClickListener(clickView : View , view : View , clickListener : View.OnClickListener) {
        val bounceClickEffectAnimator = BounceClickEffectAnimator(view)
        bounceClickEffectAnimator.setClickListener(clickView , clickListener)
    }

}