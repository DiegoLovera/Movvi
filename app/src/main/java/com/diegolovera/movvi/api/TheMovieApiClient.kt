package com.diegolovera.movvi.api

import com.diegolovera.movvi.data.models.MovieDetails
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

object TheMovieApiClient {
    private const val BASE_URL = "https://api.themoviedb.org/"
    const val API_KEY = "f6a645a26832545d6a571359dafdf621"

    private val gson = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build())
        .addConverterFactory(GsonConverterFactory.create(gson))

    private var retrofit = builder.build()

    /**
     * Creates a service using the retrofit builder.
     * @param serviceClass Class
     * @param <S> S
     * @return Returns the service
    </S> */
    private fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    var service: TheMovieApiServices = createService(TheMovieApiServices::class.java)

    fun getMovieDetails(movieId: Long): MovieDetails? {
        return try {
            val response: Response<MovieDetails> = service.getMovieDetails(
                movieId, API_KEY)
                .execute()

            if (response.isSuccessful && response.code() == 200) {
                response.body()!!
            } else {
                null
            }
        } catch (ex: UnknownHostException) {
            null
        }
    }
}