package com.diegolovera.movvi.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.diegolovera.movvi.data.models.Genre

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(genre: Genre): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg genre: Genre)
}