package com.diegolovera.movvi.data.models

import androidx.room.Embedded
import androidx.room.Relation

class MovieDetailsRelation {
    @Embedded
    var movieDetails: MovieDetails? = null
    @Relation(parentColumn = "id", entityColumn = "movieId")
    var genres: List<Genre> = ArrayList()
    @Relation(parentColumn = "id", entityColumn = "movieId")
    var productionCountries: List<ProductionCountry> = ArrayList()
}