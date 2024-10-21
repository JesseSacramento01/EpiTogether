package com.example.fragmentst.db


import androidx.room.Entity

import androidx.room.ForeignKey

import androidx.room.PrimaryKey



@Entity(

    tableName = "videocrise",

    foreignKeys = [ForeignKey(
        entity = Crise::class,
        parentColumns = ["idCrise"],
        childColumns = ["idCrise"],
        onDelete = ForeignKey.CASCADE
    )])
data class VideoCrise(

    @PrimaryKey(autoGenerate = true) val idVideo: Int = 0,
    val idCrise: Int,
    val urlVideo: String,
    val dataHoraUpload: String
)

