package com.example.autoinspectionapp.utils

import android.content.SharedPreferences
import com.example.autoinspectionapp.utils.AppConstants.IS_LOGGED_IN
import javax.inject.Inject
import androidx.core.content.edit

class SessionManager @Inject constructor(private val preferences: SharedPreferences?) {

    fun getIsLoggedIn(): Boolean? {
        return preferences?.getBoolean(
            IS_LOGGED_IN,
            false
        )
    }

    fun setIsLoggedIn(loggedIn: Boolean) {
        preferences?.edit {
            this.putBoolean(IS_LOGGED_IN, loggedIn)
        }
    }
}