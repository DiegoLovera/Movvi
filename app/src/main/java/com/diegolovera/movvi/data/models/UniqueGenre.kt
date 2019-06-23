package com.diegolovera.movvi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "unique_genre")
class UniqueGenre(@PrimaryKey
                  @Expose
                  val id: Long,

                  @Expose
                  val name: String)