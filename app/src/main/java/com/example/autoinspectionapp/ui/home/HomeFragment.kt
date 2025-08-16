package com.example.autoinspectionapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentHomeBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.safeNav
import com.example.autoinspectionapp.ui.home.adapter.InspectionPagerAdapter
import com.example.autoinspectionapp.utils.Section
import com.example.autoinspectionapp.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private var pagerAdapterRef: WeakReference<InspectionPagerAdapter>? = null

    //   private val viewModel by viewModels<HomeViewModel>()
    private val binding by viewBinding(FragmentHomeBinding::bind)
    val sections = listOf(
        Section.PRELIMINARY_INFO,
        Section.ACCIDENTAL_CHECKLIST,
        Section.MECHANICAL_FUNCTION,
        Section.AC_HEATER_OPERATION,
        Section.INTERIOR,
        Section.ELECTRONIC_FUNCTION,
        Section.SUSPENSION_FUNCTION,
        Section.EXTERIOR_BODY,
        Section.TYRES,
        Section.ACCESSORIES,
        Section.TEST_DRIVE
    )
    private var currentFragmentPosition = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sections.setupPagerAdapter()
        setupClickListeners()
    }

    fun goGack() {
        if ((binding?.viewPager?.currentItem ?: 0) > 0) {
            binding?.viewPager?.currentItem = (binding?.viewPager?.currentItem ?: 0) - 1
        } else {
            findNavController().navigateUp()
        }
    }

    private fun setupClickListeners() {
        binding?.apply {
            btnContinue.setOnClickListener {
                viewPager.currentItem = viewPager.currentItem + 1
                saveCurrentPageData()
            }
            btnMarkSchemantic.setOnClickListener {
                val btnText = btnMarkSchemantic.text.toString()
                if (btnText == context?.getString(R.string.mark_schemantic)) {
                    findNavController().safeNav(
                        R.id.navigation_home,
                        R.id.action_navigation_home_to_navigation_car_schemantic
                    )
                } else {
                    goGack()
                }
            }
        }
    }

    private fun List<Section>.setupPagerAdapter() {
        val adapter = InspectionPagerAdapter(childFragmentManager, lifecycle, sectionsList = this)
        pagerAdapterRef = WeakReference(adapter)
        binding?.apply {
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 2
            viewPager.isUserInputEnabled = false
            TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
            tabLayout.touchables.forEach { it.isClickable = false }
            viewPager.setupButtonWithPageChange()
        }
    }

    fun ViewPager2.setupButtonWithPageChange(
    ) {
        this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding?.apply {
                    if (position == 0) {
                        tabLayout.visibility = View.GONE
                        btnMarkSchemantic.text = context?.getString(R.string.mark_schemantic)
                    } else {
                        tabLayout.isVisible = true
                        btnMarkSchemantic.text = context?.getString(R.string.goBack)
                    }
                    currentFragmentPosition = position
                    Log.e("setupButtonWithPageChange", "onPageSelected: $position")
                }
            }

        })
    }

    private fun saveCurrentPageData() {
        val lastPosition = currentFragmentPosition - 1
        val fragment = pagerAdapterRef?.get()
            ?.getFragment(position = lastPosition) as? PagerSaveAble
        if (fragment != null) {
            Log.d("saveCurrentPageData", "Saving page $lastPosition-1 via $fragment")
            fragment.saveData(pos = lastPosition)
        } else {
            Log.w(
                "saveCurrentPageData",
                "No PagerSaveAble found for page $lastPosition (tag=$tag)"
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pagerAdapterRef?.clear()
    }
}