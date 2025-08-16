package com.example.autoinspectionapp.ui.home.pagerScreens.carDrawing

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentCarDrawingBinding
import com.example.autoinspectionapp.databinding.FragmentPreliminaryBinding
import com.example.autoinspectionapp.databinding.FragmentPreliminaryBinding.bind
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.ui.home.pagerScreens.preliminary.PreliminaryViewModel
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("SourceLockedOrientationActivity")
@AndroidEntryPoint
class CarDrawingFragment : Fragment(R.layout.fragment_car_drawing) {

    private val binding by viewBinding(FragmentCarDrawingBinding::bind)
    private val viewModel by viewModels<CarDrawingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

        }
    }

    override fun onResume() {
        super.onResume()
        activity?.requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    override fun onPause() {
        super.onPause()
        activity?.requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

}