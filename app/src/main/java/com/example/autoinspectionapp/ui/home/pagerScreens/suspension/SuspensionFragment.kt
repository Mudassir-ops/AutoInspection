package com.example.autoinspectionapp.ui.home.pagerScreens.suspension

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentMechanicalBinding
import com.example.autoinspectionapp.databinding.FragmentMechanicalBinding.bind
import com.example.autoinspectionapp.databinding.FragmentSuspensionBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.SuspensionSteeringFunctionBO
import com.example.autoinspectionapp.ui.home.pagerScreens.accidentalChecklist.ImageAdapter
import com.example.autoinspectionapp.utils.showImageDialog
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuspensionFragment : Fragment(R.layout.fragment_suspension), PagerSaveAble {
    private val binding by viewBinding(FragmentSuspensionBinding::bind)
    private val viewModel by viewModels<SuspensionViewModel>()
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
            val suspensionSteeringFunctionBO = SuspensionSteeringFunctionBO(
                steeringAssemblyPlay = this.inputSteeringAssemblyPlay.selectedItem.orEmpty(),
                axleBoots = this.inputAxleBoots.selectedItem.orEmpty(),
                rightBallJoint = this.inputRightBallJoint.selectedItem.orEmpty(),
                leftBallJoint = this.inputLeftBallJoint.selectedItem.orEmpty(),
                tieRodEnd = this.inputTieRodEnd.selectedItem.orEmpty(),
                rightBoot = this.inputRightBoot.selectedItem.orEmpty(),
                leftBoot = this.inputLeftBoot.selectedItem.orEmpty(),
                rightBush = this.inputRightBush.selectedItem.orEmpty(),
                leftBush = this.inputLeftBush.selectedItem.orEmpty(),
                rearRightShockAbsorber = this.inputRearRightShockAbsorber.selectedItem.orEmpty(),
                rearLeftShockAbsorber = this.inputRearLeftShockAbsorber.selectedItem.orEmpty(),
                frontRightShockAbsorber = this.inputFrontRightShockAbsorber.selectedItem.orEmpty(),
                frontLeftShockAbsorber = this.inputFrontLeftShockAbsorber.selectedItem.orEmpty()
            )
            viewModel?.onNext(suspensionSteeringFunctionBO = suspensionSteeringFunctionBO)
        }
    }
}