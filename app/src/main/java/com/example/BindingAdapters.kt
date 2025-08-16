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
import com.example.autoinspectionapp.utils.SpinnerFieldView
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("android:text")
fun setText(view: MaterialTextView, text: CharSequence?) {
    view.text = text
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:imageUri111")
fun loadImage(view: AppCompatImageView, imageUrl: String?) {
    Log.e("loadImage", "loadImage: $imageUrl")
    imageUrl?.let {
        Glide.with(view.context).load(it)
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
}

@BindingAdapter(value = ["items", "defaultValue"], requireAll = false)
fun SpinnerFieldView.bindItems(
    items: List<String>?,
    defaultValue: String?
) {
    items?.let {
        this.setItems(it, defaultValue)
    }
}

@BindingAdapter("app:imageUri")
fun loadImageThumbnail(view: AppCompatImageView, imageUrl: String?) {
    Log.e("loadImage", "loadImage: $imageUrl")
    imageUrl?.let {
        Glide.with(view.context)
            .load(imageUrl)
            .thumbnail(
                Glide.with(view.context)
                    .load(imageUrl)
                    .sizeMultiplier(0.25f)
            )
            .error(R.drawable.image_placeholder)
            .into(view)
    } ?: run {
        Glide.with(view.context).load(R.drawable.image_placeholder)
    }
}

@BindingAdapter("app:imageUriDialog")
fun loadImageDialog(view: AppCompatImageView, imageUrl: String?) {
    Log.e("loadImage", "loadImage: $imageUrl")
    imageUrl?.let {
        Glide.with(view.context)
            .load(imageUrl)
            .error(R.drawable.image_placeholder)
            .into(view)
    } ?: run {
        Glide.with(view.context).load(R.drawable.image_placeholder)
    }
}

