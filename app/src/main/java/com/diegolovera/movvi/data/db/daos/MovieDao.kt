package com.diegolovera.movvi.data.db.daos

import androidx.paging.DataSource
import androidx.room.*
import com.diegolovera.movvi.data.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movieDetails: Movie): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg movieDetails: Movie)

    @Query("SELECT * FROM movie WHERE loadType = 1 ORDER BY popularity DESC")
    fun allPopularMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie WHERE loadType = 2 AND voteCount > 50 ORDER BY voteAverage DESC")
    fun allTopRatedMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie WHERE loadType = 3 ORDER BY popularity DESC")
    fun allUpcomingMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT m.* FROM movie m INNER JOIN genre g ON m.id = g.movieId AND m.loadType = g.loadType WHERE g.id IN (:filterValues)")
    fun allMoviesByGenre(filterValues: List<Int>): List<Movie>

    @Query("DELETE FROM movie WHERE loadType = 1")
    fun deleteAllPopular()

    @Query("DELETE FROM movie WHERE loadType = 2")
    fun deleteAllTopRated()

    @Query("DELETE FROM movie WHERE loadType = 3")
    fun deleteAllUpcoming()
}