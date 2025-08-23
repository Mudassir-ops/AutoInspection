package com.example.autoinspectionapp.data.local.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.autoinspectionapp.domain.local.repository.SessionManagerRepo
import com.example.autoinspectionapp.utils.AppConstants

class SessionManagerRepoImpl(
    private val preferences: SharedPreferences
) : SessionManagerRepo {

    override fun getIsLoggedIn(): Boolean? {
        return preferences.getBoolean(
            AppConstants.IS_LOGGED_IN,
            false
        )
    }

    override fun setIsLoggedIn(loggedIn: Boolean) {
        preferences.edit {
            this.putBoolean(AppConstants.IS_LOGGED_IN, loggedIn)
        }
    }

}