package com.example.fragmentst.db

import androidx.room.Entity

import androidx.room.ForeignKey

import androidx.room.PrimaryKey



@Entity(

    tableName = "permissao",

    foreignKeys = [

        ForeignKey(

            entity = Utilizador::class,
            parentColumns = ["idUtilizador"],
            childColumns = ["idProfissionalEducacao"],
            onDelete = ForeignKey.CASCADE

        ),

        ForeignKey(

            entity = Utilizador::class,
            parentColumns = ["idUtilizador"],
            childColumns = ["idAdolescente"],
            onDelete = ForeignKey.CASCADE

        )

    ]

)

data class Permissao(

    @PrimaryKey(autoGenerate = true)
    val idPermissao: Int = 0,

    val idProfissionalEducacao: Int,
    val idAdolescente: Int,
    val permissaoVisualizacao: Boolean

)
