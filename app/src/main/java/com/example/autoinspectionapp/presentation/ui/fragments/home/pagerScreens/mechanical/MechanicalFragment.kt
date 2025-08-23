package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.mechanical

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentMechanicalBinding
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.MechanicalFunctionBO
import com.example.autoinspectionapp.domain.sealed.SharedAppState
import com.example.autoinspectionapp.presentation.dialog.showImageDialog
import com.example.autoinspectionapp.presentation.ui.fragments.home.HomeFragment
import com.example.commons.base.base.viewBinding
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accidentalChecklist.ImageAdapter
import com.example.autoinspectionapp.presentation.ui.fragments.main.MainViewModel
import com.example.autoinspectionapp.utils.imagesdelegate.ImagePickerDelegate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MechanicalFragment : Fragment(R.layout.fragment_mechanical), PagerSaveAble {
    private val viewModel by viewModels<MechanicalViewModel>()
    private val binding by viewBinding(FragmentMechanicalBinding::bind)
    private val imageAdapter: ImageAdapter by lazy {
        ImageAdapter(onAddImageClick = {
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
    }

    override fun saveData(pos: Int) {
        Log.e("saveCurrentPageData", "saveCurrentPageData:$pos ")
        binding?.apply {
            val mechanicalFunctionBO = MechanicalFunctionBO(
                engineAbnormalNoise = this.inputEngineAbnormalNoise.selectedItem.orEmpty(),
                enginePick = this.inputEnginePick.selectedItem.orEmpty(),
                engineVibrations = this.inputEngineVibrations.selectedItem.orEmpty(),
                engineSmoke = this.inputEngineSmoke.selectedItem.orEmpty(),
                engineSmokeColor = this.inputEngineSmokeColor.selectedItem.orEmpty(),
                engineBlow = this.inputEngineBlow.selectedItem.orEmpty(),
                engineOilLeakage = this.inputEngineOilLeakage.selectedItem.orEmpty(),
                coolantLeakage = this.inputCoolantLeakage.selectedItem.orEmpty(),
                brakeOilLeakage = this.inputBrakeOilLeakage.selectedItem.orEmpty(),
                transmissionOilLeakage = this.inputTransmissionOilLeakage.selectedItem.orEmpty(),
                catalyticConverter = this.inputCatalyticConverter.selectedItem.orEmpty(),
                exhaustSound = this.inputExhaustSound.selectedItem.orEmpty(),
                radiator = this.inputRadiator.selectedItem.orEmpty(),
                suctionFan = this.inputSuctionFan.selectedItem.orEmpty(),
                gearTransmission = this.inputGearTransmission.selectedItem.orEmpty()
            )
            viewModel?.onNext(mechanicalFunctionBO = mechanicalFunctionBO)
        }
    }

    override fun setImage(pickedUri: Uri?) {
        imageAdapter.addImage(pickedUri.toString())
    }

}