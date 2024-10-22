package com.example.fragmentst.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "utilizador")


data class Utilizador(
    @PrimaryKey(autoGenerate = true) val idUtilizador: Int = 0,
    val nome: String,
    val email: String,
    val password: String,
    val tipoDeUtilizador: String,
    val dataDeNascimento: String?,
    val genero: String?,
    val telefone: String?,
    val endereco: String?
)


