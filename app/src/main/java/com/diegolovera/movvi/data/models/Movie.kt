package com.diegolovera.movvi.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

class Movie(@SerializedName("vote_count") var voteCount: Int,
            @SerializedName("id") var id: Long,
            @SerializedName("video") var video: Boolean,
            @SerializedName("vote_average") var voteAverage: Float,
            @SerializedName("title") var title: String,
            @SerializedName("popularity") var popularity: Float,
            @SerializedName("poster_path") var posterPath: String,
            @SerializedName("original_language") var originalLanguage: String,
            @SerializedName("original_title") var originalTitle: String,
            @SerializedName("backdrop_path") var backdropPath: String,
            @SerializedName("adult") var adult: Boolean,
            @SerializedName("overview") var overview: String,
            @SerializedName("release_date") var releaseDate: Date)