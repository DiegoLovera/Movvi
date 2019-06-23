package com.diegolovera.movvi.data.sources

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.diegolovera.movvi.api.TheMovieApiClient
import com.diegolovera.movvi.api.responses.GetMoviesResponse
import com.diegolovera.movvi.data.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingMovieDataSource : PageKeyedDataSource<Int, Movie>() {
    private val TAG = UpcomingMovieDataSource::class.java.simpleName

    companion object {
        private const val FIRST_PAGE = 1
        const val PAGE_SIZE = 20
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        TheMovieApiClient.service
            .getUpcomingMovies(TheMovieApiClient.API_KEY, FIRST_PAGE)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.d(TAG, t.message)
                }

                override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                    if (response.isSuccessful && response.code() == 200) {
                        val data = response.body()
                        if (data != null) {
                            callback.onResult(data.results, null, FIRST_PAGE + 1)
                        }
                        response.body()!!
                    } else {
                        Log.d(TAG, response.errorBody().toString())
                    }
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TheMovieApiClient.service
            .getUpcomingMovies(TheMovieApiClient.API_KEY, (params.key))
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