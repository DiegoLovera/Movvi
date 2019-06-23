package com.diegolovera.movvi.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.diegolovera.movvi.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_search_movies, container, false)
        mChipMovieGenresGroup = v.findViewById(R.id.genres_filter_chip_group)

        mViewModel.genres.observe(this, Observer{
            mChipMovieGenresGroup.removeAllViews()
            if (context != null) {
                it.forEach { genre ->
                    val chip = ViewUtils.createChip(genre.name, true, context!!)
                    chip.id = genre.id.toInt()
                    chip.setOnCheckedChangeListener { buttonView, isChecked ->
                        Toast.makeText(context!!, buttonView.id.toString() + " is " + isChecked.toString(), Toast.LENGTH_SHORT).show()
                    }
                    mChipMovieGenresGroup.addView(chip)
                }
            }
            mChipMovieGenresGroup.setChipSpacing(8)
        })

        return v
    }


}
