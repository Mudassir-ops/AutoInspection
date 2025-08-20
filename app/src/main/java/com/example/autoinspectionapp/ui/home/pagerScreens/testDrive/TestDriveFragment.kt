package com.example.autoinspectionapp.ui.home.pagerScreens.testDrive

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentAccessoriesBinding
import com.example.autoinspectionapp.databinding.FragmentAccessoriesBinding.bind
import com.example.autoinspectionapp.databinding.FragmentTestDriveBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.SparePartsFunctionBO
import com.example.autoinspectionapp.domain.TestDriveInspectionBo
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestDriveFragment : Fragment(R.layout.fragment_test_drive), PagerSaveAble {
    private val binding by viewBinding(FragmentTestDriveBinding::bind)
    private val viewModel by viewModels<TestDriveViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
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
}