package com.example.autoinspectionapp.ui.home.pagerScreens.electronic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElectronicFragment : Fragment(R.layout.fragment_electronic) {
    private val viewModel by viewModels<ElectronicViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}