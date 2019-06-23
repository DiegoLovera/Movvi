package com.diegolovera.movvi.data.sources

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.diegolovera.movvi.data.models.Movie

class PopularMovieDataSourceFactory(val context: Application) : DataSource.Factory<Int, Movie>() {

    var liveDataSource: MutableLiveData<PageKeyedDataSource<Int, Movie>>? = null
        private set

    override fun create(): DataSource<Int, Movie> {
        val itemDataSource = PopularMovieDataSource(context)

        liveDataSource = MutableLiveData()
        liveDataSource!!.postValue(itemDataSource)

        return itemDataSource
    }
}