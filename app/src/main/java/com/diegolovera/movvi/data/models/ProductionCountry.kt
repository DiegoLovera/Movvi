package com.diegolovera.movvi.data.models

import com.google.gson.annotations.SerializedName

class ProductionCountry(@SerializedName("iso_3166_1") var iso: String,
                        @SerializedName("name") var name: String)