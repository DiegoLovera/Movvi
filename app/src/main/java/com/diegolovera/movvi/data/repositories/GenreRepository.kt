package com.diegolovera.movvi.data.repositories

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.diegolovera.movvi.api.TheMovieApiClient
import com.diegolovera.movvi.api.responses.GetGenresResponse
import com.diegolovera.movvi.data.db.MovviRoomDatabase
import com.diegolovera.movvi.data.db.daos.MovieDao
import com.diegolovera.movvi.data.db.daos.UniqueGenreDao
import com.diegolovera.movvi.data.models.Movie
import com.diegolovera.movvi.data.models.MovieDetails
import com.diegolovera.movvi.data.models.UniqueGenre
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreRepository(context: Application) {
    private val mUniqueGenreDao: UniqueGenreDao
    private val mMovieDao: MovieDao

    init {
        val db = MovviRoomDatabase.getInstance(context)
        mUniqueGenreDao = db.uniqueGenreDao()
        mMovieDao = db.movieDao()
    }

    fun getAllGenres(): LiveData<List<UniqueGenre>> {
        TheMovieApiClient.service.getGenres(TheMovieApiClient.API_KEY).enqueue(object : Callback<GetGenresResponse> {
            override fun onFailure(call: Call<GetGenresResponse>, t: Throwable) {
                // do nothing
            }

            override fun onResponse(call: Call<GetGenresResponse>, response: Response<GetGenresResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()!!.results.forEach {
                        upsert(it)
                    }
                }
            }

        })
        return mUniqueGenreDao.selectDistinct()
    }

    private fun upsert(entity: UniqueGenre) {
        AsyncTask.execute {
            val id = insert(entity)
            if (id == -1L) {
                update(entity)
            }
        }
    }

    private fun insert(movieDetails: UniqueGenre): Long {
        return mUniqueGenreDao.insert(movieDetails)
    }

    private fun update(vararg movieDetails: UniqueGenre) {
        mUniqueGenreDao.update(*movieDetails)
    }

    fun getMoviesByGenre(filterValues: List<Int>): List<Movie> {
        return mMovieDao.allMoviesByGenre(filterValues)
    }

    companion object {
        private var mInstance: GenreRepository? = null

        fun getInstance(context: Application): GenreRepository {
            if (mInstance == null) {
                mInstance = GenreRepository(context)
            }
            return mInstance as GenreRepository
        }
    }
}