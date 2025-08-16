package com.example.autoinspectionapp.ui.home.pagerScreens.accidentalChecklist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentAccidentalChecklistBinding
import com.example.autoinspectionapp.databinding.FragmentPreliminaryBinding
import com.example.autoinspectionapp.databinding.FragmentPreliminaryBinding.bind
import com.example.autoinspectionapp.ui.home.pagerScreens.preliminary.PreliminaryViewModel
import com.example.autoinspectionapp.utils.showImageDialog
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import saveUriToCache


@AndroidEntryPoint
class AccidentalChecklistFragment : Fragment(R.layout.fragment_accidental_checklist) {
    private val binding by viewBinding(FragmentAccidentalChecklistBinding::bind)
    private val viewModel by viewModels<AccidentalChecklistViewModel>()
    private val imageAdapter: ImageAdapter by lazy {
        ImageAdapter(onAddImageClick = {
            openImagePicker()
        }, onImageClick = {
            showImageDialog(imagePath = it)
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
            scrollToPosition(imageAdapter.itemCount - 1)
        }
    }

    fun openImagePicker() {
        pickImageLauncher.launch("image/*")
    }

}
