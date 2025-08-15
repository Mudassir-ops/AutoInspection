package com.example.autoinspectionapp.ui.home.pagerScreens.interior

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.ui.home.pagerScreens.mechanical.MechanicalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InteriorFragment : Fragment(R.layout.fragment_interior) {
    private val viewModel by viewModels<InteriorViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}