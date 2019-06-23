package com.diegolovera.movvi.viewModels

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diegolovera.movvi.data.models.Movie
import com.diegolovera.movvi.data.models.UniqueGenre
import com.diegolovera.movvi.data.repositories.GenreRepository

class SearchViewModel(application: Application): AndroidViewModel(application) {
    private val mRepository: GenreRepository = GenreRepository.getInstance(application)
    val genres: LiveData<List<UniqueGenre>> = mRepository.getAllGenres()
    private var mMoviesList: MutableLiveData<List<Movie>> = MutableLiveData()

    fun filterMovies(filterValues: List<Int>) {
        AsyncTask.execute {
            val movies = mRepository.getMoviesByGenre(filterValues)
            mMoviesList.postValue(movies)
        }
    }

    fun getMoviesFiltered(): MutableLiveData<List<Movie>> {
        return mMoviesList
    }
}