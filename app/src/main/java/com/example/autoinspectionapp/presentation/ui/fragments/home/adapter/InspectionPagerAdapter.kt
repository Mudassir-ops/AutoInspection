package com.example.autoinspectionapp.presentation.ui.fragments.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.acHeate.AcHeaterFragment
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accessories.AccessoriesFragment
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accidentalChecklist.AccidentalChecklistFragment
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.electronic.ElectronicFragment
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.exterior.ExteriorFragment
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.interior.InteriorFragment
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.mechanical.MechanicalFragment
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.preliminary.PreliminaryFragment
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.suspension.SuspensionFragment
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.testDrive.TestDriveFragment
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.tyres.TyresFragment
import com.example.autoinspectionapp.presentation.ui.fragments.savePage.SaveFragment
import com.example.autoinspectionapp.utils.enums.Section


class InspectionPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val sectionsList: List<Section>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    // Store created fragments by position
    private val fragmentMap = mutableMapOf<Int, Fragment>()

    override fun getItemCount() = sectionsList.size

    override fun createFragment(position: Int): Fragment {
        val fragment = when (sectionsList[position]) {
            Section.PRELIMINARY_INFO -> PreliminaryFragment()
            Section.ACCIDENTAL_CHECKLIST -> AccidentalChecklistFragment()
            Section.MECHANICAL_FUNCTION -> MechanicalFragment()
            Section.AC_HEATER_OPERATION -> AcHeaterFragment()
            Section.INTERIOR -> InteriorFragment()
            Section.ELECTRONIC_FUNCTION -> ElectronicFragment()
            Section.SUSPENSION_FUNCTION -> SuspensionFragment()
            Section.EXTERIOR_BODY -> ExteriorFragment()
            Section.TYRES -> TyresFragment()
            Section.ACCESSORIES -> AccessoriesFragment()
            Section.TEST_DRIVE -> TestDriveFragment()
            Section.SAVE_SEND -> SaveFragment()
        }
        fragmentMap[position] = fragment
        return fragment
    }

    /** Return the fragment instance for a given position */
    fun getFragment(position: Int): Fragment? {
        return fragmentMap[position]
    }
}