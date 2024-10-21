package com.example.fragmentst.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.fragmentst.db.Autenticacao
import com.example.fragmentst.db.Crise

interface BaseDao<T> {
        @Insert
        suspend fun insert(data: T) : Long

        @Update
        suspend fun update(data: T)

        @Delete
        suspend fun delete(data: T)

        @Insert
        suspend fun insertAll(vararg data: T)
}