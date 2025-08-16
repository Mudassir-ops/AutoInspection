package com.example.autoinspectionapp.ui.home.pagerScreens.acHeate

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentAcHeaterBinding
import com.example.autoinspectionapp.databinding.FragmentMechanicalBinding
import com.example.autoinspectionapp.databinding.FragmentMechanicalBinding.bind
import com.example.autoinspectionapp.domain.ACHeaterFunctionBO
import com.example.autoinspectionapp.domain.MechanicalFunctionBO
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.ui.home.pagerScreens.accidentalChecklist.ImageAdapter
import com.example.autoinspectionapp.utils.showImageDialog
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AcHeaterFragment : Fragment(R.layout.fragment_ac_heater), PagerSaveAble {
    private val binding by viewBinding(FragmentAcHeaterBinding::bind)
    private val viewModel by viewModels<AcHeaterViewModel>()
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
            val acHeaterFunctionBO = ACHeaterFunctionBO(
                acInstalled = this.inputACInstalled.selectedItem.orEmpty(),
                acFan = this.inputACFan.selectedItem.orEmpty(),
                blowerThrow = this.inputBlowerThrow.selectedItem.orEmpty(),
                acCooling = this.inputACCooling.selectedItem.orEmpty(),
                heater = this.inputHeater.selectedItem.orEmpty()
            )
            viewModel?.onNext(acHeaterFunctionBO = acHeaterFunctionBO)
        }
    }
}