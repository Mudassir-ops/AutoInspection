package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.electronic

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
import com.example.autoinspectionapp.databinding.FragmentElectronicBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.ElectricalSafetyFunctionBO
import com.example.autoinspectionapp.domain.sealed.PagesDataState
import com.example.autoinspectionapp.presentation.dialog.showImageDialog
import com.example.autoinspectionapp.presentation.ui.fragments.home.HomeFragment
import com.example.commons.base.base.viewBinding
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accidentalChecklist.ImageAdapter
import com.example.autoinspectionapp.presentation.uimodels.ElectricalSafetyFunctionUI
import com.example.autoinspectionapp.presentation.uimodels.PreliminaryInfoUI
import com.example.autoinspectionapp.utils.enums.Section
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ElectronicFragment : Fragment(R.layout.fragment_electronic), PagerSaveAble {

    private val binding by viewBinding(FragmentElectronicBinding::bind)
    private val viewModel by viewModels<ElectronicViewModel>()
    private val imageAdapter: ImageAdapter by lazy {
        ImageAdapter(onAddImageClick = {
            parentFragmentManager.setFragmentResult("pickImage", bundleOf())
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
        binding?.viewModel = viewModel
        setupRecyclerView()
        setupData()
    }

    private fun setupRecyclerView() {
        binding?.rvImages?.run {
            adapter = imageAdapter
            binding?.rvImages?.scrollToPosition(imageAdapter.itemCount - 1)
        }
    }

    override fun saveData(pos: Int) {
        Log.e("saveCurrentPageData", "saveCurrentPageData:$pos ")
        binding?.apply {
            val electricalSafetyFunctionBO = ElectricalSafetyFunctionBO(
                battery = this.inputBattery.selectedItem.orEmpty(),
                horn = this.inputHorn.selectedItem.orEmpty(),
                rightHeadlightOperation = this.inputRightHeadlightOperation.selectedItem.orEmpty(),
                rightHeadlightCondition = this.inputRightHeadlightCondition.selectedItem.orEmpty(),
                rightHeadlightOriginal = this.inputRightHeadlightOriginal.selectedItem.orEmpty(),
                leftHeadlightOperation = this.inputLeftHeadlightOperation.selectedItem.orEmpty(),
                leftHeadlightCondition = this.inputLeftHeadlightCondition.selectedItem.orEmpty(),
                leftHeadlightOriginal = this.inputLeftHeadlightOriginal.selectedItem.orEmpty(),
                foglights = this.inputFoglights.selectedItem.orEmpty(),
                leftTailLightsOperation = this.inputLeftTailLightsOperation.selectedItem.orEmpty(),
                leftTailLightsCondition = this.inputLeftTailLightsCondition.selectedItem.orEmpty(),
                leftTailLightsOriginal = this.inputLeftTailLightsOriginal.selectedItem.orEmpty(),
                rightTailLightsOperation = this.inputRightTailLightsOperation.selectedItem.orEmpty(),
                rightTailLightsCondition = this.inputRightTailLightsCondition.selectedItem.orEmpty(),
                rightTailLightsOriginal = this.inputRightTailLightsOriginal.selectedItem.orEmpty(),
                windshieldWipers = this.inputWindshieldWipers.selectedItem.orEmpty(),
                airbags = this.inputAirbags.selectedItem.orEmpty(),
                checkLights = this.inputCheckLights.selectedItem.orEmpty()
            )
            viewModel?.onNext(electricalSafetyFunctionBO = electricalSafetyFunctionBO)
        }
    }

    override fun setImage(pickedUri: Uri?) {
        imageAdapter.addImage(pickedUri.toString())
    }

    private fun setupData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataListDataStateFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle).filter { state ->
                    state is PagesDataState.Data<*> && state.section == Section.ELECTRONIC_FUNCTION
                }.collect { state ->
                    when (state) {
                        is PagesDataState.Data<*> -> {
                            val data = state.data as? ElectricalSafetyFunctionUI
                            data?.setViewData()
                        }

                        else -> Unit
                    }
                }
        }
    }

    fun ElectricalSafetyFunctionUI.setViewData() {
        binding?.apply {
            inputBattery.setSelectionByValue(battery)
            inputHorn.setSelectionByValue(horn)
            inputRightHeadlightOperation.setSelectionByValue(rightHeadlightOperation)
            inputRightHeadlightCondition.setSelectionByValue(rightHeadlightCondition)
            inputRightHeadlightOriginal.setSelectionByValue(rightHeadlightOriginal)
            inputLeftHeadlightOperation.setSelectionByValue(leftHeadlightOperation)
            inputLeftHeadlightCondition.setSelectionByValue(leftHeadlightCondition)
            inputLeftHeadlightOriginal.setSelectionByValue(leftHeadlightOriginal)
            inputFoglights.setSelectionByValue(foglights)
            inputLeftTailLightsOperation.setSelectionByValue(leftTailLightsOperation)
            inputLeftTailLightsCondition.setSelectionByValue(leftTailLightsCondition)
            inputLeftTailLightsOriginal.setSelectionByValue(leftTailLightsOriginal)
            inputRightTailLightsOperation.setSelectionByValue(rightTailLightsOperation)
            inputRightTailLightsCondition.setSelectionByValue(rightTailLightsCondition)
            inputRightTailLightsOriginal.setSelectionByValue(rightTailLightsOriginal)
            inputWindshieldWipers.setSelectionByValue(windshieldWipers)
            inputAirbags.setSelectionByValue(airbags)
            inputCheckLights.setSelectionByValue(checkLights)
        }
    }


}