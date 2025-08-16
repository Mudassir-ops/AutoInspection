package com.example.autoinspectionapp.ui.home.pagerScreens.testDrive

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestDriveFragment : Fragment(R.layout.fragment_test_drive) {
    private val viewModel by viewModels<TestDriveViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}