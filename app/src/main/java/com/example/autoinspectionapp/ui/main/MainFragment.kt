package com.example.autoinspectionapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentMainBinding
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.setCustomRipple
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by activityViewModels<MainViewModel>()
    private val binding by viewBinding(FragmentMainBinding::bind)
    private var isHomeVisible: Boolean = false

    @Inject
    lateinit var helper: LogsHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }

    private fun clickListeners() {
        binding?.apply {
            viewInspectVehicle.setCustomRipple(
                rippleColor = ContextCompat.getColor(context ?: return@apply, R.color.myRippleColor)
            ) {
                showHome()
            }
            CompleteDraft.setCustomRipple(
                rippleColor = ContextCompat.getColor(context ?: return@apply, R.color.myRippleColor)
            ) {

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

    fun showHome() {
        binding?.apply {
            mainButtonsContainer.visibility = View.GONE
            navHostContainer.visibility = View.VISIBLE
            isHomeVisible = true
        }
    }

    fun showMainMenu() {
        binding?.apply {
            mainButtonsContainer.visibility = View.VISIBLE
            navHostContainer.visibility = View.GONE
            isHomeVisible = false
            if (viewModel.currentFragmentPosition == 0) {
                tvVehicleInspection.text = context?.getString(R.string.vehicle_inspection)
            } else {
                tvVehicleInspection.text = context?.getString(R.string.complete_draft)
            }
        }
    }

    fun isHomeCurrentlyVisible(): Boolean = isHomeVisible

}