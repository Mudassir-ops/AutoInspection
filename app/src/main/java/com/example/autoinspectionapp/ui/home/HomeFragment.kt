package com.example.autoinspectionapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.autoinspectionapp.CarSchemanticViewActivity
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentHomeBinding
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.setCustomRipple
import com.example.autoinspectionapp.showExitDialog
import com.example.autoinspectionapp.ui.home.adapter.InspectionPagerAdapter
import com.example.autoinspectionapp.ui.main.MainFragment
import com.example.autoinspectionapp.ui.main.MainViewModel
import com.example.autoinspectionapp.utils.Section
import com.example.autoinspectionapp.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private var pagerAdapterRef: WeakReference<InspectionPagerAdapter>? = null

    private val viewModel by activityViewModels<MainViewModel>()
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
        Section.TEST_DRIVE,
        Section.SAVE_SEND
    )
    private var currentFragmentPosition = 0

    @Inject
    lateinit var helper: LogsHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sections.setupPagerAdapter()
        setupClickListeners()
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            helper.createLog("Back pressed Home")
            goGack()
        }
    }

    fun goGack() {
        val main = (parentFragment?.parentFragment as? MainFragment)
        val homeVisible = main?.isHomeCurrentlyVisible() ?: false
        helper.createLog("goGack--$homeVisible--->$main")
        if (homeVisible) {
            if ((binding?.viewPager?.currentItem ?: 0) > 0) {
                binding?.viewPager?.currentItem = (binding?.viewPager?.currentItem ?: 0) - 1
            } else {
                val main = (parentFragment?.parentFragment as? MainFragment)
                val homeVisible = main?.isHomeCurrentlyVisible() ?: false
                if (!homeVisible) {
                    activity?.showExitDialog()
                } else {
                    main.showMainMenu()
                }
            }
        } else {
            activity?.showExitDialog()
        }
    }

    private fun setupClickListeners() {
        binding?.apply {
            btnContinue.setCustomRipple(
                rippleColor = ContextCompat.getColor(context ?: return@apply, R.color.myRippleColor)
            ) {
                LogsHelper().createLog("setupClickListeners${viewPager.currentItem}---$currentFragmentPosition")
                viewPager.currentItem = viewPager.currentItem + 1
                saveCurrentPageData()
            }
            btnBack.setCustomRipple(
                rippleColor = ContextCompat.getColor(
                    context ?: return@apply,
                    R.color.myRippleColor
                )
            ) {
                goGack()
            }
            btnMarkSchemantic.setCustomRipple(
                rippleColor = ContextCompat.getColor(context ?: return@apply, R.color.blue_color)
            ) {
                val btnText = btnMarkSchemantic.text.toString()
                if (btnText == context?.getString(R.string.mark_schemantic)) {
                    startActivity(
                        Intent(
                            context ?: return@setCustomRipple,
                            CarSchemanticViewActivity::class.java
                        )
                    )
                } else {
                    val main = parentFragment?.parentFragment
                    helper.createLog("goGack--$main")
                    (main as? MainFragment)?.showMainMenu()
                }
            }
            homeMenu.setOnClickListener { view ->
                homeMenu.rotation = 180F
                val popup = PopupMenu(context ?: return@setOnClickListener, view)
                popup.menuInflater.inflate(R.menu.home_menu, popup.menu)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_preliminary_info -> navigateToFragment(Section.PRELIMINARY_INFO.position)
                        R.id.menu_accidental_checklist -> navigateToFragment(Section.ACCIDENTAL_CHECKLIST.position)
                        R.id.menu_mechanical_function -> navigateToFragment(Section.MECHANICAL_FUNCTION.position)
                        R.id.menu_ac_heater -> navigateToFragment(Section.AC_HEATER_OPERATION.position)
                        R.id.menu_interior -> navigateToFragment(Section.INTERIOR.position)
                        R.id.menu_electronic -> navigateToFragment(Section.ELECTRONIC_FUNCTION.position)
                        R.id.menu_suspension -> navigateToFragment(Section.SUSPENSION_FUNCTION.position)
                        R.id.menu_exterior -> navigateToFragment(Section.EXTERIOR_BODY.position)
                        R.id.menu_tyres -> navigateToFragment(Section.TYRES.position)
                        R.id.menu_accessories -> navigateToFragment(Section.ACCESSORIES.position)
                        R.id.menu_test_drive -> navigateToFragment(Section.TEST_DRIVE.position)
                        R.id.menu_save_send -> navigateToFragment(Section.SAVE_SEND.position)
                    }
                    true
                }

                popup.show()
                popup.setOnDismissListener {
                    homeMenu.rotation = 0F
                }
            }

        }
    }

    private fun navigateToFragment(pos: Int) {
        binding?.viewPager?.currentItem = pos
    }

    private fun List<Section>.setupPagerAdapter() {
        val adapter = InspectionPagerAdapter(childFragmentManager, lifecycle, sectionsList = this)
        pagerAdapterRef = WeakReference(adapter)
        binding?.apply {
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 11
            viewPager.isUserInputEnabled = true
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
                    when (position) {
                        0 -> {
                            btnMarkSchemantic.visibility = View.VISIBLE
                            btnContinue.visibility = View.VISIBLE
                            btnBack.visibility = View.VISIBLE
                            btnMarkSchemantic.text = context?.getString(R.string.mark_schemantic)
                        }

                        11 -> {
                            btnMarkSchemantic.visibility = View.GONE
                            btnContinue.visibility = View.GONE
                            btnBack.visibility = View.GONE
                        }

                        else -> {
                            btnMarkSchemantic.visibility = View.GONE
                            btnContinue.visibility = View.VISIBLE
                            btnBack.visibility = View.VISIBLE
                            btnMarkSchemantic.text = context?.getString(R.string.goBack)
                        }
                    }
                    currentFragmentPosition = position
                    viewModel.currentFragmentPosition = currentFragmentPosition
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
            if (!isAdded || view == null) return
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