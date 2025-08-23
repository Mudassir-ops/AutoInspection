package com.example.autoinspectionapp.ui.home.pagerScreens.accidentalChecklist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.autoinspectionapp.PagerReadyListener
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentAccidentalChecklistBinding
import com.example.autoinspectionapp.domain.AccidentChecklistBO
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.ui.home.HomeFragment
import com.example.autoinspectionapp.ui.main.MainViewModel
import com.example.autoinspectionapp.utils.Section
import com.example.autoinspectionapp.utils.showImageDialog
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue


@AndroidEntryPoint
class AccidentalChecklistFragment : Fragment(R.layout.fragment_accidental_checklist),
    PagerSaveAble {
    private val binding by viewBinding(FragmentAccidentalChecklistBinding::bind)
    private val viewModel by viewModels<AccidentalChecklistViewModel>()
    private val mainViewmodel by activityViewModels<MainViewModel>()
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
        binding?.rvAccidentalImages?.run {
            adapter = imageAdapter
            binding?.rvAccidentalImages?.scrollToPosition(imageAdapter.itemCount - 1)
        }
    }

    fun openImagePicker() {
        pickImageLauncher.launch("image/*")
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

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        LogsHelper().createLog("setMenuVisibility$menuVisible")
    }
}
