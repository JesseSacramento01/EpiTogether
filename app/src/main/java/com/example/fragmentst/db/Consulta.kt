package com.example.fragmentst.db

import androidx.room.Entity

import androidx.room.ForeignKey

import androidx.room.PrimaryKey



@Entity(

    tableName = "consulta",

    foreignKeys = [ForeignKey(

        entity = Utilizador::class,
        parentColumns = ["idUtilizador"],
        childColumns = ["idUtilizador"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class Consulta(

    @PrimaryKey(autoGenerate = true) val idConsulta: Int = 0,

    val idUtilizador: Int,
    val dataHora: String,
    val tipoProfissional: String?,
    val local: String?,
    val notas: String?
)
