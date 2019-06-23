package com.diegolovera.movvi.viewModels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.diegolovera.movvi.data.MovieBoundaryCallback
import com.diegolovera.movvi.data.models.Movie
import com.diegolovera.movvi.data.repositories.MovieRepository
import com.diegolovera.movvi.utils.PageUtils

class UpcomingMoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: MovieRepository = MovieRepository.getInstance(application)
    private val dataSourceFactory: DataSource.Factory<Int, Movie>
    private val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    init {
        dataSourceFactory = mRepository.allUpcomingMovies()
    }

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPageSize(MovieBoundaryCallback.PAGE_SIZE)
        .build()

    val movies = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        .setBoundaryCallback(
            MovieBoundaryCallback(application,
                MovieBoundaryCallback.LoadType.UPCOMING))
        .build()

    fun refresh() {
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected) {
            AsyncTask.execute {
                PageUtils.upcomingPage = 0
                mRepository.deleteAllUpcoming()
                mRepository.refresh()
            }
        } else {
            movies.value!!.dataSource.invalidate()
        }
    }
}
