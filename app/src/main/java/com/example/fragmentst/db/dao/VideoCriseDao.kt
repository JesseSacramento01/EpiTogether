package com.example.fragmentst.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fragmentst.db.VideoCrise

@Dao
interface VideoCriseDao : BaseDao<VideoCrise> {

    @Query("SELECT * FROM videocrise")
    suspend fun getAllVideos(): List<VideoCrise>

    @Query("SELECT * FROM videocrise WHERE idVideo = :id")
    suspend fun getVideoById(id: Int): VideoCrise?

    @Query("SELECT * FROM videocrise WHERE idCrise = :idCrise")
    suspend fun getVideosByCriseId(idCrise: Int): List<VideoCrise>

    @Query("DELETE FROM videocrise WHERE idCrise = :idCrise")
    suspend fun deleteVideosByCriseId(idCrise: Int)
}