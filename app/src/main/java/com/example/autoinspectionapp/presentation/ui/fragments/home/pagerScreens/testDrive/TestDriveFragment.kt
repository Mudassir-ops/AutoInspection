package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.testDrive

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentTestDriveBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.TestDriveInspectionBo
import com.example.autoinspectionapp.domain.sealed.PagesDataState
import com.example.autoinspectionapp.presentation.uimodels.PreliminaryInfoUI
import com.example.autoinspectionapp.presentation.uimodels.TestDriveInspectionUI
import com.example.autoinspectionapp.utils.enums.Section
import com.example.commons.base.base.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TestDriveFragment : Fragment(R.layout.fragment_test_drive), PagerSaveAble {
    private val binding by viewBinding(FragmentTestDriveBinding::bind)
    private val viewModel by viewModels<TestDriveViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        setupData()
    }

    override fun saveData(pos: Int) {
        Log.e("saveCurrentPageData", "saveCurrentPageData:$pos ")
        binding?.apply {
            val testDriveInspectionBo = TestDriveInspectionBo(
                enginePick = this.inputEnginePick.selectedItem.orEmpty(),
                gearShifting = this.inputGearShifting.selectedItem.orEmpty(),
                differentialNoise = this.inputDifferentialNoise.selectedItem.orEmpty(),
                driveShaftNoise = this.inputDriveShaftNoise.selectedItem.orEmpty(),
                absActuation = this.inputAbsActuation.selectedItem.orEmpty(),
                brakePedalOperation = this.inputBrakePedalOperation.selectedItem.orEmpty(),
                frontSuspensionNoise = this.inputFrontSuspensionNoise.selectedItem.orEmpty(),
                rearSuspensionNoise = this.inputRearSuspensionNoise.selectedItem.orEmpty(),
                steeringFunction = this.inputSteeringFunction.selectedItem.orEmpty(),
                steeringWheelAlignment = this.inputSteeringWheelAlignment.selectedItem.orEmpty(),
                speedometerInformationCluster = this.inputSpeedometerInformationCluster.selectedItem.orEmpty(),
                testDriveDoneBy = this.inputTestDriveDoneBy.etInput.text.toString()
            )
            viewModel?.onNext(testDriveInspectionBo = testDriveInspectionBo)
        }
    }

    private fun setupData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataListDataStateFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle).filter { state ->
                    state is PagesDataState.Data<*> && state.section == Section.ELECTRONIC_FUNCTION
                }.collect { state ->
                    when (state) {
                        is PagesDataState.Data<*> -> {
                            val data = state.data as? TestDriveInspectionUI
                            data?.setViewData()
                        }

                        else -> Unit
                    }
                }
        }
    }

    private fun TestDriveInspectionUI.setViewData() {
        binding?.apply {
            inputEnginePick.setSelectionByValue(enginePick)
            inputGearShifting.setSelectionByValue(gearShifting)
            inputDifferentialNoise.setSelectionByValue(differentialNoise)
            inputDriveShaftNoise.setSelectionByValue(driveShaftNoise)
            inputAbsActuation.setSelectionByValue(absActuation)
            inputBrakePedalOperation.setSelectionByValue(brakePedalOperation)
            inputFrontSuspensionNoise.setSelectionByValue(frontSuspensionNoise)
            inputRearSuspensionNoise.setSelectionByValue(rearSuspensionNoise)
            inputSteeringFunction.setSelectionByValue(steeringFunction)
            inputSteeringWheelAlignment.setSelectionByValue(steeringWheelAlignment)
            inputSpeedometerInformationCluster.setSelectionByValue(speedometerInformationCluster)
            inputTestDriveDoneBy.etInput.setText(testDriveDoneBy)
        }
    }


}