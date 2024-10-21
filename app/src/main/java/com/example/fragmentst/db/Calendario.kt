package com.example.fragmentst.db

import androidx.room.Entity

import androidx.room.ForeignKey

import androidx.room.PrimaryKey



@Entity(

    tableName = "calendario",

    foreignKeys = [ForeignKey(

        entity = Utilizador::class,
        parentColumns = ["idUtilizador"],
        childColumns = ["idUtilizador"],
        onDelete = ForeignKey.CASCADE

    )]

)

data class Calendario(

    @PrimaryKey(autoGenerate = true) val idCalendario: Int = 0,
    val idUtilizador: Int,
    val evento: String


)
