package com.diegolovera.movvi.viewModels

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.diegolovera.movvi.data.MovieBoundaryCallback
import com.diegolovera.movvi.data.models.Movie
import com.diegolovera.movvi.data.repositories.MovieRepository
import com.diegolovera.movvi.utils.PageUtils
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager



class PopularMoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: MovieRepository = MovieRepository.getInstance(application)
    private val dataSourceFactory: DataSource.Factory<Int, Movie> = mRepository.allPopularMovies()
    private val cm = application.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPageSize(MovieBoundaryCallback.PAGE_SIZE)
        .build()

    val movies = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        .setBoundaryCallback(
            MovieBoundaryCallback(application,
                MovieBoundaryCallback.LoadType.POPULAR))
        .build()

    fun refresh() {
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected) {
            AsyncTask.execute {
                PageUtils.popularPage = 0
                mRepository.deleteAllPopular()
                mRepository.refresh()
            }
        } else {
            movies.value!!.dataSource.invalidate()
        }
    }
}