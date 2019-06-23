package com.diegolovera.movvi.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "production_country",
    indices = [Index("movieId")],
    foreignKeys = [ForeignKey(entity = MovieDetails::class,
        parentColumns = ["id"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    )])
class ProductionCountry(@field:PrimaryKey
                        @field:SerializedName("iso_3166_1")
                        @field:Expose
                        val iso: String,

                        @field:SerializedName("name")
                        @field:Expose
                        val name: String,

                        var movieId: Long = 0)