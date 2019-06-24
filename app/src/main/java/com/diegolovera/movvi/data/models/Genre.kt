package com.diegolovera.movvi.data.models

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre",
    primaryKeys = [ "id", "movieId", "loadType" ],
    indices = [Index("movieId")],
    foreignKeys = [ForeignKey(entity = Movie::class,
        parentColumns = ["id", "loadType"],
        childColumns = ["movieId", "loadType"],
        onDelete = ForeignKey.CASCADE
    )])
class Genre(@Expose
            @SerializedName("id")
            val id: Long,

            @Expose
            @SerializedName("name")
            val name: String,

            var loadType: Int = 0,

            var movieId: Long = 0)
