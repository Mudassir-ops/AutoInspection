package com.example.autoinspectionapp

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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

fun Fragment.getCurrentFragment(position: Int, sections: List<Section>): PagerSaveAble? {
    val fm = childFragmentManager
    return fm.fragments.firstOrNull { fragment ->
        when (sections[position]) {
            Section.PRELIMINARY_INFO -> fragment is PreliminaryFragment
            Section.ACCIDENTAL_CHECKLIST -> fragment is AccidentalChecklistFragment
            Section.MECHANICAL_FUNCTION -> fragment is MechanicalFragment
            Section.AC_HEATER_OPERATION -> fragment is AcHeaterFragment
            Section.INTERIOR -> fragment is InteriorFragment
            Section.ELECTRONIC_FUNCTION -> fragment is ElectronicFragment
            Section.SUSPENSION_FUNCTION -> fragment is SuspensionFragment
            Section.EXTERIOR_BODY -> fragment is ExteriorFragment
            Section.TYRES -> fragment is TyresFragment
            Section.ACCESSORIES -> fragment is AccessoriesFragment
            Section.TEST_DRIVE -> fragment is TestDriveFragment
        }
    } as? PagerSaveAble
}

fun Fragment.showLoader() {
    (activity as? BaseActivity)?.showLoader()
}

fun Fragment.hideLoader() {
    (activity as? BaseActivity)?.hideLoader()
}