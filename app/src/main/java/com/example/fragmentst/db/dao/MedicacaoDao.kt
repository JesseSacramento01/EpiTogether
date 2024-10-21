package com.example.fragmentst.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fragmentst.db.Medicacao

@Dao
interface MedicacaoDao: BaseDao<Medicacao>{

    @Query("SELECT * FROM medicacao")
    suspend fun getAllMedicacoes(): List<Medicacao>

    @Query("SELECT * FROM medicacao WHERE idMedicacao = :id")
    suspend fun getMedicacaoById(id: Int): Medicacao?

    @Query("SELECT * FROM medicacao WHERE idUtilizador = :idUtilizador")
    suspend fun getMedicacoesByUtilizadorId(idUtilizador: Int): List<Medicacao>

    @Query("SELECT * FROM medicacao WHERE dataFim IS NULL OR dataFim > :currentDate")
    suspend fun getActiveMedicacoes(currentDate: String): List<Medicacao>

    @Query("DELETE FROM medicacao WHERE idUtilizador = :idUtilizador")
    suspend fun deleteMedicacoesByUtilizadorId(idUtilizador: Int)
}