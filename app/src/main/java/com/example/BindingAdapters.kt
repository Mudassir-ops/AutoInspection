package com.example

import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.autoinspectionapp.R
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("android:text")
fun setText(view: MaterialTextView, text: CharSequence?) {
    view.text = text
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:imageUri")
fun loadImage(view: AppCompatImageView, imageUrl: String?) {
    Log.e("loadImage", "loadImage: ${imageUrl}")
    imageUrl?.let {
        Glide.with(view.context).load(it).placeholder(R.drawable.image_placeholder)
            .error(R.drawable.ic_launcher_foreground).into(view)
    } ?: run {
        Glide.with(view.context).load(R.drawable.image_placeholder)
    }
}

@BindingAdapter("app:lottieAnimationFromType")
fun loadLottieAnimationFromType(view: LottieAnimationView, lottieRes: Int?) {
    if (lottieRes != null) {
        view.setAnimation(lottieRes)
        view.playAnimation()
    }
}

@BindingAdapter("app:imageUrl")
fun loadImageWithResID(view: AppCompatImageView, imageUrl: Int) {
    imageUrl.let {
//        Glide.with(view.context).load(it).placeholder(R.drawable.imageplace)
//            .error(R.drawable.ic_launcher_foreground).into(view)
    }
}

@BindingAdapter("app:drawableImage")
fun setDrawableImage(view: AppCompatImageView, drawable: Drawable?) {
    Glide.with(view.context).load(drawable).placeholder(R.drawable.image_placeholder)
        .error(R.drawable.ic_launcher_foreground).into(view)
}

@BindingAdapter("app:byteArrayImage")
fun setByteArrayImage(view: AppCompatImageView, byteArray: ByteArray?) {
    if (byteArray?.isNotEmpty() == true) {
//        Glide.with(view.context).load(byteArray).placeholder(R.drawable.imageplace)
//            .error(R.drawable.ic_launcher_foreground).into(view)
    } else {
        //   view.setImageResource(R.drawable.imageplace)
    }
}

@BindingAdapter("app:imageUrlNew")
fun loadImageWithUri(view: AppCompatImageView, imageUrl: Uri?) {
    if (imageUrl != null) {
        Glide.with(view.context)
            .load(imageUrl)
            .error(R.drawable.image_placeholder)
            .into(view)
    }
//    } else {
//        Glide.with(view.context)
//            .load(R.drawable.image_placeholder).into(view)
//    }
}
