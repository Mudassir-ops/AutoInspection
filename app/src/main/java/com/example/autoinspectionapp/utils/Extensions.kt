package com.example.autoinspectionapp.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentHomeBinding
import com.example.autoinspectionapp.utils.imagesdelegate.ImagePickerDelegate
import com.example.commons.base.base.BaseActivity

/**
 * All Destination For Home Pages Screen
 * */
val nextDestinations = mapOf(
    R.id.preliminaryFragment to R.id.action_preliminaryFragment_to_accidentalChecklistFragment,
    R.id.accidentalChecklistFragment to R.id.action_accidentalChecklistFragment_to_mechanicalFragment,
    R.id.mechanicalFragment to R.id.action_mechanicalFragment_to_acHeaterFragment,
    R.id.acHeaterFragment to R.id.action_acHeaterFragment_to_interiorFragment,
    R.id.interiorFragment to R.id.action_interiorFragment_to_electronicFragment,
    R.id.electronicFragment to R.id.action_electronicFragment_to_suspensionFragment,
    R.id.suspensionFragment to R.id.action_suspensionFragment_to_exteriorFragment,
    R.id.exteriorFragment to R.id.action_exteriorFragment_to_tyresFragment,
    R.id.tyresFragment to R.id.action_tyresFragment_to_accessoriesFragment,
    R.id.accessoriesFragment to R.id.action_accessoriesFragment_to_testDriveFragment,
    R.id.testDriveFragment to R.id.action_testDriveFragment_to_saveFragment
)

/**
 * Menu Destination
 * */
val menuNavigationMap = mapOf(
    R.id.menu_preliminary_info to R.id.preliminaryFragment,
    R.id.menu_accidental_checklist to R.id.accidentalChecklistFragment,
    R.id.menu_mechanical_function to R.id.mechanicalFragment,
    R.id.menu_ac_heater to R.id.acHeaterFragment,
    R.id.menu_interior to R.id.interiorFragment,
    R.id.menu_electronic to R.id.electronicFragment,
    R.id.menu_suspension to R.id.suspensionFragment,
    R.id.menu_exterior to R.id.exteriorFragment,
    R.id.menu_tyres to R.id.tyresFragment,
    R.id.menu_accessories to R.id.accessoriesFragment,
    R.id.menu_test_drive to R.id.testDriveFragment,
    R.id.menu_save_send to R.id.saveFragment,
    R.id.menu_home to R.id.navigation_home
)


fun Fragment.showLoader() {
    (activity as? BaseActivity)?.showLoader()
}

fun Fragment.hideLoader() {
    (activity as? BaseActivity)?.hideLoader()
}

fun FragmentHomeBinding?.showShimmer() {
    this?.apply {
        shimmerContainer.visibility = View.VISIBLE
        navHostFragmentHome.visibility = View.INVISIBLE
    }
}

fun FragmentHomeBinding?.hideShimmer() {
    this?.apply {
        shimmerContainer.visibility = View.GONE
        navHostFragmentHome.visibility = View.VISIBLE
    }
}
