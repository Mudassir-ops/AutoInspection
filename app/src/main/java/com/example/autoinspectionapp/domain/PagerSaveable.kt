package com.example.autoinspectionapp.domain

import android.net.Uri

interface PagerSaveAble {
    fun saveData(pos: Int) {}
    fun setImage(pickedUri: Uri?) {}
}