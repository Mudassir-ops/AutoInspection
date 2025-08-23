package com.example.autoinspectionapp

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.autoinspectionapp.databinding.FragmentHomeBinding
import com.example.autoinspectionapp.presentation.ui.actvities.base.BaseActivity
import java.io.File
import java.io.FileOutputStream
import java.util.Calendar

fun NavController.safeNav(
    @IdRes currentDestId: Int,
    @IdRes actionId: Int
) {
    if (currentDestination?.id == currentDestId) {
        navigate(actionId)
    }
}

fun saveUriToCache(context: Context, uri: Uri): File? {
    return try {
        val fileName = "${System.currentTimeMillis()}.jpg" // or get from uri
        val cacheFile = File(context.cacheDir, fileName)

        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            FileOutputStream(cacheFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }

        cacheFile
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun View?.showDatePicker(onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    this?.context?.let {
        DatePickerDialog(
            it,
            { _, year, month, dayOfMonth ->
                val dateString = "$dayOfMonth/${month + 1}/$year"
                onDateSelected(dateString)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }?.show()
}


fun Fragment.showLoader() {
    (activity as? BaseActivity)?.showLoader()
}

fun Fragment.hideLoader() {
    (activity as? BaseActivity)?.hideLoader()
}

fun View.setCustomRipple(
    rippleColor: Int,
    onClick: () -> Unit
) {
    // 1) Create the ripple drawable
    val colorStateList = ColorStateList.valueOf(rippleColor)
    val rippleDrawable = RippleDrawable(
        colorStateList,
        null,
        null
    )
    // 2) Apply it
    this.foreground = rippleDrawable
    this.isClickable = true

    // 3) Set click
    setOnClickListener { onClick() }
}

fun Activity?.showExitDialog() {
    this?.let { ctx ->
        androidx.appcompat.app.AlertDialog.Builder(ctx)
            .setTitle("Exit App")
            .setMessage("Are you sure you want to exit?")
            .setCancelable(true)
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                finish() // exit the app
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}

private var currentToast: Toast? = null

fun showToast(context: Context, message: String) {
    currentToast?.cancel()
    currentToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    currentToast?.show()
}

fun RecyclerView.showShimmer(count: Int = 5) {
    adapter = ShimmerAdapter(count)
}

fun <T, VH : RecyclerView.ViewHolder> RecyclerView.showData(
    adapter: RecyclerView.Adapter<VH>,
    data: List<T>,
    submit: (RecyclerView.Adapter<VH>, List<T>) -> Unit
) {
    this.adapter = adapter
    submit(adapter, data)
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

fun AppCompatButton.updateButtonState(
    isEnabled: Boolean,
    backgroundColor: ColorStateList?,
    textColor: ColorStateList? = null
) {
    this.isClickable = isEnabled
    this.isEnabled = isEnabled
    this.backgroundTintList = backgroundColor
    textColor?.let { this.setTextColor(it) }
}