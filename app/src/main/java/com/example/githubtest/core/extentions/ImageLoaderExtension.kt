package com.example.githubtest.core.extentions

import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation


fun ImageView.displayCircleImage(imageUrl : String) {
    displayCircle(imageUrl)
}

fun ImageView.displayCenterCropImage(
    imageUrl : String
) {
    load(imageUrl) {
        crossfade(true)
    }
    scaleType = ImageView.ScaleType.CENTER_CROP
}


 private fun ImageView.displayCircle(
    imageUrl : String ,
) {
    load(imageUrl) {
        crossfade(true)
        transformations(CircleCropTransformation())
    }
}



