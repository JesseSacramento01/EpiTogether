package com.example.fragmentst.repository

import android.app.Application
import com.example.fragmentst.db.Crise
import com.example.fragmentst.db.EpiTogetherDB
import com.example.fragmentst.db.Utilizador
import com.example.fragmentst.db.dao.CriseDao
import com.example.fragmentst.db.dao.UtilizadorDao
import kotlinx.coroutines.flow.Flow

class UtilizadorRepository(app: Application) {
    private val utilizadorDao: UtilizadorDao = EpiTogetherDB(app).utilizadoorDao()


    // Function to insert data
    suspend fun insertData(utilizador: Utilizador) {
        utilizadorDao.insert(utilizador)
    }

    fun getAllUtilizadores(): Flow<List<Utilizador>> {
        return utilizadorDao.getAllUtilizadores()
    }

    suspend fun getUtilizadorById(id: Int): Utilizador? {
        return utilizadorDao.getUtilizadorById(id)
    }

}