package com.diegolovera.movvi.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.diegolovera.movvi.data.models.MovieDetailsRelation
import com.diegolovera.movvi.data.repositories.MovieDetailRepository

class MovieDetailViewModel(application: Application): AndroidViewModel(application) {
    private val dataRepository: MovieDetailRepository = MovieDetailRepository.getInstance(application)

    fun getMovieDetails(movieId: Long): LiveData<MovieDetailsRelation> {
        return dataRepository.getMovieDetails(movieId)
    }
}