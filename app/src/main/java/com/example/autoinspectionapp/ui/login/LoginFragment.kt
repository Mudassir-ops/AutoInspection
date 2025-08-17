package com.example.autoinspectionapp.ui.login

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentLoginBinding
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.sealed.LoginState
import com.example.autoinspectionapp.domain.uimodels.LoginUi
import com.example.autoinspectionapp.hideLoader
import com.example.autoinspectionapp.safeNav
import com.example.autoinspectionapp.setCustomRipple
import com.example.autoinspectionapp.showLoader
import com.example.autoinspectionapp.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewModel by viewModels<LoginViewModel>()
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private var isExpanded = false
    private var fullSerial = "SN1234.."

    @Inject
    lateinit var logsHelper: LogsHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLoginState()
        clickListeners()
        viewModel.getDeviceSerialNumber(context ?: return)
    }

    fun clickListeners() {
        binding?.apply {
            btnLogin.setCustomRipple(
                rippleColor = ContextCompat.getColor(context ?: return@apply, R.color.myRippleColor)
            ) {
                login()
            }
            motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(
                    motionLayout: MotionLayout,
                    startId: Int,
                    endId: Int
                ) {
                }

                override fun onTransitionChange(
                    motionLayout: MotionLayout,
                    startId: Int,
                    endId: Int,
                    progress: Float
                ) {
                }

                override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                    isExpanded = currentId == R.id.expanded
                    if (isExpanded) {
                        tvSerial.text = fullSerial
                        imgExpand.setImageResource(R.drawable.ic_forward)
                        imgExpand.rotation = 180F
                        tvSerialDetails.text = fullSerial
                    } else {
                        tvSerial.text = fullSerial.take(5)
                        imgExpand.setImageResource(R.drawable.ic_forward)
                        imgExpand.rotation = 0F
                    }
                }

                override fun onTransitionTrigger(
                    motionLayout: MotionLayout,
                    triggerId: Int,
                    positive: Boolean,
                    progress: Float
                ) {
                }
            })
        }
    }

    fun login() {
        viewModel.run {
            LoginUi(
                loginEmail = binding?.edTextMail?.text?.trim().toString(),
                loginPwd = binding?.edTextPwd?.text?.trim().toString(),
                serialNumber = fullSerial
            ).onLogin()
        }
    }

    fun observeLoginState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                when (it) {
                    LoginState.Init -> Unit
                    is LoginState.DeviceSerialNumber -> {
                        binding?.apply {
                            fullSerial = it.serialNumber
                        }
                    }

                    is LoginState.Error -> {

                    }

                    LoginState.Success -> {
                        logsHelper.createLog("Success")
                        hideLoader()
                        findNavController().safeNav(
                            R.id.navigation_login,
                            R.id.action_navigation_login_to_navigation_main
                        )
                    }

                    LoginState.Loading -> {
                        logsHelper.createLog("Loading")
                        showLoader()
                    }
                }
            }
        }
    }
}