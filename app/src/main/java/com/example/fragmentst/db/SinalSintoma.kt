package com.example.fragmentst.db

import androidx.room.Entity

import androidx.room.ForeignKey

import androidx.room.PrimaryKey



@Entity(
    tableName = "sinalSintoma",

    foreignKeys = [ForeignKey(

        entity = Utilizador::class,
        parentColumns = ["idUtilizador"],
        childColumns = ["idUtilizador"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class SinalSintoma(

    @PrimaryKey(autoGenerate = true) val idSinalSintoma: Int = 0,

    val idUtilizador: Int,
    val dataHora: String, // Timestamp (ISO format)
    val sinaisSintomas: String,
    val possiveisCausas: String?,
    val notas: String?
)
