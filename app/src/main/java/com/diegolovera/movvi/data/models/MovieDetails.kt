package com.diegolovera.movvi.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

class MovieDetails(@SerializedName("adult") var adult: Boolean,
                   @SerializedName("backdrop_path") var backdropPath: String,
                   @SerializedName("budget") var budget: Float,
                   @SerializedName("genres") var genres: List<Genre>,
                   @SerializedName("homepage") var homepage: String,
                   @SerializedName("id") var id: Long,
                   @SerializedName("original_language") var originalLanguage: String,
                   @SerializedName("original_title") var originalTitle: String,
                   @SerializedName("overview") var overview: String,
                   @SerializedName("popularity") var popularity: Float,
                   @SerializedName("poster_path") var posterPath: String,
                   @SerializedName("production_countries") var productionCountries: List<ProductionCountry>,
                   @SerializedName("release_date") var releaseDate: Date,
                   @SerializedName("revenue") var revenue: Float,
                   @SerializedName("runtime") var runtime: Int,
                   @SerializedName("status") var status: String,
                   @SerializedName("tagline") var tagline: String,
                   @SerializedName("title") var title: String,
                   @SerializedName("video") var video: Boolean,
                   @SerializedName("vote_average") var voteAverage: Float,
                   @SerializedName("vote_count") var voteCount: Int)