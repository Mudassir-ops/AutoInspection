package com.example.autoinspectionapp.ui.home.pagerScreens.mechanical

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MechanicalFragment : Fragment(R.layout.fragment_mechanical) {
    private val viewModel by viewModels<MechanicalViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//    override fun onResume() {
//        super.onResume()
//        activity?.requestedOrientation =
//            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//    }
//
//    override fun onPause() {
//        super.onPause()
//        activity?.requestedOrientation =
//            ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
//    }
}