package com.example.autoinspectionapp.ui.empty

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentEmptyBinding
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmptyFragment : Fragment(R.layout.fragment_empty) {
    private val viewModel by viewModels<EmptyViewModel>()
    private val binding by viewBinding(FragmentEmptyBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}