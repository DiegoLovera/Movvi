package com.diegolovera.movvi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "movie", primaryKeys = [ "id", "loadType" ])
class Movie(@SerializedName("id")
            @Expose
            val id: Long,

            @SerializedName("vote_count")
            @Expose
            val voteCount: Int?,

            @SerializedName("video")
            @Expose
            val video: Boolean?,

            @SerializedName("vote_average")
            @Expose
            val voteAverage: Float?,

            @SerializedName("title")
            @Expose
            val title: String?,

            @SerializedName("popularity")
            @Expose
            val popularity: Float?,

            @SerializedName("poster_path")
            @Expose
            val posterPath: String?,

            @SerializedName("original_language")
            @Expose
            val originalLanguage: String?,

            @SerializedName("original_title")
            @Expose
            val originalTitle: String?,

            @SerializedName("backdrop_path")
            @Expose
            val backdropPath: String?,

            @SerializedName("adult")
            @Expose
            val adult: Boolean?,

            @SerializedName("overview")
            @Expose
            val overview: String?,

            @SerializedName("release_date")
            @Expose
            val releaseDate: Date?,

            var loadType: Int = 0)