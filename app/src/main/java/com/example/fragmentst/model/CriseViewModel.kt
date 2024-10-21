package com.example.fragmentst.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragmentst.db.Crise
import com.example.fragmentst.repository.CriseRepository
import kotlinx.coroutines.launch

class CriseViewModel(private val repository: CriseRepository) : ViewModel() {

    fun getAllCrises(): List<Crise> {
        var crisesList: List<Crise> = listOf()
        viewModelScope.launch {
            crisesList = repository.getAllCrises()
        }
        return crisesList
    }

    suspend fun getCriseById(id: Int): Crise? {
        return repository.getCriseById(id)
    }

    suspend fun getCrisesByUtilizadorId(idUtilizador: Int): List<Crise> {
        return repository.getCrisesByUtilizadorId(idUtilizador)
    }

    fun deleteCrisesByUtilizadorId(idUtilizador: Int) {
        viewModelScope.launch {
            repository.deleteCrisesByUtilizadorId(idUtilizador)
        }
    }
}