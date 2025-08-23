package com.example.autoinspectionapp.presentation.ui.actvities.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.autoinspectionapp.R

abstract class BaseActivity : AppCompatActivity() {
    private var loadingView: View? = null

    fun showLoader() {
        if (loadingView == null) {
            loadingView = LayoutInflater.from(this).inflate(
                R.layout.layout_loading_progress,
                findViewById(android.R.id.content),
                false
            ).apply { isClickable = true }
            (findViewById<ViewGroup>(android.R.id.content)).addView(loadingView)
        } else {
            loadingView?.visibility = View.VISIBLE
        }
    }

    fun hideLoader() {
        loadingView?.visibility = View.GONE
    }
}