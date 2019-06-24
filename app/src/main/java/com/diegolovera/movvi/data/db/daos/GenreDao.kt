package com.diegolovera.movvi.data.db.daos

import androidx.room.*
import com.diegolovera.movvi.data.models.Genre

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(genre: Genre): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg genre: Genre)

    @Query("DELETE FROM genre WHERE movieId IN (SELECT id FROM movie WHERE loadType = 1)")
    fun deleteAllPopular()

    @Query("DELETE FROM genre WHERE movieId IN (SELECT id FROM movie WHERE loadType = 2)")
    fun deleteAllTopRated()

    @Query("DELETE FROM genre WHERE movieId IN (SELECT id FROM movie WHERE loadType = 3)")
    fun deleteAllUpcoming()
}