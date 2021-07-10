package com.example.iot.feature.ui

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.iot.feature.data.GuardRepository

class GuardProfileViewModelFactory(
    private val repository: GuardRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) :
    AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(GuardProfileViewModel::class.java)) {
            return GuardProfileViewModel(
                repository,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}