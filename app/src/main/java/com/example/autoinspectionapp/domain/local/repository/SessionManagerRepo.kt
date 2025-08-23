package com.example.autoinspectionapp.domain.local.repository

interface SessionManagerRepo {

    fun getIsLoggedIn(): Boolean?
    fun setIsLoggedIn(loggedIn: Boolean)

}