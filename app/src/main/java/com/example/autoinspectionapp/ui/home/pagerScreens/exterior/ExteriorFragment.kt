package com.example.autoinspectionapp.ui.home.pagerScreens.exterior

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentExteriorBinding
import com.example.autoinspectionapp.domain.BodyStructureFunctionBO
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExteriorFragment : Fragment(R.layout.fragment_exterior), PagerSaveAble {
    private val binding by viewBinding(FragmentExteriorBinding::bind)
    private val viewModel by viewModels<ExteriorViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
    }

    override fun saveData(pos: Int) {
        Log.e("saveCurrentPageData", "saveCurrentPageData:$pos ")
        binding?.apply {
            val bodyStructureFunctionBO = BodyStructureFunctionBO(
                trunkLock = this.inputTrunkLock.selectedItem.orEmpty(),
                frontDriverFender = this.inputFrontDriverFender.etInput.text.toString(),
                bonnet = this.inputBonnet.etInput.text.toString(),
                frontWindshield = this.inputFrontWindshield.etInput.text.toString(),
                frontPassengerFender = this.inputFrontPassengerFender.etInput.text.toString(),
                frontPassengerDoor = this.inputFrontPassengerDoor.etInput.text.toString(),
                rearPassengerDoor = this.inputRearPassengerDoor.etInput.text.toString(),
                rearPassengerFender = this.inputRearPassengerFender.etInput.text.toString(),
                trunk = this.inputTrunk.etInput.text.toString(),
                rearWindshield = this.inputRearWindshield.etInput.text.toString(),
                rearDriverFender = this.inputRearDriverFender.etInput.text.toString(),
                rearDriverDoor = this.inputRearDriverDoor.etInput.text.toString(),
                frontDriverDoor = this.inputFrontDriverDoor.etInput.text.toString(),
                roof = this.inputRoof.etInput.text.toString(),
                driverAPillar = this.inputDriverAPillar.etInput.text.toString(),
                driverBPillar = this.inputDriverBPillar.etInput.text.toString(),
                driverCPillar = this.inputDriverCPillar.etInput.text.toString(),
                driverDPillar = this.inputDriverDPillar.etInput.text.toString(),
                passengerAPillar = this.inputPassengerAPillar.etInput.text.toString(),
                passengerBPillar = this.inputPassengerBPillar.etInput.text.toString(),
                passengerCPillar = this.inputPassengerCPillar.etInput.text.toString(),
                passengerDPillar = this.inputPassengerDPillar.etInput.text.toString()
            )
            viewModel?.onNext(bodyStructureFunctionBO = bodyStructureFunctionBO)
        }
    }
}