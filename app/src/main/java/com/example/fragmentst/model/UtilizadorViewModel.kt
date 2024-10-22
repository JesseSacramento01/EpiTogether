package com.example.fragmentst.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragmentst.db.Utilizador
import com.example.fragmentst.repository.UtilizadorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UtilizadorViewModel(app: Application, private var repository: UtilizadorRepository) : AndroidViewModel(app) {


    fun insertData(utilizador: Utilizador) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(utilizador)
        }
    }

    fun getAllUtilizadores(): List<Utilizador> {
        var utilizadorList: List<Utilizador> = listOf()
        viewModelScope.launch {
            utilizadorList = repository.getAllUtilizadores()
        }
        return utilizadorList
    }

    suspend fun getUtilizadorById(id: Int): Utilizador? {
        return repository.getUtilizadorById(id)
    }

}