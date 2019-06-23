package com.diegolovera.movvi.ui.fragments


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegolovera.movvi.R
import com.diegolovera.movvi.ui.adapters.SearchMovieItemAdapter
import com.diegolovera.movvi.utils.ViewUtils
import com.diegolovera.movvi.viewModels.SearchViewModel
import com.google.android.material.chip.ChipGroup

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchMoviesFragment : Fragment() {
    private lateinit var mViewModel: SearchViewModel
    private lateinit var mChipMovieGenresGroup: ChipGroup
    private lateinit var mRecycler: RecyclerView
    private lateinit var mAdapter: SearchMovieItemAdapter
    private lateinit var mFilterValues: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        mFilterValues = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_search_movies, container, false)
        mChipMovieGenresGroup = v.findViewById(R.id.genres_filter_chip_group)

        mRecycler = v.findViewById(R.id.filter_list_recycler)
        mRecycler.setHasFixedSize(true)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecycler.layoutManager = LinearLayoutManager(context)
        } else {
            mRecycler.layoutManager = GridLayoutManager(context, 2)
        }
        mAdapter = context?.let { SearchMovieItemAdapter(it, ArrayList()) }!!
        mRecycler.adapter = mAdapter

        mViewModel.genres.observe(this, Observer{ uniqueGenreList ->
            mChipMovieGenresGroup.removeAllViews()
            if (context != null) {
                uniqueGenreList.forEach { genre ->
                    val chip = ViewUtils.createChip(genre.name, true, context!!)
                    chip.id = genre.id.toInt()
                    chip.setOnCheckedChangeListener { buttonView, isChecked ->
                        if (isChecked) {
                            mFilterValues.add(buttonView.id)
                        } else {
                            mFilterValues.remove(buttonView.id)
                        }
                        mViewModel.filterMovies(mFilterValues)
                    }
                    mChipMovieGenresGroup.addView(chip)
                }
            }
            mChipMovieGenresGroup.setChipSpacing(8)
        })

        mViewModel.getMoviesFiltered().observe(this, Observer {
            mAdapter.setData(it)
        })

        return v
    }


}
