package com.diegolovera.movvi.api

import com.diegolovera.movvi.api.responses.GetMoviesResponse
import com.diegolovera.movvi.data.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApiServices {
    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String,
                         @Query("page") page: Int): Call<GetMoviesResponse>

    @GET("/3/movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String,
                          @Query("page") page: Int): Call<GetMoviesResponse>

    @GET("/3/movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey: String,
                          @Query("page") page: Int): Call<GetMoviesResponse>

    @GET("/3/movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: Long,
                        @Query("api_key") apiKey: String): Call<MovieDetails>
}