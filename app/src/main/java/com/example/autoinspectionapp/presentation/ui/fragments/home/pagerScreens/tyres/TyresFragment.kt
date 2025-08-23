package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.tyres

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentTyresBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.TyreFunctionBO
import com.example.autoinspectionapp.presentation.dialog.showImageDialog
import com.example.commons.base.base.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TyresFragment : Fragment(R.layout.fragment_tyres), PagerSaveAble {
    private val binding by viewBinding(FragmentTyresBinding::bind)
    private val viewModel by viewModels<TyresViewModel>()
    private var currentAdapter = 1
    private val imageAdapterFrontPassengerTyreSize: ImageAdapterTyres by lazy {
        ImageAdapterTyres(
            adapterId = 1,
            onAddImageClick = {
                currentAdapter = it
                openImagePicker()
            }, onImageClick = {
                showImageDialog(
                    imagePath = it,
                    deleteImage = {
                        imageAdapterFrontPassengerTyreSize.removeImage(path = it)
                    }
                )
            })
    }

    private val imageAdapterFrontDriverTyreCondition: ImageAdapterTyres by lazy {
        ImageAdapterTyres(
            adapterId = 2,
            onAddImageClick = {
                currentAdapter = it
                openImagePicker()
            }, onImageClick = {
                showImageDialog(
                    imagePath = it,
                    deleteImage = {
                        imageAdapterFrontDriverTyreCondition.removeImage(path = it)
                    }
                )
            })
    }

    private val imageAdapterRearPassengerTyreCondition: ImageAdapterTyres by lazy {
        ImageAdapterTyres(
            adapterId = 3,
            onAddImageClick = {
                currentAdapter = it
                openImagePicker()
            }, onImageClick = {
                showImageDialog(
                    imagePath = it,
                    deleteImage = {
                        imageAdapterRearPassengerTyreCondition.removeImage(path = it)
                    }
                )
            })
    }

    val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            when (currentAdapter) {
                1 -> {
                    imageAdapterFrontPassengerTyreSize.addImage(it.toString())
                }

                2 -> {
                    imageAdapterFrontDriverTyreCondition.addImage(it.toString())
                }

                3 -> {
                    imageAdapterRearPassengerTyreCondition.addImage(it.toString())
                }
            }


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
        binding?.rvFrontPassengerTyreSize?.run {
            adapter = imageAdapterFrontPassengerTyreSize
            binding?.rvFrontPassengerTyreSize?.scrollToPosition(imageAdapterFrontPassengerTyreSize.itemCount - 1)
        }

        binding?.rvFrontDriverTyreCondition?.run {
            adapter = imageAdapterFrontDriverTyreCondition
            binding?.rvFrontDriverTyreCondition?.scrollToPosition(
                imageAdapterFrontDriverTyreCondition.itemCount - 1
            )
        }

        binding?.rvRearPassengerTyreCondition?.run {
            adapter = imageAdapterRearPassengerTyreCondition
            binding?.rvRearPassengerTyreCondition?.scrollToPosition(
                imageAdapterRearPassengerTyreCondition.itemCount - 1
            )
        }

    }

    fun openImagePicker() {
        pickImageLauncher.launch("image/*")
    }

    override fun saveData(pos: Int) {
        Log.e("saveCurrentPageData", "saveCurrentPageData:$pos ")
        binding?.apply {
            val tyreFunctionBO = TyreFunctionBO(
                frontPassengerTyreBrand = this.inputFrontPassengerTyreBrand.etInput.text.toString(),
                frontPassengerTyreSize = this.inputFrontPassengerTyreSize.etInput.text.toString(),
                frontPassengerTyreCondition = this.inputFrontPassengerTyreCondition.selectedItem.orEmpty(),
                frontDriverTyreBrand = this.inputFrontDriverTyreBrand.etInput.text.toString(),
                frontDriverTyreSize = this.inputFrontDriverTyreSize.etInput.text.toString(),
                frontDriverTyreCondition = this.inputFrontDriverTyreCondition.selectedItem.orEmpty(),
                rearPassengerTyreBrand = this.inputRearPassengerTyreBrand.etInput.text.toString(),
                rearPassengerTyreSize = this.inputRearPassengerTyreSize.etInput.text.toString(),
                rearPassengerTyreCondition = this.inputRearPassengerTyreCondition.selectedItem.orEmpty(),
                rearDriverTyreBrand = this.inputRearDriverTyreBrand.etInput.text.toString(),
                rearDriverTyreSize = this.inputRearDriverTyreSize.etInput.text.toString(),
                rearDriverTyreCondition = this.inputRearDriverTyreCondition.selectedItem.orEmpty(),
                alloyRims = this.inputAlloyRims.etInput.text.toString()
            )
            viewModel?.onNext(tyreFunctionBO = tyreFunctionBO)
        }
    }

}