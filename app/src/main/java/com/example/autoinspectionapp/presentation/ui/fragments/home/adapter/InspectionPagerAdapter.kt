package com.example.autoinspectionapp.presentation.ui.fragments.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.electronic.ElectronicFragment
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.suspension.SuspensionFragment
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.testDrive.TestDriveFragment
import com.example.autoinspectionapp.utils.enums.Section


class InspectionPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val sectionsList: List<Section>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentMap = mutableMapOf<Int, Fragment>()

    override fun getItemCount() = sectionsList.size

    override fun createFragment(position: Int): Fragment {
        val fragment = when (sectionsList[position]) {

            Section.ELECTRONIC_FUNCTION -> ElectronicFragment()
            Section.SUSPENSION_FUNCTION -> SuspensionFragment()
            Section.TEST_DRIVE -> TestDriveFragment()
            Section.EXTERIOR_BODY -> TODO()
            Section.ACCESSORIES -> TODO()
            Section.TYRES -> TODO()
            Section.SAVE_SEND -> TODO()
            Section.PRELIMINARY_INFO -> TODO()
            Section.ACCIDENTAL_CHECKLIST -> TODO()
            Section.MECHANICAL_FUNCTION -> TODO()
            Section.AC_HEATER_OPERATION -> TODO()
            Section.INTERIOR -> TODO()
        }
        fragmentMap[position] = fragment
        return fragment
    }

    /** Return the fragment instance for a given position */
    fun getFragment(position: Int): Fragment? {
        return fragmentMap[position]
    }
}