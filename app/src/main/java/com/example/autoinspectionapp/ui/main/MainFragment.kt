package com.example.autoinspectionapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentMainBinding
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.hideLoader
import com.example.autoinspectionapp.safeNav
import com.example.autoinspectionapp.setCustomRipple
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by viewModels<MainViewModel>()
    private val binding by viewBinding(FragmentMainBinding::bind)

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
            navHostFragmentHome.doOnPreDraw {
                hideLoader()
                helper.createLog("MainFragment", "Home container is now drawn")
            }
        }
    }

    fun showHome() {
        binding?.apply {
            mainButtonsContainer.visibility = View.GONE
            navHostFragmentHome.visibility = View.VISIBLE
        }
    }

    fun showMainMenu() {
        binding?.apply {
            mainButtonsContainer.visibility = View.VISIBLE
            navHostFragmentHome.visibility = View.GONE
        }
    }

}