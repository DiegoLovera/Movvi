package com.diegolovera.movvi.data

import android.app.Application
import androidx.paging.PagedList
import com.diegolovera.movvi.data.models.Movie
import com.diegolovera.movvi.data.repositories.MovieRepository
import com.diegolovera.movvi.utils.PageUtils

class MovieBoundaryCallback(private val application: Application, private val loadType: LoadType) : PagedList.BoundaryCallback<Movie>() {
    companion object {
        const val PAGE_SIZE = 20
    }

    enum class LoadType(val value: Int) {
        POPULAR(1),
        TOP_RATED(2),
        UPCOMING(3)
    }

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        loadMovies(loadType)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        super.onItemAtEndLoaded(itemAtEnd)
        loadMovies(loadType)
    }

    private fun loadMovies(loadType: LoadType) {
        when (loadType) {
            LoadType.POPULAR ->  {
                PageUtils.popularPage += 1
                MovieRepository.getInstance(application).getPopularMovies(PageUtils.popularPage)
            }
            LoadType.TOP_RATED ->  {
                PageUtils.topRatedPage += 1
                MovieRepository.getInstance(application).getTopRatedMovies(PageUtils.topRatedPage)
            }
            LoadType.UPCOMING ->  {
                PageUtils.upcomingPage += 1
                MovieRepository.getInstance(application).getUpcomingMovies(PageUtils.upcomingPage)
            }
        }
    }
}