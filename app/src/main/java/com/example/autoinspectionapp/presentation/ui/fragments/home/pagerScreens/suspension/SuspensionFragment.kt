package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.suspension

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
import com.example.autoinspectionapp.databinding.FragmentSuspensionBinding
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.SuspensionSteeringFunctionBO
import com.example.autoinspectionapp.domain.sealed.PagesDataState
import com.example.autoinspectionapp.presentation.dialog.showImageDialog
import com.example.autoinspectionapp.presentation.ui.fragments.home.HomeFragment
import com.example.commons.base.base.viewBinding
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accidentalChecklist.ImageAdapter
import com.example.autoinspectionapp.presentation.uimodels.PreliminaryInfoUI
import com.example.autoinspectionapp.presentation.uimodels.SuspensionSteeringFunctionUI
import com.example.autoinspectionapp.utils.enums.Section
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SuspensionFragment : Fragment(R.layout.fragment_suspension), PagerSaveAble {
    private val binding by viewBinding(FragmentSuspensionBinding::bind)
    private val viewModel by viewModels<SuspensionViewModel>()
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

    override fun setImage(pickedUri: Uri?) {
        imageAdapter.addImage(pickedUri.toString())
    }

    private fun setupData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataListDataStateFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle).filter { state ->
                    state is PagesDataState.Data<*> && state.section == Section.SUSPENSION_FUNCTION
                }.collect { state ->
                    when (state) {
                        is PagesDataState.Data<*> -> {
                            val data = state.data as? SuspensionSteeringFunctionUI
                            data?.setViewData()
                        }

                        else -> Unit
                    }
                }
        }
    }

    fun SuspensionSteeringFunctionUI.setViewData() = binding?.apply {
        inputSteeringAssemblyPlay.setSelectionByValue(steeringAssemblyPlay)
        inputAxleBoots.setSelectionByValue(axleBoots)
        inputRightBallJoint.setSelectionByValue(rightBallJoint)
        inputLeftBallJoint.setSelectionByValue(leftBallJoint)
        inputTieRodEnd.setSelectionByValue(tieRodEnd)
        inputRightBoot.setSelectionByValue(rightBoot)
        inputLeftBoot.setSelectionByValue(leftBoot)
        inputRightBush.setSelectionByValue(rightBush)
        inputLeftBush.setSelectionByValue(leftBush)
        inputRearRightShockAbsorber.setSelectionByValue(rearRightShockAbsorber)
        inputRearLeftShockAbsorber.setSelectionByValue(rearLeftShockAbsorber)
        inputFrontRightShockAbsorber.setSelectionByValue(frontRightShockAbsorber)
        inputFrontLeftShockAbsorber.setSelectionByValue(frontLeftShockAbsorber)
    }


}