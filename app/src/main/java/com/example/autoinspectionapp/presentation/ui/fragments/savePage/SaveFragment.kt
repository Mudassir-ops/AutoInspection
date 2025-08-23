package com.example.autoinspectionapp.presentation.ui.fragments.savePage

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentSaveBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.presentation.dialog.showImageDialog
import com.example.autoinspectionapp.presentation.ui.fragments.home.HomeFragment
import com.example.commons.base.base.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveFragment : Fragment(R.layout.fragment_save), PagerSaveAble {
    private val binding by viewBinding(FragmentSaveBinding::bind)
    private val viewModel by viewModels<SaveViewModel>()
    private val imageAdapter: ImageAdapterSaveAndSend by lazy {
        ImageAdapterSaveAndSend(onAddImageClick = {
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

    override fun setImage(pickedUri: Uri?) {
        imageAdapter.addImage(pickedUri.toString())
    }
}