package com.example.autoinspectionapp.presentation.ui.actvities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.ActivityAutoInspectionMainBinding
import com.example.autoinspectionapp.presentation.ui.actvities.base.BaseActivity
import com.example.autoinspectionapp.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AutoInspectionMainActivity : BaseActivity() {

    private val binding by lazy {
        ActivityAutoInspectionMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupStartDestination()
    }

    fun setupStartDestination() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main222) as? NavHostFragment
        val navController = navHostFragment?.navController
        val navGraph = navController?.navInflater?.inflate(R.navigation.mobile_navigation)

        val startDestination = if (sessionManager.getIsLoggedIn() == true) {
            R.id.navigation_main
        } else {
            R.id.navigation_login
        }
        navGraph?.setStartDestination(startDestination)
        navGraph?.let { navController.setGraph(it, null) }

    }
}