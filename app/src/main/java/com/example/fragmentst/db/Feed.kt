package com.example.fragmentst.db

import androidx.room.Entity

import androidx.room.ForeignKey

import androidx.room.PrimaryKey



@Entity(

    tableName = "feed",

    foreignKeys = [ForeignKey(

        entity = Utilizador::class,
        parentColumns = ["idUtilizador"],
        childColumns = ["idProfissionalSaude"],
        onDelete = ForeignKey.CASCADE

    )]

)

data class Feed(

    @PrimaryKey(autoGenerate = true) val idPost: Int = 0,

    val idProfissionalSaude: Int,
    val titulo: String,
    val texto: String,
    val link: String?,
    val imagem: String?,
    val dataHoraPublicacao: String // Timestamp (ISO format)

)
