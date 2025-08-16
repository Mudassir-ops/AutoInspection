package com.example.autoinspectionapp.ui.home.pagerScreens.interior

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InteriorFragment : Fragment(R.layout.fragment_interior) {
    private val viewModel by viewModels<InteriorViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}