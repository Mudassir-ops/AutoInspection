package com.example.autoinspectionapp.ui.home.pagerScreens.exterior

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExteriorFragment : Fragment(R.layout.fragment_exterior) {
    private val viewModel by viewModels<ExteriorViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}