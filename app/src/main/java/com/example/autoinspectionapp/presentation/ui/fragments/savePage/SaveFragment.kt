package com.example.autoinspectionapp.presentation.ui.fragments.savePage

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentSaveBinding
import com.example.autoinspectionapp.presentation.dialog.showImageDialog
import com.example.commons.base.base.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveFragment : Fragment(R.layout.fragment_save) {
    private val binding by viewBinding(FragmentSaveBinding::bind)
    private val viewModel by viewModels<SaveViewModel>()
    private val imageAdapter: ImageAdapterSaveAndSend by lazy {
        ImageAdapterSaveAndSend(onAddImageClick = {
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
        setupRecyclerView()
        clickListeners()
    }

    private fun clickListeners() {
        binding?.apply {
            btnSaveAndSend.setOnClickListener {

            }
        }
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

}