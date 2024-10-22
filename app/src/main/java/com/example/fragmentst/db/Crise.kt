package com.example.fragmentst.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "crise",
        foreignKeys = [ForeignKey(entity = Utilizador::class, parentColumns = ["idUtilizador"],
            childColumns = ["idUtilizador"], onDelete = ForeignKey.CASCADE)])

data class Crise(
    @PrimaryKey(autoGenerate = true) val idCrise: Int = 0,
    val idUtilizador: Int,
    val data: String?,
    val hora: String?,
    val duracao: String?,
    val ciclo: String?,
    val tipoDeMovimento: String?,
    val localDemovimento: String?,
    val corDaPele: String?,
    val respiracao: String?,
    val estadoDeConsciencia: String?,
    val outrasManifestacoes: String?,
    val possiveisCausas: String? = null,
    val localDaCrise: String?,
    val atividadePraticada: String?,
    val observacoes: String? = null
)
