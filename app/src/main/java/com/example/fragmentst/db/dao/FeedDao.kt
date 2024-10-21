package com.example.fragmentst.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fragmentst.db.Feed

@Dao
interface FeedDao: BaseDao<Feed> {

    @Query("SELECT * FROM feed")
    suspend fun getAllPosts(): List<Feed>

    @Query("SELECT * FROM feed WHERE idPost = :id")
    suspend fun getPostById(id: Int): Feed?

    @Query("SELECT * FROM feed WHERE titulo LIKE 'Epilepsia' || :search || '%'")
    suspend fun searchPostsByTitle(search: String): List<Feed>
}