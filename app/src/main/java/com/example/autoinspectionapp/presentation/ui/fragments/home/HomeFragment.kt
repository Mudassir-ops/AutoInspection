package com.example.autoinspectionapp.presentation.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.ShimmerAdapter
import com.example.autoinspectionapp.databinding.FragmentHomeBinding
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.sealed.ShimmerState
import com.example.autoinspectionapp.hideShimmer
import com.example.autoinspectionapp.presentation.ui.actvities.CarSchemanticViewActivity
import com.example.autoinspectionapp.presentation.ui.fragments.base.viewBinding
import com.example.autoinspectionapp.presentation.ui.fragments.home.adapter.InspectionPagerAdapter
import com.example.autoinspectionapp.presentation.ui.fragments.main.MainViewModel
import com.example.autoinspectionapp.showShimmer
import com.example.autoinspectionapp.updateButtonState
import com.example.autoinspectionapp.utils.Section
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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
    private var shimmerAdapter = ShimmerAdapter(10)


    @Inject
    lateinit var helper: LogsHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sections.setupPagerAdapter()
        setupClickListeners()
        observeShimmer()
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            helper.createLog("Back pressed Home")
            goGack()
        }
        binding?.rvShimmer?.adapter = shimmerAdapter
    }

    fun goGack() {
        if ((binding?.viewPager?.currentItem ?: 0) > 0) {
            viewModel.loadHideShimmer(visibleOrHide = true)
            binding?.viewPager?.setCurrentItem(
                (binding?.viewPager?.currentItem ?: 0) - 1,
                false
            )
        } else {
            findNavController().navigateUp()
        }
    }

    private fun setupClickListeners() {
        binding?.apply {
            btnContinue.setOnClickListener {
                viewModel.loadHideShimmer(visibleOrHide = true, R.id.btnContinue)
                LogsHelper().createLog("setupClickListeners${viewPager.currentItem}---$currentFragmentPosition")
                binding?.viewPager?.setCurrentItem(viewPager.currentItem + 1, false)
                saveCurrentPageData()
            }
            btnBack.setOnClickListener {
                viewModel.loadHideShimmer(visibleOrHide = true, R.id.btnBack)
                goGack()
            }
            btnMarkSchemantic.setOnClickListener {
                val btnText = btnMarkSchemantic.text.toString()
                if (btnText == context?.getString(R.string.mark_schemantic)) {
                    startActivity(
                        Intent(
                            context ?: return@setOnClickListener,
                            CarSchemanticViewActivity::class.java
                        )
                    )
                } else {
                    findNavController().navigateUp()
                }
            }
            homeMenu.setOnClickListener { view ->
                homeMenu.rotation = 270F
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
                        R.id.menu_home -> navigateToFragment(99)
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
        if (pos != 99) {
            viewModel.loadHideShimmer(visibleOrHide = true)
            binding?.viewPager?.setCurrentItem(pos, false)
        } else {
            findNavController().navigateUp()
        }
    }

    private fun List<Section>.setupPagerAdapter() {
        val adapter = InspectionPagerAdapter(childFragmentManager, lifecycle, sectionsList = this)
        pagerAdapterRef = WeakReference(adapter)
        binding?.apply {
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 1
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

    fun observeShimmer() {
        val btnBGSelectedColor = ContextCompat.getColorStateList(
            context ?: return,
            R.color.tertiary_color
        )

        val btnTextSelectedColor = ContextCompat.getColorStateList(
            context ?: return,
            R.color.legend_black
        )

        val btnBGUnSelectedColor = ContextCompat.getColorStateList(
            context ?: return,
            R.color.text_gray_color
        )

        val btnTextUnSelectedColor = ContextCompat.getColorStateList(
            context ?: return,
            R.color.white
        )

        val btnPrevBGUnSelectedColor = ContextCompat.getColorStateList(
            context ?: return,
            R.color.legend_red
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.shimmerSharedFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { shimmerState ->
                    when (shimmerState) {
                        is ShimmerState.ShimmerVisibility -> {
                            if (shimmerState.isShimmer) {
                                binding.showShimmer()
                            } else {
                                binding.hideShimmer()
                            }
                            when (shimmerState.buttonId) {
                                R.id.btnContinue -> {
                                    binding?.btnContinue?.updateButtonState(
                                        isEnabled = !shimmerState.isShimmer,
                                        backgroundColor = if (shimmerState.isShimmer) {
                                            btnBGSelectedColor
                                        } else {
                                            btnBGUnSelectedColor
                                        },
                                        textColor = if (shimmerState.isShimmer) {
                                            btnTextSelectedColor
                                        } else {
                                            btnTextUnSelectedColor
                                        }
                                    )
                                }

                                R.id.btnBack -> {
                                    binding?.btnBack?.updateButtonState(
                                        isEnabled = !shimmerState.isShimmer,
                                        backgroundColor = if (shimmerState.isShimmer) {
                                            btnBGSelectedColor
                                        } else {
                                            btnPrevBGUnSelectedColor
                                        },
                                        textColor = if (shimmerState.isShimmer) {
                                            btnTextSelectedColor
                                        } else {
                                            btnTextUnSelectedColor
                                        }
                                    )
                                }

                                else -> null
                            }
                        }
                    }
                }
        }

    }
}