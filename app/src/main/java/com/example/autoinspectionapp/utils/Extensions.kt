package com.example.autoinspectionapp.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.example.autoinspectionapp.databinding.FragmentHomeBinding
import com.example.autoinspectionapp.utils.imagesdelegate.ImagePickerDelegate
import com.example.commons.base.base.BaseActivity

fun Fragment.showLoader() {
    (activity as? BaseActivity)?.showLoader()
}

fun Fragment.hideLoader() {
    (activity as? BaseActivity)?.hideLoader()
}

fun FragmentHomeBinding?.showShimmer() {
    this?.apply {
        shimmerContainer.visibility = View.VISIBLE
        viewPager.visibility = View.INVISIBLE
    }
}

fun FragmentHomeBinding?.hideShimmer() {
    this?.apply {
        shimmerContainer.visibility = View.GONE
        viewPager.visibility = View.VISIBLE
    }
}
