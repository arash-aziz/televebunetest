
package com.example.githubtest.core.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

// Reviewed
class BounceClickEffectAnimator(
    val view : View ,
    private val scaleOffSet : Float = 0.03F ,
    private val alphaScale : Float = 0.8F ,
) {

    private val onTouchActionDownAnimatorSet : AnimatorSet = AnimatorSet().apply {
        val xScale = ObjectAnimator.ofFloat(view , "scaleX" , (1 - scaleOffSet))
        val yScale = ObjectAnimator.ofFloat(view , "scaleY" , (1 - scaleOffSet))
        val alpha = ObjectAnimator.ofFloat(view , "alpha" , alphaScale)
        playTogether(alpha , xScale , yScale)
    }

    private val onTouchActionUpAnimatorSet : AnimatorSet = AnimatorSet().apply {
        val xScale = ObjectAnimator.ofFloat(
            view ,
            "scaleX" ,
            (1 - scaleOffSet) ,
            (1 + scaleOffSet) ,
            1F
        )
        val yScale = ObjectAnimator.ofFloat(
            view ,
            "scaleY" ,
            (1 - scaleOffSet) ,
            (1 + scaleOffSet) ,
            1F
        )
        val alpha = ObjectAnimator.ofFloat(view , "alpha" , alphaScale , view.alpha)
        playTogether(alpha , xScale , yScale)
    }

    private fun touchEffect() {
        onTouchActionUpAnimatorSet.end()
        onTouchActionDownAnimatorSet.start()

    }

    private fun releaseEffect() {
        onTouchActionDownAnimatorSet.end()
        onTouchActionUpAnimatorSet.start()
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setClickListener(clickListener : View.OnClickListener) {
        (view).setOnTouchListener { _ : View? , event : MotionEvent ->

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    touchEffect()
                }

                MotionEvent.ACTION_UP , MotionEvent.ACTION_CANCEL -> {
                    releaseEffect()


                }

            }
            return@setOnTouchListener false
        }
        view.setOnClickListener {
            clickListener.onClick(it)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setClickListener(clickView : View , clickListener : View.OnClickListener) {
        clickView.setOnTouchListener { _ : View? , event : MotionEvent ->

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    touchEffect()
                }

                MotionEvent.ACTION_UP , MotionEvent.ACTION_CANCEL -> {
                    releaseEffect()


                }

            }
            return@setOnTouchListener false
        }
        clickView.setOnClickListener {
            clickListener.onClick(it)
        }
    }

}