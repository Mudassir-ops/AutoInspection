package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.preliminary

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentPreliminaryBinding
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.PreliminaryInfoBO
import com.example.autoinspectionapp.presentation.ui.fragments.home.HomeFragment
import com.example.commons.base.base.viewBinding
import com.example.commons.extensions.showDatePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreliminaryFragment : Fragment(R.layout.fragment_preliminary), PagerSaveAble {
    private val binding by viewBinding(FragmentPreliminaryBinding::bind)
    private val viewModel by viewModels<PreliminaryViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        clickListeners()
    }

    private fun clickListeners() {
        binding?.apply {
            ivUploadImage.setOnClickListener {
                (parentFragment as? HomeFragment)?.showImagePicker()
            }
            inputInspectionDate.etInput.apply {
                isFocusable = false
                isFocusableInTouchMode = false
                setOnClickListener {
                    it.showDatePicker { selectedDate ->
                        inputInspectionDate.etInput.setText(selectedDate)
                    }
                }
            }
        }
    }

    override fun saveData(pos: Int) {
        Log.e("saveCurrentPageData", "saveCurrentPageData:$pos ")
        binding?.apply {
            val preliminaryInfoBO = PreliminaryInfoBO(
                clientName = this.inputClientName.etInput.text.toString(),
                inspectionDate = this.inputInspectionDate.etInput.text.toString(),
                vehicleMake = this.inputVehicleMake.etInput.text.toString(),
                vehicleModel = this.inputVehicleModel.etInput.text.toString(),
                vehicleVariant = this.inputVehicleVariant.etInput.text.toString(),
                modelYear = this.inputModelYear.etInput.text.toString(),
                transmission = this.inputTransmission.etInput.text.toString(),
                engineCapacity = this.inputEngineCapacity.etInput.text.toString(),
                fuelType = this.inputFuelType.etInput.text.toString(),
                bodyColor = this.inputBodyColor.etInput.text.toString(),
                mileage = this.inputMileage.etInput.text.toString(),
                registrationNumber = this.inputRegistrationNumber.etInput.text.toString(),
                registeredRegion = this.inputRegisteredRegion.etInput.text.toString(),
                chassisNumber = this.inputChassisNumber.etInput.text.toString(),
                engineNumber = this.inputEngineNumber.etInput.text.toString(),
                inspectionLocation = this.inputInspectionLocation.etInput.text.toString()
            )
            viewModel?.onNext(preliminaryInfoBO = preliminaryInfoBO)
        }
    }

    override fun setImage(pickedUri: Uri?) {
        LogsHelper().createLog("setImage$pickedUri")
        viewModel.uploadImage.set(pickedUri.toString())
    }
}