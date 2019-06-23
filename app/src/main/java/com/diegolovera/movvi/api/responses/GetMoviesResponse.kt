package com.diegolovera.movvi.api.responses

import com.diegolovera.movvi.data.models.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetMoviesResponse(@SerializedName("page")
                        @Expose
                        var page: Int,
                        @Expose
                        @SerializedName("total_results")
                        var totalResults: Int,
                        @SerializedName("total_pages")
                        @Expose
                        var totalPage: Int,
                        @SerializedName("results")
                        @Expose
                        var results: List<Movie>)