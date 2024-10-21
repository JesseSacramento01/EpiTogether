package com.example.fragmentst.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey



@Entity(tableName = "autenticacao",
        foreignKeys = [ForeignKey(
        entity = Utilizador::class,
        parentColumns = ["idUtilizador"],
        childColumns = ["idUtilizador"],
        onDelete = ForeignKey.CASCADE
    )])

data class Autenticacao(

    @PrimaryKey(autoGenerate = true) val idAutenticacao: Int = 0,

    val idUtilizador: Int,
    val token: String,
    val dataHoraExpiracao: String // Timestamp (ISO format)
)
