package com.example.autoinspectionapp.ui.home.pagerScreens.preliminary

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentPreliminaryBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.PreliminaryInfoBO
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import safeNav
import saveUriToCache
import showDatePicker

@AndroidEntryPoint
class PreliminaryFragment : Fragment(R.layout.fragment_preliminary), PagerSaveAble {
    private val binding by viewBinding(FragmentPreliminaryBinding::bind)
    private val viewModel by viewModels<PreliminaryViewModel>()
    val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            viewModel.uploadImage.set(it)
            val file = saveUriToCache(context ?: return@let, uri)
            if (file != null) {
                val path = file.absolutePath
                Log.d("FilePath", path)
                Log.e("pickImageLauncher", ": $uri--$path")

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        clickListeners()
    }

    private fun clickListeners() {
        binding?.apply {
            ivUploadImage.setOnClickListener {
                pickImageLauncher.launch("image/*")
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

    override fun saveData() {
        Log.e("saveCurrentPageData", "saveCurrentPageData: ")
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
}