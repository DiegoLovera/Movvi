package com.diegolovera.movvi.data.repositories

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.diegolovera.movvi.api.TheMovieApiClient
import com.diegolovera.movvi.data.db.MovviRoomDatabase
import com.diegolovera.movvi.data.db.daos.GenreDao
import com.diegolovera.movvi.data.db.daos.MovieDetailsDao
import com.diegolovera.movvi.data.db.daos.ProductionCountryDao
import com.diegolovera.movvi.data.models.MovieDetails
import com.diegolovera.movvi.data.models.MovieDetailsRelation

class MovieDetailRepository(context: Application) {
    private val mMovieDetailsDao: MovieDetailsDao
    private val mGenreDao: GenreDao
    private val mProductionCountryDao: ProductionCountryDao

    init {
        val db = MovviRoomDatabase.getInstance(context)
        mMovieDetailsDao = db.movieDetailsDao()
        mGenreDao = db.genreDao()
        mProductionCountryDao = db.productionCountryDao()
    }

    fun getMovieDetails(movieId: Long): LiveData<MovieDetailsRelation> {
        val dbResponse = mMovieDetailsDao.getDaysAndSubjectsFromUser(movieId)
        AsyncTask.execute {
            val movieDetails = TheMovieApiClient.getMovieDetails(movieId)
            if (movieDetails != null) {
                upsert(movieDetails)
                movieDetails.genres.forEach{
                    it.movieId = movieDetails.id
                    val id = mGenreDao.insert(it)
                    if (id == -1L) {
                        mGenreDao.update(it)
                    }
                }
                movieDetails.productionCountries.forEach {
                    it.movieId = movieDetails.id
                    val id = mProductionCountryDao.insert(it)
                    if (id == -1L) {
                        mProductionCountryDao.update(it)
                    }
                }
            }
        }
        return dbResponse
    }

    private fun upsert(entity: MovieDetails) {
        val id = insert(entity)
        if (id == -1L) {
            update(entity)
        }
    }

    private fun insert(movieDetails: MovieDetails): Long {
        return mMovieDetailsDao.insert(movieDetails)
    }

    private fun update(vararg movieDetails: MovieDetails) {
        mMovieDetailsDao.update(*movieDetails)
    }

    companion object {
        private var mInstance: MovieDetailRepository? = null

        fun getInstance(context: Application): MovieDetailRepository {
            if (mInstance == null) {
                mInstance = MovieDetailRepository(context)
            }
            return mInstance as MovieDetailRepository
        }
    }
}