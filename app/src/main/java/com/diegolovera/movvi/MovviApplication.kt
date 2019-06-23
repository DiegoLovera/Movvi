package com.diegolovera.movvi

import com.facebook.stetho.Stetho

class MovviApplication : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        /*MovieRepository.getInstance(this).getPopularMovies(1)
        MovieRepository.getInstance(this).getTopRatedMovies(1)
        MovieRepository.getInstance(this).getUpcomingMovies(1)*/
    }
}