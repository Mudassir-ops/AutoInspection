package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accidentalChecklist

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentAccidentalChecklistBinding
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.models.AccidentChecklistBO
import com.example.autoinspectionapp.domain.sealed.SharedAppState
import com.example.autoinspectionapp.presentation.dialog.showImageDialog
import com.example.autoinspectionapp.presentation.ui.fragments.home.HomeFragment
import com.example.commons.base.base.viewBinding
import com.example.autoinspectionapp.presentation.ui.fragments.main.MainViewModel
import com.example.autoinspectionapp.utils.imagesdelegate.ImagePickerDelegate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AccidentalChecklistFragment : Fragment(R.layout.fragment_accidental_checklist),
    PagerSaveAble {
    private val binding by viewBinding(FragmentAccidentalChecklistBinding::bind)
    private val viewModel by viewModels<AccidentalChecklistViewModel>()
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
        binding?.rvAccidentalImages?.run {
            adapter = imageAdapter
            binding?.rvAccidentalImages?.scrollToPosition(imageAdapter.itemCount - 1)
        }
    }

    override fun saveData(pos: Int) {
        Log.e("saveCurrentPageData", "saveCurrentPageData:$pos ")
        binding?.apply {
            val accidentChecklistBO = AccidentChecklistBO(
                engineRoomFirewall = this.inputEngineRoomFirewall.selectedItem.orEmpty(),
                rightStrutTower = this.inputRightStrutTower.selectedItem.orEmpty(),
                leftStrutTower = this.inputLeftStrutTower.selectedItem.orEmpty(),
                rightFrontRail = this.inputRightFrontRail.selectedItem.orEmpty(),
                leftFrontRail = this.inputLeftFrontRail.selectedItem.orEmpty(),
                frontBumperSupport = this.inputFrontBumperSupport.selectedItem.orEmpty(),
                rearCoreSupport = this.inputRearCoreSupport.selectedItem.orEmpty(),
                radiatorCoreSupport = this.inputRadiatorCoreSupport.selectedItem.orEmpty(),
                rightAPillar = this.inputRightAPillar.selectedItem.orEmpty(),
                leftAPillar = this.inputLeftAPillar.selectedItem.orEmpty(),
                rightBPillar = this.inputRightBPillar.selectedItem.orEmpty(),
                leftBPillar = this.inputLeftBPillar.selectedItem.orEmpty(),
                rightCPillar = this.inputRightCPillar.selectedItem.orEmpty(),
                leftCPillar = this.inputLeftCPillar.selectedItem.orEmpty(),
                rightDPillar = this.inputRightDPillar.selectedItem.orEmpty(),
                leftDPillar = this.inputLeftDPillar.selectedItem.orEmpty(),
                bootFloor = this.inputBootFloor.selectedItem.orEmpty(),
                frontUnderbody = this.inputFrontUnderbody.selectedItem.orEmpty(),
                rearUnderbody = this.inputRearUnderbody.selectedItem.orEmpty()
            )
            viewModel?.onNext(accidentChecklistBO = accidentChecklistBO)
        }
    }

    override fun setImage(pickedUri: Uri?) {
        imageAdapter.addImage(pickedUri.toString())
    }

}
