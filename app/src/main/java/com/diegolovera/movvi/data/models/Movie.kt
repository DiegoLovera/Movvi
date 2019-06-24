package com.diegolovera.movvi.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "movie",
    primaryKeys = [ "id", "loadType" ])
class Movie() {
    @SerializedName("id")
    @Expose
    var id: Long = 0

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = 0

    @SerializedName("video")
    @Expose
    var video: Boolean? = false

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = 0f

    @SerializedName("title")
    @Expose
    var title: String? = ""

    @SerializedName("popularity")
    @Expose
    var popularity: Float? = 0f

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = ""

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = ""

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = ""

    @SerializedName("genre_ids")
    @Expose
    @Ignore
    var genres: List<Long> = ArrayList()

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = ""

    @SerializedName("adult")
    @Expose
    var adult: Boolean? = false

    @SerializedName("overview")
    @Expose
    var overview: String? = ""

    @SerializedName("release_date")
    @Expose
    var releaseDate: Date? = Date()

    var loadType: Int = 0

}