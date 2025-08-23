package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.interior

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentInteriorBinding
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.InteriorControlFunctionBO
import com.example.autoinspectionapp.domain.sealed.SharedAppState
import com.example.autoinspectionapp.presentation.dialog.showImageDialog
import com.example.autoinspectionapp.presentation.ui.fragments.home.HomeFragment
import com.example.commons.base.base.viewBinding
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.tyres.ImageAdapterTyres
import com.example.autoinspectionapp.presentation.ui.fragments.main.MainViewModel
import com.example.autoinspectionapp.utils.imagesdelegate.ImagePickerDelegate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InteriorFragment : Fragment(R.layout.fragment_interior), PagerSaveAble {
    private val viewModel by viewModels<InteriorViewModel>()
    private val binding by viewBinding(FragmentInteriorBinding::bind)
    private var currentAdapter = 1
    private val imageAdapter: ImageAdapterTyres by lazy {
        ImageAdapterTyres(
            adapterId = 1,
            onAddImageClick = {
                currentAdapter = it
                (parentFragment as? HomeFragment)?.showImagePicker()
            }, onImageClick = {
                showImageDialog(
                    imagePath = it,
                    deleteImage = {
                        imageAdapter.removeImage(path = it)
                    }
                )
            })
    }

    private val imageAdapterSecond: ImageAdapterTyres by lazy {
        ImageAdapterTyres(
            adapterId = 2,
            onAddImageClick = {
                currentAdapter = it
                (parentFragment as? HomeFragment)?.showImagePicker()
            }, onImageClick = {
                showImageDialog(
                    imagePath = it,
                    deleteImage = {
                        imageAdapterSecond.removeImage(path = it)
                    }
                )
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding?.rvImages?.run {
            adapter = imageAdapter
            binding?.rvImages?.scrollToPosition(imageAdapter.itemCount - 1)
        }

        binding?.rvImagesSecond?.run {
            adapter = imageAdapterSecond
            binding?.rvImagesSecond?.scrollToPosition(imageAdapter.itemCount - 1)
        }
    }

    override fun saveData(pos: Int) {
        Log.e("saveCurrentPageData", "saveCurrentPageData:$pos ")
        binding?.apply {
            val interiorControlFunctionBO = InteriorControlFunctionBO(
                steeringWheelWearTear = this.inputSteeringWheelWearTear.selectedItem.orEmpty(),
                powerSteering = this.inputPowerSteering.selectedItem.orEmpty(),
                steeringWheelButtons = this.inputSteeringWheelButtons.selectedItem.orEmpty(),
                lightsLeverSwitch = this.inputLightsLeverSwitch.selectedItem.orEmpty(),
                dashboardScratches = this.inputDashboardScratches.selectedItem.orEmpty(),
                dashControlButtons = this.inputDashControlButtons.selectedItem.orEmpty(),
                interiorLights = this.inputInteriorLights.selectedItem.orEmpty(),
                defogger = this.inputDefogger.selectedItem.orEmpty(),
                hazardLights = this.inputHazardLights.selectedItem.orEmpty(),
                multimedia = this.inputMultimedia.selectedItem.orEmpty(),
                rearViewCamera = this.inputRearViewCamera.selectedItem.orEmpty(),
                frontViewCamera = this.inputFrontViewCamera.selectedItem.orEmpty(),
                trunkRelease = this.inputTrunkRelease.selectedItem.orEmpty(),
                doorSkirts = this.inputDoorSkirts.selectedItem.orEmpty(),
                fuelCapReleaseLever = this.inputFuelCapReleaseLever.selectedItem.orEmpty(),
                bonnetReleaseLever = this.inputBonnetReleaseLever.selectedItem.orEmpty(),
                sideViewMirrorAdjustment = this.inputSideViewMirrorAdjustment.selectedItem.orEmpty(),
                leftSideViewMirror = this.inputLeftSideViewMirror.selectedItem.orEmpty(),
                rightSideViewMirror = this.inputRightSideViewMirror.selectedItem.orEmpty(),
                retractingSideViewMirrors = this.inputRetractingSideViewMirrors.selectedItem.orEmpty(),
                acGrills = this.inputACGrills.selectedItem.orEmpty(),
                acceleratorPedal = this.inputAcceleratorPedal.selectedItem.orEmpty(),
                brakePedal = this.inputBrakePedal.selectedItem.orEmpty(),
                clutchPedal = this.inputClutchPedal.selectedItem.orEmpty(),
                sunroof = this.inputSunroof.selectedItem.orEmpty(),
                seatsType = this.inputSeatsType.selectedItem.orEmpty(),
                seatsCondition = this.inputSeatsCondition.selectedItem.orEmpty(),
                driverSeatbelt = this.inputDriverSeatbelt.selectedItem.orEmpty(),
                passengerSeatbelt = this.inputPassengerSeatbelt.selectedItem.orEmpty(),
                windowsType = this.inputWindowsType.selectedItem.orEmpty(),
                frontDriverWindow = this.inputFrontDriverWindow.selectedItem.orEmpty(),
                frontPassengerWindow = this.inputFrontPassengerWindow.selectedItem.orEmpty(),
                rearDriverSideWindow = this.inputRearDriverSideWindow.selectedItem.orEmpty(),
                rearPassengerSideWindow = this.inputRearPassengerSideWindow.selectedItem.orEmpty(),
                windowSafetyLockButton = this.inputWindowSafetyLockButton.selectedItem.orEmpty(),
                centralLocking = this.inputCentralLocking.selectedItem.orEmpty(),
                keyButtons = this.inputKeyButtons.selectedItem.orEmpty(),
                floorMats = this.inputFloorMats.selectedItem.orEmpty(),
                frontDriverDoorSeal = this.inputFrontDriverDoorSeal.selectedItem.orEmpty(),
                frontPassengerDoorSeal = this.inputFrontPassengerDoorSeal.selectedItem.orEmpty(),
                rearDriverSideDoorSeal = this.inputRearDriverSideDoorSeal.selectedItem.orEmpty(),
                rearPassengerSideDoorSeal = this.inputRearPassengerSideDoorSeal.selectedItem.orEmpty(),
                bonnetSeal = this.inputBonnetSeal.selectedItem.orEmpty(),
                trunkSeal = this.inputTrunkSeal.selectedItem.orEmpty()
            )
            viewModel?.onNext(interiorControlFunctionBO = interiorControlFunctionBO)
        }
    }

    override fun setImage(pickedUri: Uri?) {
        when (currentAdapter) {
            1 -> {
                imageAdapter.addImage(pickedUri.toString())
            }

            2 -> {
                imageAdapterSecond.addImage(pickedUri.toString())
            }
        }
    }
}