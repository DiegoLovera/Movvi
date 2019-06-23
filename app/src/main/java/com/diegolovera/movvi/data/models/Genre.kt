package com.diegolovera.movvi.data.models

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre",
    primaryKeys = [ "id", "movieId" ],
    indices = [Index("movieId")],
    foreignKeys = [ForeignKey(entity = Movie::class,
        parentColumns = ["id"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    )])
class Genre(@Expose
            @SerializedName("id")
            val id: Long,

            @Expose
            @SerializedName("name")
            val name: String,

            var movieId: Long = 0)
