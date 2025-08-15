package com.example.autoinspectionapp.ui.home.pagerScreens.accidentalChecklist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccidentalChecklistFragment : Fragment(R.layout.fragment_accidental_checklist) {
    private val viewModel by viewModels<AccidentalChecklistViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
