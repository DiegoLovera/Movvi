package com.diegolovera.movvi.api.responses

import com.diegolovera.movvi.data.Movie
import com.google.gson.annotations.SerializedName

class GetMoviesResponse(@SerializedName("page") var page: Int,
                        @SerializedName("total_results") var totalResults: Int,
                        @SerializedName("total_pages") var totalPage: Int,
                        @SerializedName("results") var results: List<Movie>)