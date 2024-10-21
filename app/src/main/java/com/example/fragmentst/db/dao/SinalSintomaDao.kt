package com.example.fragmentst.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fragmentst.db.SinalSintoma

@Dao
interface SinalSintomaDao : BaseDao<SinalSintoma> {

    @Query("SELECT * FROM sinalSintoma")
    suspend fun getAllSinalSintomas(): List<SinalSintoma>

    @Query("SELECT * FROM sinalSintoma WHERE idSinalSintoma = :id")
    suspend fun getSinalSintomaById(id: Int): SinalSintoma?

    @Query("SELECT * FROM sinalSintoma WHERE idUtilizador = :idUtilizador")
    suspend fun getSinalSintomasByUtilizadorId(idUtilizador: Int): List<SinalSintoma>

    @Query("DELETE FROM sinalSintoma WHERE idUtilizador = :idUtilizador")
    suspend fun deleteSinalSintomasByUtilizadorId(idUtilizador: Int)
}