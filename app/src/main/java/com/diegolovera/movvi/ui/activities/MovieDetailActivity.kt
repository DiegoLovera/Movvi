package com.diegolovera.movvi.ui.activities

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.icu.text.NumberFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.forEach
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import com.diegolovera.movvi.R
import com.diegolovera.movvi.data.models.Genre
import com.diegolovera.movvi.data.models.MovieDetailsRelation
import com.diegolovera.movvi.utils.DateUtils
import com.diegolovera.movvi.utils.FlagsUtils
import com.diegolovera.movvi.utils.MoviesColorUtils
import com.diegolovera.movvi.utils.ViewUtils
import com.diegolovera.movvi.viewModels.MovieDetailViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlin.math.abs

class MovieDetailActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {
    companion object {
        const val EXTRA_MOVIE_ID: String = "EXTRA_MOVIE_ID"
        const val EXTRA_MOVIE_LOAD_TYPE: String = "EXTRA_MOVIE_LOAD_TYPE"
    }

    private lateinit var mViewModel: MovieDetailViewModel
    private lateinit var mMovie: MovieDetailsRelation

    private lateinit var mCollapsingToolbar: CollapsingToolbarLayout
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mImagePosterCardView: MaterialCardView
    private lateinit var mCoverImageView: ImageView
    private lateinit var mToolbar: Toolbar
    private lateinit var mCardViewContent: MaterialCardView

    private lateinit var mImagePoster: ImageView
    private lateinit var mRatingBar: RatingBar
    private lateinit var mTextRating: TextView
    private lateinit var mTextMovieTagLine: TextView
    private lateinit var mTextMovieOverview: TextView
    private lateinit var mTextMovieRelease: TextView
    private lateinit var mTextMovieLength: TextView
    private lateinit var mTextMovieFlags: TextView
    private lateinit var mTextMovieBudget: TextView
    private lateinit var mTextMovieRevenue: TextView
    private lateinit var mTextMovieStatus: TextView
    private lateinit var mChipMovieGenresGroup: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        mViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        mCollapsingToolbar = findViewById(R.id.movie_details_collapsing)
        mCollapsingToolbar.title = ""
        mCoverImageView = findViewById(R.id.movie_details_backdrop)
        mProgressBar = findViewById(R.id.movie_details_progress)
        mImagePoster = findViewById(R.id.movie_details_poster)
        mImagePosterCardView = findViewById(R.id.poster_image_cardview)
        mTextMovieTagLine = findViewById(R.id.title_movie_detail_text)
        mTextMovieOverview = findViewById(R.id.overview_movie_detail_text)
        mRatingBar = findViewById(R.id.movie_rating_bar)
        mTextRating = findViewById(R.id.movie_rating_text)
        mTextMovieStatus = findViewById(R.id.movie_status_text)
        mToolbar = findViewById(R.id.movie_details_toolbar)
        mToolbar.title = ""
        mChipMovieGenresGroup = findViewById(R.id.movie_genre_chips)
        mCardViewContent = findViewById(R.id.flexible_movie_detail_cardview)
        mTextMovieRelease = findViewById(R.id.release_movie_detail_text)
        mTextMovieLength = findViewById(R.id.length_movie_detail)
        mTextMovieFlags = findViewById(R.id.flags_movie_detail_text)
        mTextMovieBudget = findViewById(R.id.budget_movie_detail_text)
        mTextMovieRevenue = findViewById(R.id.revenue_movie_detail_text)
        val appbarLayout = findViewById<AppBarLayout>(R.id.movies_details_appbar)

        appbarLayout.addOnOffsetChangedListener(this)
        setSupportActionBar(mToolbar)
        mCardViewContent.visibility = View.INVISIBLE
        mCardViewContent.animate()
            .scaleY(0f).scaleX(0f)
            .setDuration(0)
            .start()

        mRatingBar.max = 10
        mRatingBar.numStars = 5
        mRatingBar.stepSize = 0.1f
        mMaxScrollSize = appbarLayout.totalScrollRange

