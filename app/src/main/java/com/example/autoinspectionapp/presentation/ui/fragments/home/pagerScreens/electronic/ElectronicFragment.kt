package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.electronic

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentElectronicBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.ElectricalSafetyFunctionBO
import com.example.autoinspectionapp.presentation.dialog.showImageDialog
import com.example.autoinspectionapp.presentation.ui.fragments.home.HomeFragment
import com.example.commons.base.base.viewBinding
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accidentalChecklist.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElectronicFragment : Fragment(R.layout.fragment_electronic), PagerSaveAble {

    private val binding by viewBinding(FragmentElectronicBinding::bind)
    private val viewModel by viewModels<ElectronicViewModel>()
    private val imageAdapter: ImageAdapter by lazy {
        ImageAdapter(onAddImageClick = {
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
        binding?.viewModel = viewModel
        setupRecyclerView()
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
}