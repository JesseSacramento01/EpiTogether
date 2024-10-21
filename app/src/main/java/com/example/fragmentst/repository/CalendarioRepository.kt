package com.example.fragmentst.repository

import android.app.Application
import com.example.fragmentst.db.Calendario
import com.example.fragmentst.db.EpiTogetherDB
import com.example.fragmentst.db.dao.CalendarioDao

class CalendarioRepository(app: Application) {

    private val calendarioDao: CalendarioDao = EpiTogetherDB(app).calendarioDao()

    // Insert multiple eventos
    suspend fun insertEventos(vararg calendario: Calendario) {
        calendarioDao.insertAll(*calendario)
    }

    // Get all eventos
    suspend fun getAllEventos(): List<Calendario> {
        return calendarioDao.getAllEventos()
    }

    // Get evento by ID
    suspend fun getEventoById(id: Int): Calendario? {
        return calendarioDao.getEventoById(id)
    }

    // Get eventos by user ID
    suspend fun getEventosByUtilizadorId(idUtilizador: Int): List<Calendario> {
        return calendarioDao.getEventosByUtilizadorId(idUtilizador)
    }

    // Delete eventos by user ID
    suspend fun deleteEventosByUtilizadorId(idUtilizador: Int) {
        calendarioDao.deleteEventosByUtilizadorId(idUtilizador)
    }
}
