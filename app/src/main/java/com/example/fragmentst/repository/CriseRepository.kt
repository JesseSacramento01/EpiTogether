package com.example.fragmentst.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.fragmentst.db.Crise
import com.example.fragmentst.db.EpiTogetherDB
import com.example.fragmentst.db.dao.CriseDao

class CriseRepository(app: Application) {

    private val criseDao : CriseDao = EpiTogetherDB(app).criseDao()


    // Function to insert data
    suspend fun insertData(crise: Crise) {
        criseDao.insert(crise)
    }

    fun getAllCrises(): LiveData<List<Crise>> {
        return criseDao.getAllCrises()
    }

    suspend fun getCriseById(id: Int): Crise? {
        return criseDao.getCriseById(id)
    }

    suspend fun getCrisesByUtilizadorId(idUtilizador: Int): List<Crise> {
        return criseDao.getCrisesByUtilizadorId(idUtilizador)
    }

    suspend fun deleteCrisesByUtilizadorId(idUtilizador: Int) {
        criseDao.deleteCrisesByUtilizadorId(idUtilizador)
    }
}
