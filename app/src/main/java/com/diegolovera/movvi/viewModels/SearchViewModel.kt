package com.diegolovera.movvi.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.diegolovera.movvi.data.models.UniqueGenre
import com.diegolovera.movvi.data.repositories.GenreRepository

class SearchViewModel(application: Application): AndroidViewModel(application) {
    private val mRepository: GenreRepository = GenreRepository.getInstance(application)
    val genres: LiveData<List<UniqueGenre>> = mRepository.getAllGenres()
}