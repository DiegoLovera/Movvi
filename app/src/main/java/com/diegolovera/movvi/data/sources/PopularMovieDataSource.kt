package com.diegolovera.movvi.data.sources

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.diegolovera.movvi.api.TheMovieApiClient
import com.diegolovera.movvi.api.responses.GetMoviesResponse
import com.diegolovera.movvi.data.models.Movie
import com.diegolovera.movvi.data.repositories.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMovieDataSource(val context: Application): PageKeyedDataSource<Int, Movie>() {
    private val TAG = PopularMovieDataSource::class.java.simpleName

    companion object {
        private const val FIRST_PAGE = 1
        const val PAGE_SIZE = 20
    }

    init {

    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        /*callback.onResult(MovieRepository.getInstance(context)
            .getMovies(FIRST_PAGE), null, FIRST_PAGE + 1)*/
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TheMovieApiClient.service
            .getPopularMovies(TheMovieApiClient.API_KEY, (params.key))
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.d(TAG, t.message)
                }

                override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                    if (response.isSuccessful && response.code() == 200) {
                        val data = response.body()
                        if (data != null) {
                            callback.onResult(data.results, params.key + 1)
                        }
                        response.body()!!
                    } else {
                        Log.d(TAG, response.errorBody().toString())
                    }
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }
}