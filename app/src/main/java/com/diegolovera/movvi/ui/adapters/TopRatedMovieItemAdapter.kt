package com.diegolovera.movvi.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diegolovera.movvi.R
import com.diegolovera.movvi.data.models.Movie
import com.diegolovera.movvi.ui.activities.MovieDetailActivity
import com.diegolovera.movvi.utils.DateUtils
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class TopRatedMovieItemAdapter(context: Context)
    : PagedListAdapter<Movie, TopRatedMovieItemAdapter.ViewHolder>(DIFF_CALLBACK) {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movie = getItem(position)
        if (holder.movie != null) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + (holder.movie!!.posterPath ?: ""))
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .into(holder.mImagePoster)
            holder.mTextTitle.text = holder.movie!!.title ?: ""
            val finalRate: Float = if (holder.movie!!.voteAverage != null) {
                holder.movie!!.voteAverage!! * 10
            } else {
                0f
            }
            val rateString = if (finalRate == 0f) {
                "N/R"
            } else {
                (finalRate).roundToInt().toString()
            }
            holder.mTextPercentage.text = rateString
            holder.mTextRelease.text = DateUtils.toSimpleString(holder.movie!!.releaseDate!!)
            holder.mTextOverview.text = holder.movie!!.overview ?: ""
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movie: Movie? = null
        private var mCard: MaterialCardView = itemView.findViewById(R.id.background_movie_card)
        var mImagePoster: ImageView = itemView.findViewById(R.id.poster_movie_image)
        var mTextTitle: TextView = itemView.findViewById(R.id.title_movie_text)
        var mTextPercentage: TextView = itemView.findViewById(R.id.percentage_movie_text)
        var mTextOverview: TextView = itemView.findViewById(R.id.overview_movie_text)
        var mTextRelease: TextView = itemView.findViewById(R.id.release_movie_text)

        init {
            mCard.setOnClickListener { v -> openDetails(v.context) }
        }

        private fun openDetails(c: Context) {
            val intent = Intent(c, MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movie!!.id)
            c.startActivity(intent)
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return (oldItem.id == newItem.id && oldItem.loadType == newItem.loadType)
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return (oldItem.title == newItem.title
                        && oldItem.overview == newItem.overview
                        && oldItem.voteAverage == newItem.voteAverage
                        && oldItem.loadType == newItem.loadType)
            }
        }
    }
}