package com.example.autoinspectionapp.ui.home.pagerScreens.testDrive

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
class TestDriveFragment : Fragment(R.layout.fragment_test_drive) {
    private val viewModel by viewModels<TestDriveViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}