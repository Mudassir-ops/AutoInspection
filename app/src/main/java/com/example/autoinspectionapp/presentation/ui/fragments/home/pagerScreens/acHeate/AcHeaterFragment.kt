package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.acHeate

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentAcHeaterBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.ACHeaterFunctionBO
import com.example.autoinspectionapp.domain.sealed.PagesDataState
import com.example.autoinspectionapp.presentation.uimodels.ACHeaterFunctionUI
import com.example.autoinspectionapp.presentation.uimodels.PreliminaryInfoUI
import com.example.autoinspectionapp.utils.enums.Section
import com.example.commons.base.base.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AcHeaterFragment : Fragment(R.layout.fragment_ac_heater), PagerSaveAble {
    private val binding by viewBinding(FragmentAcHeaterBinding::bind)
    private val viewModel by viewModels<AcHeaterViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        setupData()
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

    private fun setupData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.acHeaterListDataStateFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle).filter { state ->
                    state is PagesDataState.Data<*> && state.section == Section.AC_HEATER_OPERATION
                }.collect { state ->
                    when (state) {
                        is PagesDataState.Data<*> -> {
                            val data = state.data as? ACHeaterFunctionUI
                            data?.setViewData()
                        }

                        else -> Unit
                    }
                }
        }
    }

    fun ACHeaterFunctionUI.setViewData() {
        binding?.apply {
            inputACInstalled.setSelectionByValue(acInstalled)
            inputACFan.setSelectionByValue(acFan)
            inputBlowerThrow.setSelectionByValue(blowerThrow)
            inputACCooling.setSelectionByValue(acCooling)
            inputHeater.setSelectionByValue(heater)
        }
    }


}