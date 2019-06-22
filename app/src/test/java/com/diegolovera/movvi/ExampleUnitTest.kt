package com.diegolovera.movvi

import com.diegolovera.movvi.api.TheMovieApiClient
import com.diegolovera.movvi.api.responses.GetMoviesResponse
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private var moviesResponse: GetMoviesResponse? = null
    @Test
    fun webServiceTest() {
        TheMovieApiClient.service
            .getPopularMovies(TheMovieApiClient.API_KEY, 1)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                    if (response.isSuccessful && response.code() == 200) {
                        moviesResponse = response.body()!!
                    }
                }

            })
        Thread.sleep(2000)
        val i = if (moviesResponse != null) {
            1
        } else {
            2
        }
        assertEquals(1, i)
    }
}
