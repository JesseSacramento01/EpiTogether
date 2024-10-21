package com.example.fragmentst.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fragmentst.db.Permissao

@Dao
interface PermissaoDao: BaseDao<Permissao>{

    @Query("SELECT * FROM permissao")
    suspend fun getAllPermissoes(): List<Permissao>

    @Query("SELECT * FROM permissao WHERE idPermissao = :id")
    suspend fun getPermissaoById(id: Int): Permissao?

    @Query("SELECT * FROM permissao WHERE idProfissionalEducacao = :idProfissionalEducacao")
    suspend fun getPermissoesByProfissionalEducacaoId(idProfissionalEducacao: Int): List<Permissao>

    @Query("SELECT * FROM permissao WHERE idAdolescente = :idAdolescente")
    suspend fun getPermissoesByAdolescenteId(idAdolescente: Int): List<Permissao>

    @Query("DELETE FROM permissao WHERE idProfissionalEducacao = :idProfissionalEducacao")
    suspend fun deletePermissoesByProfissionalEducacaoId(idProfissionalEducacao: Int)

    @Query("DELETE FROM permissao WHERE idAdolescente = :idAdolescente")
    suspend fun deletePermissoesByAdolescenteId(idAdolescente: Int)
}