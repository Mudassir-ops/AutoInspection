package com.example.autoinspectionapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentHomeBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sections.setupPagerAdapter()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding?.apply {
            btnNext.setOnClickListener {
                viewPager.currentItem = viewPager.currentItem + 1
                saveCurrentPageData()
            }
            btnBack.setOnClickListener {
                viewPager.currentItem = viewPager.currentItem - 1
            }
        }
    }

    private fun List<Section>.setupPagerAdapter() {
        val adapter = InspectionPagerAdapter(childFragmentManager, lifecycle, sectionsList = this)
        pagerAdapterRef = WeakReference(adapter)
        binding?.apply {
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 9
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
                Log.e("setupButtonWithPageChange", "onPageSelected: $position")
            }
        })
    }

    private fun saveCurrentPageData() {
        val position = binding?.viewPager?.currentItem
        val tag = "f$position"

        // If you created adapter with `this` (the fragment), use childFragmentManager:
        val fm = childFragmentManager

        val target = (fm.findFragmentByTag(tag) as? PagerSaveAble)
            ?: (fm.fragments.firstOrNull { it.isVisible && it is PagerSaveAble } as? PagerSaveAble)

        if (target != null) {
            Log.d("saveCurrentPageData", "Saving page $position via $target")
            target.saveData()
        } else {
            Log.w("saveCurrentPageData", "No PagerSaveAble found for page $position (tag=$tag)")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        pagerAdapterRef?.clear()
    }
}