package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.tyres

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentTyresBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.TyreFunctionBO
import com.example.autoinspectionapp.domain.sealed.PagesDataState
import com.example.autoinspectionapp.presentation.dialog.showImageDialog
import com.example.autoinspectionapp.presentation.ui.fragments.home.HomeFragment
import com.example.autoinspectionapp.presentation.uimodels.PreliminaryInfoUI
import com.example.autoinspectionapp.presentation.uimodels.TyreFunctionUI
import com.example.autoinspectionapp.utils.enums.Section
import com.example.commons.base.base.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

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
                parentFragmentManager.setFragmentResult("pickImage", bundleOf())
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
                parentFragmentManager.setFragmentResult("pickImage", bundleOf())
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
                parentFragmentManager.setFragmentResult("pickImage", bundleOf())
            }, onImageClick = {
                showImageDialog(
                    imagePath = it,
                    deleteImage = {
                        imageAdapterRearPassengerTyreCondition.removeImage(path = it)
                    }
                )
            })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        setupRecyclerView()
        setupData()
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
                alloyRims = this.inputAlloyRims.selectedItem.orEmpty()
            )
            viewModel?.onNext(tyreFunctionBO = tyreFunctionBO)
        }
    }

    override fun setImage(pickedUri: Uri?) {
        when (currentAdapter) {
            1 -> {
                imageAdapterFrontPassengerTyreSize.addImage(pickedUri.toString())
            }

            2 -> {
                imageAdapterFrontDriverTyreCondition.addImage(pickedUri.toString())
            }

            3 -> {
                imageAdapterRearPassengerTyreCondition.addImage(pickedUri.toString())
            }
        }
    }

    private fun setupData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataListDataStateFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle).filter { state ->
                    state is PagesDataState.Data<*> && state.section == Section.TYRES
                }.collect { state ->
                    when (state) {
                        is PagesDataState.Data<*> -> {
                            val data = state.data as? TyreFunctionUI
                            data?.setViewData()
                        }

                        else -> Unit
                    }
                }
        }
    }

    fun TyreFunctionUI.setViewData() = binding?.apply {
        inputFrontPassengerTyreBrand.etInput.setText(frontPassengerTyreBrand)
        inputFrontPassengerTyreSize.etInput.setText(frontPassengerTyreSize)
        inputFrontPassengerTyreCondition.setSelectionByValue(frontPassengerTyreCondition)
        inputFrontDriverTyreBrand.etInput.setText(frontDriverTyreBrand)
        inputFrontDriverTyreSize.etInput.setText(frontDriverTyreSize)
        inputFrontDriverTyreCondition.setSelectionByValue(frontDriverTyreCondition)
        inputRearPassengerTyreBrand.etInput.setText(rearPassengerTyreBrand)
        inputRearPassengerTyreSize.etInput.setText(rearPassengerTyreSize)
        inputRearPassengerTyreCondition.setSelectionByValue(rearPassengerTyreCondition)
        inputRearDriverTyreBrand.etInput.setText(rearDriverTyreBrand)
        inputRearDriverTyreSize.etInput.setText(rearDriverTyreSize)
        inputRearDriverTyreCondition.setSelectionByValue(rearDriverTyreCondition)
        inputAlloyRims.setSelectionByValue(alloyRims)
    }

}