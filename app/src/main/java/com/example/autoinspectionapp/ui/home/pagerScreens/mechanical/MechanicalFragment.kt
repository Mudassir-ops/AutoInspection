package com.example.autoinspectionapp.ui.home.pagerScreens.mechanical

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentAccidentalChecklistBinding
import com.example.autoinspectionapp.databinding.FragmentAccidentalChecklistBinding.bind
import com.example.autoinspectionapp.databinding.FragmentMechanicalBinding
import com.example.autoinspectionapp.domain.AccidentChecklistBO
import com.example.autoinspectionapp.domain.MechanicalFunctionBO
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.ui.home.pagerScreens.accidentalChecklist.ImageAdapter
import com.example.autoinspectionapp.utils.showImageDialog
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MechanicalFragment : Fragment(R.layout.fragment_mechanical), PagerSaveAble {
    private val viewModel by viewModels<MechanicalViewModel>()
    private val binding by viewBinding(FragmentMechanicalBinding::bind)
    private val imageAdapter: ImageAdapter by lazy {
        ImageAdapter(onAddImageClick = {
            openImagePicker()
        }, onImageClick = {
            showImageDialog(
                imagePath = it,
                deleteImage = {
                    imageAdapter.removeImage(path = it)
                }
            )
        })
    }
    val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imageAdapter.addImage(it.toString())
//            viewModel.uploadImage.set(it)
//            val file = saveUriToCache(context ?: return@let, uri)
//            if (file != null) {
//                val path = file.absolutePath
//                Log.d("FilePath", path)
//                Log.e("pickImageLauncher", ": $uri--$path")
//
//            }
        }
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

    fun openImagePicker() {
        pickImageLauncher.launch("image/*")
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
}