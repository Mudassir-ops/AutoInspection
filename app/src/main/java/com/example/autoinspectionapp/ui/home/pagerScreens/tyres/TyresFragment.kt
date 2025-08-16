package com.example.autoinspectionapp.ui.home.pagerScreens.tyres

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TyresFragment : Fragment(R.layout.fragment_tyres) {
    private val viewModel by viewModels<TyresViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}