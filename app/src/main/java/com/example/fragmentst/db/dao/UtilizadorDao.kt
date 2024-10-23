package com.example.fragmentst.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fragmentst.db.Utilizador
import kotlinx.coroutines.flow.Flow

@Dao
interface UtilizadorDao: BaseDao<Utilizador> {

    @Query("SELECT * FROM utilizador")
    fun getAllUtilizadores(): Flow<List<Utilizador>>

    @Query("SELECT * FROM utilizador WHERE idUtilizador = :id")
    suspend fun getUtilizadorById(id: Int): Utilizador?

    @Query("SELECT * FROM utilizador WHERE email = :email LIMIT 1")
    suspend fun getUtilizadorByEmail(email: String): Utilizador?

    @Query("DELETE FROM utilizador")
    suspend fun deleteAllUtilizadores()

    @Query("SELECT * FROM utilizador WHERE tipoDeUtilizador = :tipo")
    suspend fun getUtilizadoresByTipo(tipo: String): List<Utilizador>

    @Query("SELECT COUNT(*) FROM utilizador WHERE email = :email")
    suspend fun isEmailInUse(email: String): Int
}