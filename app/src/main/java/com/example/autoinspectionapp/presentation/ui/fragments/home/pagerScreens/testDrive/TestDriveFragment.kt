package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.testDrive

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
import com.example.autoinspectionapp.domain.SpinnerFieldListener
import com.example.autoinspectionapp.domain.models.TestDriveInspectionBo
import com.example.autoinspectionapp.domain.sealed.PagesDataState
import com.example.autoinspectionapp.presentation.dialog.showInputDialogForName
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
        binding?.inputTestDriveDoneBy?.listener = object : SpinnerFieldListener {
            override fun onSpecialItemSelected(item: String) {
                showInputDialogForName(dialogInputCallback = {
                    binding?.inputTestDriveDoneBy?.setItems(
                        listOf(it, "Inspector", "Not Taken", "N/A"),
                        defaultValue = it
                    )
                })
            }
        }
    }

    override fun saveData(pos: Int) {
        "Inspector"
        "Name"
        "Not Taken"

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
                testDriveDoneBy = this.inputTestDriveDoneBy.selectedItem.orEmpty()
            )
            viewModel?.onNext(testDriveInspectionBo = testDriveInspectionBo)
        }
    }

    private fun setupData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataListDataStateFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle).filter { state ->
                    state is PagesDataState.Data<*> && state.section == Section.TEST_DRIVE
                }.collect { state ->
                    when (state) {
                        is PagesDataState.Data<*> -> {
                            val data = state.data as? TestDriveInspectionUI
                            binding?.data = data
                        }

                        else -> Unit
                    }
                }
        }
    }

}