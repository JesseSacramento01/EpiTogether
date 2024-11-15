package com.example.fragmentst.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.fragmentst.repository.UtilizadorRepository

class UtilizadorViewModelFactory (
    private val application: Application,
    private val repository: UtilizadorRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UtilizadorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UtilizadorViewModel(application, repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }