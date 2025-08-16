package com.example.autoinspectionapp.ui.home.pagerScreens.accessories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccessoriesFragment : Fragment(R.layout.fragment_accessories) {
    private val viewModel by viewModels<AccessoriesViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}