package com.diegolovera.movvi.data.repositories

import android.app.Application
import android.os.AsyncTask
import androidx.paging.DataSource
import com.diegolovera.movvi.api.TheMovieApiClient
import com.diegolovera.movvi.api.responses.GetMoviesResponse
import com.diegolovera.movvi.data.MovieBoundaryCallback
import com.diegolovera.movvi.data.db.MovviRoomDatabase
import com.diegolovera.movvi.data.db.daos.MovieDao
import com.diegolovera.movvi.data.models.Movie
import com.diegolovera.movvi.utils.PageUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(context: Application) {
    private val mMovieDao: MovieDao

    init {
        val db = MovviRoomDatabase.getInstance(context)
        mMovieDao = db.movieDao()
    }

    fun refresh() {
        //getPopularMovies(1)
        //getTopRatedMovies(1)
        //getUpcomingMovies(1)
        //PageUtils.resetPages()
    }

    fun getPopularMovies(page: Int) {
        TheMovieApiClient.service.getPopularMovies(TheMovieApiClient.API_KEY, page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {

                }

                override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                    if (response.code() == 200) {
                        val data = response.body()
                        upsert(data!!.results, MovieBoundaryCallback.LoadType.POPULAR)
                    }
                }
            })
    }

    fun getTopRatedMovies(page: Int) {
        TheMovieApiClient.service.getTopRatedMovies(TheMovieApiClient.API_KEY, page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {

                }

                override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                    if (response.code() == 200) {
                        val data = response.body()
                        upsert(data!!.results, MovieBoundaryCallback.LoadType.TOP_RATED)
                    }
                }
            })
    }

    fun getUpcomingMovies(page: Int) {
        TheMovieApiClient.service.getUpcomingMovies(TheMovieApiClient.API_KEY, page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {

                }

                override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                    if (response.code() == 200) {
                        val data = response.body()

                        upsert(data!!.results, MovieBoundaryCallback.LoadType.UPCOMING)
                    }
                }
            })
    }

    fun upsert(movies: List<Movie>, loadType: MovieBoundaryCallback.LoadType) {
        AsyncTask.execute {
            movies.forEach {
                it.loadType = loadType.value
                val id = mMovieDao.insert(it)
                if (id == -1L) {
                    mMovieDao.update(it)
                }
            }
            mMovieDao.insertMovies(movies)
        }
    }

    fun deleteAllPopular() {
        mMovieDao.deleteAllPopular()
    }

    fun deleteAllTopRated() {
        mMovieDao.deleteAllTopRated()
    }

    fun deleteAllUpcoming() {
        mMovieDao.deleteAllUpcoming()
    }

    fun allPopularMovies(): DataSource.Factory<Int, Movie> = mMovieDao.allPopularMovies()
    fun allTopRatedMovies(): DataSource.Factory<Int, Movie> = mMovieDao.allTopRatedMovies()
    fun allUpcomingMovies(): DataSource.Factory<Int, Movie> = mMovieDao.allUpcomingMovies()

    companion object {
        private var mInstance: MovieRepository? = null

        fun getInstance(context: Application): MovieRepository {
            if (mInstance == null) {
                mInstance = MovieRepository(context)
            }
            return mInstance as MovieRepository
        }
    }
}