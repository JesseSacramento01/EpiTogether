package com.example.fragmentst.model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentst.repository.CriseRepository

class CriseViewModelFactory(
    private val application: Application,
    private val repository: CriseRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CriseViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CriseViewModel(application, repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }