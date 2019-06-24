package com.diegolovera.movvi.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.diegolovera.movvi.data.models.MovieDetails
import com.diegolovera.movvi.data.models.MovieDetailsRelation

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movieDetails: MovieDetails): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg movieDetails: MovieDetails)

    @Transaction
    @Query("SELECT * FROM movie_details WHERE id = :id")
    fun getDaysAndSubjectsFromUser(id: Long): LiveData<MovieDetailsRelation>

    @Query("DELETE FROM movie_details WHERE id IN (SELECT id FROM movie WHERE loadType = 1)")
    fun deleteAllPopular()

    @Query("DELETE FROM movie_details WHERE id IN (SELECT id FROM movie WHERE loadType = 2)")
    fun deleteAllTopRated()

    @Query("DELETE FROM movie_details WHERE id IN (SELECT id FROM movie WHERE loadType = 3)")
    fun deleteAllUpcoming()
}