package com.elka.stepikandroid

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun showImage(image: ImageView, imageUrl: String) {
  Glide
    .with(image.context)
    .load(imageUrl)
    .into(image)
}