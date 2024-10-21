package com.example.fragmentst.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fragmentst.db.dao.AutenticacaoDao
import com.example.fragmentst.db.dao.CalendarioDao
import com.example.fragmentst.db.dao.ConsultaDao
import com.example.fragmentst.db.dao.CriseDao
import com.example.fragmentst.db.dao.FeedDao
import com.example.fragmentst.db.dao.MedicacaoDao
import com.example.fragmentst.db.dao.PermissaoDao
import com.example.fragmentst.db.dao.SinalSintomaDao
import com.example.fragmentst.db.dao.UtilizadorDao
import com.example.fragmentst.db.dao.VideoCriseDao

@Database(entities = [Autenticacao::class, Calendario::class, Consulta::class, Crise::class, Feed::class, Medicacao::class, Permissao::class,
                     SinalSintoma::class, Utilizador::class, VideoCrise::class], version = 1)
abstract class EpiTogetherDB : RoomDatabase()  {

    abstract fun autenticacaoDao() : AutenticacaoDao
    abstract fun calendarioDao() : CalendarioDao
    abstract fun consultaDao() : ConsultaDao
    abstract fun criseDao() : CriseDao
    abstract fun feedDao() : FeedDao
    abstract fun medicacaoDao() : MedicacaoDao
    abstract fun permissaoDao() : PermissaoDao
    abstract fun sinalSintomaDao() : SinalSintomaDao
    abstract fun utilizadoorDao() : UtilizadorDao
    abstract fun videoCriseDao() : VideoCriseDao

    companion object {
        @Volatile private var instance: EpiTogetherDB? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context.applicationContext).also { instance = it}}


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, EpiTogetherDB::class.java, "epiTogetherDB.db")
                //.fallbackToDestructiveMigration()
                .build()
    }
}