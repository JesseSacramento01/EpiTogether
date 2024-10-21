package com.example.fragmentst.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fragmentst.db.Consulta


@Dao
interface ConsultaDao: BaseDao<Consulta> {

        @Query("SELECT * FROM consulta")
        suspend fun getAllConsultas(): List<Consulta>

        @Query("SELECT * FROM consulta WHERE idConsulta = :id")
        suspend fun getConsultaById(id: Int): Consulta?

        @Query("SELECT * FROM consulta WHERE idUtilizador = :idUtilizador")
        suspend fun getConsultasByUtilizadorId(idUtilizador: Int): List<Consulta>

        @Query("SELECT * FROM consulta WHERE dataHora = :dataHora")
        suspend fun getConsultasByDataHora(dataHora: String): List<Consulta>

        @Query("DELETE FROM consulta WHERE idUtilizador = :idUtilizador")
        suspend fun deleteConsultasByUtilizadorId(idUtilizador: Int)
    }