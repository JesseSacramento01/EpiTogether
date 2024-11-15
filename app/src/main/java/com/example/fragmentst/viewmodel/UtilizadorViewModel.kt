package com.example.fragmentst.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragmentst.db.Utilizador
import com.example.fragmentst.repository.UtilizadorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UtilizadorViewModel(app: Application, private var repository: UtilizadorRepository) : AndroidViewModel(app) {



    val utilizadorListFlow: Flow<List<Utilizador>> = repository.getAllUtilizadores()

    suspend fun insertData(utilizador: Utilizador) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(utilizador)
        }
    }

    suspend fun getUtilizadorById(id: Int): Utilizador? {
        return repository.getUtilizadorById(id)
    }

}