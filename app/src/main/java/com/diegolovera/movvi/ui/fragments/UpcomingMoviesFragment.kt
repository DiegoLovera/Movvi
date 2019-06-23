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
import com.diegolovera.movvi.ui.adapters.UpcomingMovieItemAdapter
import com.diegolovera.movvi.utils.ScrollToTop
import com.diegolovera.movvi.viewModels.UpcomingMoviesViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class UpcomingMoviesFragment : Fragment() {
    private lateinit var mViewModel: UpcomingMoviesViewModel
    private lateinit var mAdapter: UpcomingMovieItemAdapter
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mSwipeContainer: SwipeRefreshLayout
    private lateinit var mRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(UpcomingMoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_upcoming_movies, container, false)
        mSwipeContainer = v.findViewById(R.id.upcoming_movies_refresh)
        mSwipeContainer.setOnRefreshListener {
            mRecycler.scrollToPosition(0)
            mViewModel.refresh()
        }
        mSwipeContainer.setColorSchemeResources(
            R.color.colorAccent,
            R.color.colorPrimary,
            R.color.colorPrimaryDark,
            R.color.colorAccent
        )

        mRecycler = v.findViewById(R.id.upcoming_movies_recycler)
        mRecycler.setHasFixedSize(true)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecycler.layoutManager = LinearLayoutManager(context)
        } else {
            mRecycler.layoutManager = GridLayoutManager(context, 2)
        }
        mAdapter = context?.let { UpcomingMovieItemAdapter(it) }!!
        mRecycler.adapter = mAdapter

        mViewModel.movies.observe(this, Observer {
            mAdapter.submitList(it)
            mSwipeContainer.isRefreshing = false
        })
        return v
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun scrollOnTop(who: ScrollToTop) {
        if (who.id == R.id.upcoming) {
            mRecycler.smoothScrollToPosition(0)
        }
    }
}
