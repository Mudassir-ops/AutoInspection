package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.acHeate

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentAcHeaterBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.ACHeaterFunctionBO
import com.example.commons.base.base.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AcHeaterFragment : Fragment(R.layout.fragment_ac_heater), PagerSaveAble {
    private val binding by viewBinding(FragmentAcHeaterBinding::bind)
    private val viewModel by viewModels<AcHeaterViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
    }

    override fun saveData(pos: Int) {
        Log.e("saveCurrentPageData", "saveCurrentPageData:$pos ")
        binding?.apply {
            val acHeaterFunctionBO = ACHeaterFunctionBO(
                acInstalled = this.inputACInstalled.selectedItem.orEmpty(),
                acFan = this.inputACFan.selectedItem.orEmpty(),
                blowerThrow = this.inputBlowerThrow.selectedItem.orEmpty(),
                acCooling = this.inputACCooling.selectedItem.orEmpty(),
                heater = this.inputHeater.selectedItem.orEmpty()
            )
            viewModel?.onNext(acHeaterFunctionBO = acHeaterFunctionBO)
        }
    }
}