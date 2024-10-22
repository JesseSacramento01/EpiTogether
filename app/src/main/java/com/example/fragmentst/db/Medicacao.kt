package com.example.fragmentst.db

import androidx.room.Entity

import androidx.room.ForeignKey

import androidx.room.PrimaryKey



@Entity(

    tableName = "medicacao",

    foreignKeys = [ForeignKey(

        entity = Utilizador::class,
        parentColumns = ["idUtilizador"],
        childColumns = ["idUtilizador"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class Medicacao(

    @PrimaryKey(autoGenerate = true) val idMedicacao: Int = 0,
    val idUtilizador: Int,
    val nomeMedicamento: String,
    val dose: String?,
    val dataInicio: String?, // Date (ISO format)
    val dataFim: String?, // Date (ISO format)
    val regime: String?,
    val horario: String?, // Time (ISO format)
    val alarme: Boolean

)
