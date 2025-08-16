package com.example.autoinspectionapp.ui.home.pagerScreens.suspension

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuspensionFragment : Fragment(R.layout.fragment_suspension) {
    private val viewModel by viewModels<SuspensionViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}