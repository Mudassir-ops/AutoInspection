package com.example.autoinspectionapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentMainBinding
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import safeNav

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by viewModels<MainViewModel>()
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }

    private fun clickListeners() {
        binding?.apply {
            viewInspectVehicle.setOnClickListener {
                findNavController().safeNav(
                    R.id.navigation_main,
                    R.id.action_navigation_main_to_navigation_home
                )
            }
            CompleteDraft.setOnClickListener {

            }
            viewPdf.setOnClickListener {

            }
        }
    }

}