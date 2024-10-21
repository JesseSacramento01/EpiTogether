package com.example.fragmentst.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fragmentst.db.Autenticacao

@Dao
interface AutenticacaoDao: BaseDao<Autenticacao> {

    @Insert
    suspend fun insertAll(autenticacao: Autenticacao)

    @Query("SELECT * FROM autenticacao")
    suspend fun getAllAutenticacoes(): List<Autenticacao>


    @Query("SELECT * FROM autenticacao WHERE idAutenticacao = :id")
    suspend fun getAutenticacaoById(id: Int): Autenticacao?


    @Query("SELECT * FROM autenticacao WHERE idUtilizador = :idUtilizador")
    suspend fun getAutenticacoesByUtilizadorId(idUtilizador: Int): List<Autenticacao>


    @Query("DELETE FROM autenticacao WHERE dataHoraExpiracao < :currentTime")
    suspend fun deleteExpiredTokens(currentTime: String)
}