        mViewModel.getMovieDetails(intent.getLongExtra(EXTRA_MOVIE_ID, 0L), intent.getIntExtra(EXTRA_MOVIE_LOAD_TYPE, 0))
            .observe(this, Observer<MovieDetailsRelation> {
                if (it != null) {
                    if (intent.getLongExtra(EXTRA_MOVIE_ID, 0L) == it.movieDetails!!.id) {
                        mMovie = it
                        setUpToolbar((it.movieDetails!!.backdropPath?: ""))
                        Picasso.get()
                            .load("https://image.tmdb.org/t/p/w500" + it.movieDetails!!.posterPath)
                            .placeholder(R.color.imagePlaceHolderColor)
                            .error(R.color.colorPrimary)
                            .into(mImagePoster)
                        mTextMovieTagLine.text = it.movieDetails!!.tagline
                        mTextMovieOverview.text = it.movieDetails!!.overview
                        mRatingBar.rating = it.movieDetails!!.voteAverage/2
                        var countriesString = ""
                        it.productionCountries.forEach{ country ->
                            countriesString += FlagsUtils.getFlag(country.iso)
                        }
                        mTextMovieFlags.text = countriesString
                        mTextMovieRelease.text = DateUtils.toSimpleString(it.movieDetails!!.releaseDate)
                        mTextMovieLength.text = it.movieDetails!!.runtime.toString() + "m"
                        mTextRating.text = "(${it.movieDetails!!.voteCount})"
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            val nf = NumberFormat.getInstance(NumberFormat.CASHCURRENCYSTYLE)
                            mTextMovieBudget.text = nf.format(it.movieDetails!!.budget)
                            mTextMovieRevenue.text = nf.format(it.movieDetails!!.revenue)
                        } else {
                            mTextMovieBudget.height = 0
                            mTextMovieRevenue.height = 0
                        }
                        mTextMovieStatus.text = it.movieDetails!!.status
                        when {
                            it.movieDetails!!.tagline.isEmpty() -> mTextMovieTagLine.visibility = View.GONE
                            else -> mTextMovieTagLine.visibility = View.VISIBLE
                        }
                        mChipMovieGenresGroup.removeAllViews()
                        val finalGenresList: ArrayList<Genre> = ArrayList()
                        it.genres.forEach { genre ->
                            var isInList = false
                            finalGenresList.forEach { genreFiltered ->
                                if (genre.id == genreFiltered.id) {
                                    isInList = true
                                }
                            }
                            if (!isInList) {
                                finalGenresList.add(genre)
                            }
                        }
                        finalGenresList.forEach {finalGenre ->
                            mChipMovieGenresGroup.addView(ViewUtils.createChip(finalGenre.name, this))
                        }
                        mChipMovieGenresGroup.setChipSpacing(8)
                    }
                }
            })
    }

    private val percentToTriggerHiding = 90
    private var mIsAvatarShown = true
    private var mMaxScrollSize: Int = 0
    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.totalScrollRange

        val percentage = abs(i) * 100 / mMaxScrollSize

        if (percentage >= percentToTriggerHiding && mIsAvatarShown) {
            mIsAvatarShown = false
            mCollapsingToolbar.title = mMovie.movieDetails!!.title
            mImagePosterCardView.animate()
                .scaleY(0f).scaleX(0f)
                .setDuration(200)
                .start()
        }

        if (percentage <= percentToTriggerHiding && !mIsAvatarShown) {
            mIsAvatarShown = true
            setToolbarTitle(mMovie.movieDetails!!.title)
            mImagePosterCardView.animate()
                .scaleY(1f).scaleX(1f)
                .start()
        }
    }

    private fun setToolbarTitle(title: String) {
        mCollapsingToolbar.title = if (title.length > 14) {
            title.substring(0, 12) + "..."
        } else {
            title
        }
    }

    private val picassoTarget : Target = object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            val blurredBitmap = MoviesColorUtils.fastBlur(bitmap, 1f, 5)
            Palette.from(bitmap).generate { palette ->
                val finalColor = MoviesColorUtils.getColor(palette, 0)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.statusBarColor = finalColor
                }
                val drawable: LayerDrawable = mRatingBar.progressDrawable as LayerDrawable
                setRatingStarColor(drawable, finalColor)
                mCollapsingToolbar.setContentScrimColor(ColorUtils.setAlphaComponent(finalColor, 122))
                mChipMovieGenresGroup.forEach { chip: View ->
                    chip as Chip
                    chip.setTextColor(Color.WHITE)
                    chip.chipBackgroundColor = ColorStateList.valueOf(finalColor)
                }

                mCardViewContent.visibility = View.VISIBLE
                mCardViewContent.animate()
                    .scaleY(1f).scaleX(1f)
                    .setDuration(400)
                    .start()
                setToolbarTitle(mMovie.movieDetails!!.title)
                mProgressBar.visibility = View.GONE
                mCoverImageView.setImageBitmap(blurredBitmap)
            }
        }

        override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {
            mProgressBar.visibility = View.GONE
            mCardViewContent.visibility = View.VISIBLE
            mCardViewContent.animate()
                .scaleY(1f).scaleX(1f)
                .setDuration(400)
                .start()
            setToolbarTitle(mMovie.movieDetails!!.title)
            mCoverImageView.setImageDrawable(errorDrawable)
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable) {
            mCoverImageView.setImageDrawable(placeHolderDrawable)
        }
    }

    private fun setUpToolbar(coverUrl: String) {
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500$coverUrl")
            .placeholder(R.color.imagePlaceHolderColor)
            .error(R.color.colorAccent)
            .into(picassoTarget)
    }

    private fun setRatingStarColor(drawable: Drawable, @ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DrawableCompat.setTint(drawable, color)
        } else {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }
}
