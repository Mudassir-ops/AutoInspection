package com.example.autoinspectionapp.presentation.ui.fragments.main

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentMainBinding
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.commons.base.base.viewBinding
import com.example.commons.safeNav
import com.example.commons.setCustomRipple
import com.example.commons.showExitDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by activityViewModels<MainViewModel>()
    private val binding by viewBinding(FragmentMainBinding::bind)

    @Inject
    lateinit var helper: LogsHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            helper.createLog("Back pressed Main")
            activity?.showExitDialog()
        }
        clickListeners()
    }

    private fun clickListeners() {
        binding?.apply {
            viewInspectVehicle.setCustomRipple(
                rippleColor = ContextCompat.getColor(context ?: return@apply, R.color.myRippleColor)
            ) {
                viewModel.loadHideShimmer(visibleOrHide = true)
                findNavController().safeNav(
                    currentDestId = R.id.navigation_main,
                    actionId = R.id.action_navigation_main_to_navigation_home
                )
            }
            viewPdf.setCustomRipple(
                rippleColor = ContextCompat.getColor(context ?: return@apply, R.color.myRippleColor)
            ) {

            }
            binding?.headerLayout?.btnBack?.setOnClickListener {
                binding?.mainDrawerLayout?.openDrawer(GravityCompat.START)
            }
        }
    }

}