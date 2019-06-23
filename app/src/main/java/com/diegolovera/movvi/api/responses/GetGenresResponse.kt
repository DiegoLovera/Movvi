package com.diegolovera.movvi.api.responses

import com.diegolovera.movvi.data.models.UniqueGenre
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetGenresResponse(@SerializedName("genres")
                        @Expose
                        var results: List<UniqueGenre>)