package com.example.fragmentst.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fragmentst.db.Calendario

@Dao
interface CalendarioDao: BaseDao<Calendario> {

    @Query("SELECT * FROM calendario")
    suspend fun getAllEventos(): List<Calendario>

    @Query("SELECT * FROM calendario WHERE idCalendario = :id")
    suspend fun getEventoById(id: Int): Calendario?

    @Query("SELECT * FROM calendario WHERE idUtilizador = :idUtilizador")
    suspend fun getEventosByUtilizadorId(idUtilizador: Int): List<Calendario>

    @Query("DELETE FROM calendario WHERE idUtilizador = :idUtilizador")
    suspend fun deleteEventosByUtilizadorId(idUtilizador: Int)
}