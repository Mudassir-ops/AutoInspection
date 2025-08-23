package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accessories

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentAccessoriesBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.SparePartsFunctionBO
import com.example.autoinspectionapp.presentation.dialog.showImageDialog
import com.example.autoinspectionapp.presentation.ui.fragments.base.viewBinding
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accidentalChecklist.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccessoriesFragment : Fragment(R.layout.fragment_accessories), PagerSaveAble {
    private val viewModel by viewModels<AccessoriesViewModel>()
    private val binding by viewBinding(FragmentAccessoriesBinding::bind)

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
            val sparePartsFunctionBO = SparePartsFunctionBO(
                spareWheel = this.inputSpareWheel.selectedItem.orEmpty(),
                toolKit = this.inputToolKit.selectedItem.orEmpty(),
                jack = this.inputJack.selectedItem.orEmpty(),
                punctureRepairKit = this.inputPunctureRepairKit.selectedItem.orEmpty()
            )
            viewModel?.onNext(sparePartsFunctionBO = sparePartsFunctionBO)
        }
    }
}