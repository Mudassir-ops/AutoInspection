package com.example.autoinspectionapp

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.example.autoinspectionapp.data.remote.BaseResponse
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.ui.home.pagerScreens.acHeate.AcHeaterFragment
import com.example.autoinspectionapp.ui.home.pagerScreens.accessories.AccessoriesFragment
import com.example.autoinspectionapp.ui.home.pagerScreens.accidentalChecklist.AccidentalChecklistFragment
import com.example.autoinspectionapp.ui.home.pagerScreens.electronic.ElectronicFragment
import com.example.autoinspectionapp.ui.home.pagerScreens.exterior.ExteriorFragment
import com.example.autoinspectionapp.ui.home.pagerScreens.interior.InteriorFragment
import com.example.autoinspectionapp.ui.home.pagerScreens.mechanical.MechanicalFragment
import com.example.autoinspectionapp.ui.home.pagerScreens.preliminary.PreliminaryFragment
import com.example.autoinspectionapp.ui.home.pagerScreens.suspension.SuspensionFragment
import com.example.autoinspectionapp.ui.home.pagerScreens.testDrive.TestDriveFragment
import com.example.autoinspectionapp.ui.home.pagerScreens.tyres.TyresFragment
import com.example.autoinspectionapp.utils.Section
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