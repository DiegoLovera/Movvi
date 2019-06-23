package com.diegolovera.movvi

import com.diegolovera.movvi.data.repositories.MovieRepository

class MovviApplication : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        /*MovieRepository.getInstance(this).getPopularMovies(1)
        MovieRepository.getInstance(this).getTopRatedMovies(1)
        MovieRepository.getInstance(this).getUpcomingMovies(1)*/
    }
}