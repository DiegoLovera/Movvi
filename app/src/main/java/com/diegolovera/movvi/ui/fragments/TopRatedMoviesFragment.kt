package com.diegolovera.movvi.ui.fragments


import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PageKeyedDataSource
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.diegolovera.movvi.R
import com.diegolovera.movvi.data.models.Movie
import com.diegolovera.movvi.ui.adapters.TopRatedMovieItemAdapter
import com.diegolovera.movvi.viewModels.TopRatedMoviesViewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TopRatedMoviesFragment : Fragment() {

    private lateinit var mViewModel: TopRatedMoviesViewModel
    private lateinit var mAdapter: TopRatedMovieItemAdapter
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mSwipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(TopRatedMoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_top_rated_movies, container, false)
        mSwipeContainer = v.findViewById(R.id.top_rated_movies_refresh)
        mSwipeContainer.setOnRefreshListener { mViewModel.refresh() }
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
            R.color.colorPrimary,
            R.color.colorPrimaryDark,
            R.color.colorAccent)

        val mRecycler = v.findViewById<RecyclerView>(R.id.top_rated_movies_recycler)
        mRecycler.setHasFixedSize(true)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecycler.layoutManager = LinearLayoutManager(context)
        } else {
            mRecycler.layoutManager = GridLayoutManager(context, 2)
        }
        mAdapter = context?.let { TopRatedMovieItemAdapter(it) }!!
        mRecycler.adapter = mAdapter

        mViewModel.movies.observe(this, Observer {
            mAdapter.submitList(it)
            mSwipeContainer.isRefreshing = false
        })
        return v
    }
}
