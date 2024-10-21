package com.example.fragmentst.repository

import android.app.Application
import com.example.fragmentst.db.Autenticacao
import com.example.fragmentst.db.EpiTogetherDB
import com.example.fragmentst.db.dao.AutenticacaoDao

class AutenticacaoRepository(app: Application) {


    private val autenticacaoDao: AutenticacaoDao = EpiTogetherDB(app).autenticacaoDao()

    suspend fun insertAutenticacao(autenticacao: Autenticacao) {
        autenticacaoDao.insertAll(autenticacao)
    }

    suspend fun getAllAutenticacoes(): List<Autenticacao> {
        return autenticacaoDao.getAllAutenticacoes()
    }

    suspend fun getAutenticacaoById(id: Int): Autenticacao? {
        return autenticacaoDao.getAutenticacaoById(id)
    }

    suspend fun getAutenticacoesByUtilizadorId(idUtilizador: Int): List<Autenticacao> {
        return autenticacaoDao.getAutenticacoesByUtilizadorId(idUtilizador)
    }

    suspend fun deleteExpiredTokens(currentTime: String) {
        autenticacaoDao.deleteExpiredTokens(currentTime)
    }
}