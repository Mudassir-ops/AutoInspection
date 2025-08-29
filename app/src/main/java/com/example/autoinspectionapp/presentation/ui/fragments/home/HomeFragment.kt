package com.example.autoinspectionapp.presentation.ui.fragments.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.FragmentHomeBinding
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.PagerSaveAble
import com.example.autoinspectionapp.domain.sealed.SharedAppState
import com.example.autoinspectionapp.presentation.ui.actvities.CarSchemanticViewActivity
import com.example.autoinspectionapp.presentation.ui.fragments.main.MainViewModel
import com.example.autoinspectionapp.utils.hideLoader
import com.example.autoinspectionapp.utils.imagesdelegate.ImagePickerDelegate
import com.example.autoinspectionapp.utils.menuNavigationMap
import com.example.autoinspectionapp.utils.nextDestinations
import com.example.autoinspectionapp.utils.showLoader
import com.example.commons.base.base.viewBinding
import com.example.commons.extensions.updateButtonState
import com.example.commons.shimmer.ShimmerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private var navController: NavController? = null
    private val viewModel by activityViewModels<MainViewModel>()
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private var shimmerAdapter = ShimmerAdapter(10)
    private lateinit var imagePicker: ImagePickerDelegate

    @Inject
    lateinit var helper: LogsHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeShimmer()
        imagePicker = ImagePickerDelegate(this) { uri, file ->
            binding?.rvShimmer?.adapter = shimmerAdapter
            saveImage(uri = uri)
        }
        val navHost =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment_home) as NavHostFragment

        navHost.childFragmentManager.setFragmentResultListener(
            "pickImage",
            viewLifecycleOwner
        ) { _, _ ->
            showImagePicker()
        }

    }

    private fun setupClickListeners() {
        binding?.apply {
            btnContinue.setOnClickListener {
                viewModel.loadHideShimmer(visibleOrHide = true, R.id.btnContinue)
                saveData()
                navigateNext()

            }
            btnBack.setOnClickListener {
                viewModel.loadHideShimmer(visibleOrHide = true, R.id.btnBack)
                LogsHelper().createLog("setupClickListeners--${navController?.currentDestination?.id}")
                if (navController?.currentDestination?.id == R.id.preliminaryFragment || navController?.currentDestination?.id == null) {
                    findNavController().navigateUp()
                    return@setOnClickListener
                }
                navController?.navigateUp()
            }
            btnMarkSchemantic.setOnClickListener {
                val btnText = btnMarkSchemantic.text.toString()
                if (btnText == context?.getString(R.string.mark_schemantic)) {
                    startActivity(
                        Intent(
                            context ?: return@setOnClickListener,
                            CarSchemanticViewActivity::class.java
                        )
                    )
                } else {
                    findNavController().navigateUp()
                }
            }
            homeMenu.setOnClickListener { view ->
                homeMenu.rotation = 270F
                val popup = PopupMenu(context ?: return@setOnClickListener, view)
                popup.menuInflater.inflate(R.menu.home_menu, popup.menu)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_preliminary_info -> onMenuItemSelected(R.id.menu_preliminary_info)
                        R.id.menu_accidental_checklist -> onMenuItemSelected(R.id.menu_accidental_checklist)
                        R.id.menu_mechanical_function -> onMenuItemSelected(R.id.menu_mechanical_function)
                        R.id.menu_ac_heater -> onMenuItemSelected(R.id.menu_ac_heater)
                        R.id.menu_interior -> onMenuItemSelected(R.id.menu_interior)
                        R.id.menu_electronic -> onMenuItemSelected(R.id.menu_electronic)
                        R.id.menu_suspension -> onMenuItemSelected(R.id.menu_suspension)
                        R.id.menu_exterior -> onMenuItemSelected(R.id.menu_exterior)
                        R.id.menu_tyres -> onMenuItemSelected(R.id.menu_tyres)
                        R.id.menu_accessories -> onMenuItemSelected(R.id.menu_accessories)
                        R.id.menu_test_drive -> onMenuItemSelected(R.id.menu_test_drive)
                        R.id.menu_save_send -> onMenuItemSelected(R.id.menu_save_send)
                        R.id.menu_home -> onMenuItemSelected(R.id.menu_home)
                    }
                    true
                }

                popup.show()
                popup.setOnDismissListener {
                    homeMenu.rotation = 0F
                }
            }

        }
    }

    fun observeShimmer() {
        val btnBGSelectedColor = ContextCompat.getColorStateList(
            context ?: return,
            R.color.tertiary_color
        )

        val btnTextSelectedColor = ContextCompat.getColorStateList(
            context ?: return,
            R.color.legend_black
        )

        val btnBGUnSelectedColor = ContextCompat.getColorStateList(
            context ?: return,
            R.color.text_gray_color
        )

        val btnTextUnSelectedColor = ContextCompat.getColorStateList(
            context ?: return,
            R.color.white
        )

        val btnPrevBGUnSelectedColor = ContextCompat.getColorStateList(
            context ?: return,
            R.color.legend_red
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.appStateFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { shimmerState ->
                    when (shimmerState) {
                        is SharedAppState.ShimmerVisibility -> {
                            if (shimmerState.isShimmer) {
                                showLoader()
                            } else {
                                hideLoader()
                            }
                            when (shimmerState.buttonId) {
                                R.id.btnContinue -> {
                                    binding?.btnContinue?.updateButtonState(
                                        isEnabled = !shimmerState.isShimmer,
                                        backgroundColor = if (shimmerState.isShimmer) {
                                            btnBGSelectedColor
                                        } else {
                                            btnBGUnSelectedColor
                                        },
                                        textColor = if (shimmerState.isShimmer) {
                                            btnTextSelectedColor
                                        } else {
                                            btnTextUnSelectedColor
                                        }
                                    )
                                }

                                R.id.btnBack -> {
                                    binding?.btnBack?.updateButtonState(
                                        isEnabled = !shimmerState.isShimmer,
                                        backgroundColor = if (shimmerState.isShimmer) {
                                            btnBGSelectedColor
                                        } else {
                                            btnPrevBGUnSelectedColor
                                        },
                                        textColor = if (shimmerState.isShimmer) {
                                            btnTextSelectedColor
                                        } else {
                                            btnTextUnSelectedColor
                                        }
                                    )
                                }

                                else -> null
                            }
                        }

                        else -> Unit
                    }
                }
        }
    }

    fun showImagePicker() {
        imagePicker.showPickerDialog()
    }

    private fun navigateNext() {
        LogsHelper().createLog("navigateNext")
        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.nav_host_fragment_home) as? NavHostFragment
            ?: return
        LogsHelper().createLog("navigateNext")
        navController = navHostFragment.navController
        val currentDestId = navController?.currentDestination?.id ?: return
        val nextAction = nextDestinations[currentDestId]
        LogsHelper().createLog("navigateNext$nextAction")
        if (nextAction != null) {
            navController?.navigate(nextAction)
        } else {
            // end of flow
        }
    }

    fun saveData() {
        val currentFragment = getCurrentNavFragment()
        if (currentFragment is PagerSaveAble) {
            currentFragment.saveData(0)
        }
    }

    fun saveImage(uri: Uri?) {
        val currentFragment = getCurrentNavFragment()
        if (currentFragment is PagerSaveAble) {
            currentFragment.setImage(uri)
        }
    }

    fun getCurrentNavFragment(): Fragment? {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment_home) as? NavHostFragment
        return navHostFragment?.childFragmentManager?.fragments?.firstOrNull()
    }

    fun onMenuItemSelected(menuId: Int) {
        if (menuId == R.id.menu_home) {
            findNavController().navigateUp()
            return
        }
        val destinationId = menuNavigationMap[menuId] ?: return
        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.nav_host_fragment_home) as? NavHostFragment
            ?: return
        val navController = navHostFragment.navController
        if (navController.currentDestination?.id != destinationId) {
            // navController.popBackStack(navController.graph.startDestinationId, false)
            navController.navigate(destinationId)
        }
    }
}