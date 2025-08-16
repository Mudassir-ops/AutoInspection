package com.example.autoinspectionapp.ui.home.pagerScreens.accidentalChecklist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentAccidentalChecklistBinding
import com.example.autoinspectionapp.domain.AccidentChecklistBO
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.utils.showImageDialog
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccidentalChecklistFragment : Fragment(R.layout.fragment_accidental_checklist),
    PagerSaveAble {
    private val binding by viewBinding(FragmentAccidentalChecklistBinding::bind)
    private val viewModel by viewModels<AccidentalChecklistViewModel>()
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
        binding?.rvAccidentalImages?.run {
            adapter = imageAdapter
            binding?.rvAccidentalImages?.scrollToPosition(imageAdapter.itemCount - 1)
        }
    }

    fun openImagePicker() {
        pickImageLauncher.launch("image/*")
    }

    override fun saveData(pos: Int) {
        Log.e("saveCurrentPageData", "saveCurrentPageData:$pos ")
        binding?.apply {
            val accidentChecklistBO = AccidentChecklistBO(
                engineRoomFirewall = this.inputEngineRoomFirewall.getSelectedItem().orEmpty(),
                rightStrutTower = this.inputRightStrutTower.getSelectedItem().orEmpty(),
                leftStrutTower = this.inputLeftStrutTower.getSelectedItem().orEmpty(),
                rightFrontRail = this.inputRightFrontRail.getSelectedItem().orEmpty(),
                leftFrontRail = this.inputLeftFrontRail.getSelectedItem().orEmpty(),
                frontBumperSupport = this.inputFrontBumperSupport.getSelectedItem().orEmpty(),
                rearCoreSupport = this.inputRearCoreSupport.getSelectedItem().orEmpty(),
                radiatorCoreSupport = this.inputRadiatorCoreSupport.getSelectedItem().orEmpty(),
                rightAPillar = this.inputRightAPillar.getSelectedItem().orEmpty(),
                leftAPillar = this.inputLeftAPillar.getSelectedItem().orEmpty(),
                rightBPillar = this.inputRightBPillar.getSelectedItem().orEmpty(),
                leftBPillar = this.inputLeftBPillar.getSelectedItem().orEmpty(),
                rightCPillar = this.inputRightCPillar.getSelectedItem().orEmpty(),
                leftCPillar = this.inputLeftCPillar.getSelectedItem().orEmpty(),
                rightDPillar = this.inputRightDPillar.getSelectedItem().orEmpty(),
                leftDPillar = this.inputLeftDPillar.getSelectedItem().orEmpty(),
                bootFloor = this.inputBootFloor.getSelectedItem().orEmpty(),
                frontUnderbody = this.inputFrontUnderbody.getSelectedItem().orEmpty(),
                rearUnderbody = this.inputRearUnderbody.getSelectedItem().orEmpty()
            )
            viewModel?.onNext(accidentChecklistBO = accidentChecklistBO)
        }
    }

}
