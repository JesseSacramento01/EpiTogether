package com.example.fragmentst.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fragmentst.db.Crise

@Dao
interface CriseDao: BaseDao<Crise> {

        @Query("SELECT * FROM crise")
        suspend fun getAllCrises(): List<Crise>

        @Query("SELECT * FROM crise WHERE idCrise = :id")
        suspend fun getCriseById(id: Int): Crise?

        @Query("SELECT * FROM crise WHERE idUtilizador = :idUtilizador")
        suspend fun getCrisesByUtilizadorId(idUtilizador: Int): List<Crise>

        @Query("DELETE FROM crise WHERE idUtilizador = :idUtilizador")
        suspend fun deleteCrisesByUtilizadorId(idUtilizador: Int)
    }
