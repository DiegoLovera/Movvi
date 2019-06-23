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
}