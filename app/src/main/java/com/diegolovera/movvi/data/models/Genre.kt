package com.diegolovera.movvi.data.models

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre",
    indices = [Index("movieId")],
    foreignKeys = [ForeignKey(entity = MovieDetails::class,
        parentColumns = ["id"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    )])
class Genre(@PrimaryKey
            @Expose
            @SerializedName("id")
            val id: Long,

            @Expose
            @SerializedName("name")
            val name: String,

            var movieId: Long = 0)
