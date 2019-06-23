package com.diegolovera.movvi.ui.fragments


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.diegolovera.movvi.R
import com.diegolovera.movvi.ui.adapters.PopularMovieItemAdapter
import com.diegolovera.movvi.utils.ScrollToTop
import com.diegolovera.movvi.viewModels.PopularMoviesViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * A simple [Fragment] subclass.
 *
 */
class PopularMoviesFragment : Fragment() {

    private lateinit var mViewModel: PopularMoviesViewModel
    private lateinit var mAdapter: PopularMovieItemAdapter
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mSwipeContainer: SwipeRefreshLayout
    private lateinit var mRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_popular_movies, container, false)
        mSwipeContainer = v.findViewById(R.id.popular_movies_refresh)
        mSwipeContainer.setOnRefreshListener {
            mViewModel.refresh()
        }
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
            R.color.colorPrimary,
            R.color.colorPrimaryDark,
            R.color.colorAccent)

        mRecycler = v.findViewById(R.id.popular_movies_recycler)
        mRecycler.setHasFixedSize(true)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecycler.layoutManager = LinearLayoutManager(context)
        } else {
            mRecycler.layoutManager = GridLayoutManager(context, 2)
        }
        mAdapter = context?.let { PopularMovieItemAdapter(it) }!!
        mRecycler.adapter = mAdapter

        mViewModel.movies.observe(this, Observer{
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
        if (who.id == R.id.popular) {
            mRecycler.smoothScrollToPosition(0)
        }
    }
}
