package com.diegolovera.movvi.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.diegolovera.movvi.data.models.UniqueGenre

@Dao
interface UniqueGenreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(genre: UniqueGenre): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg genre: UniqueGenre)

    @Query("SELECT * FROM unique_genre")
    fun selectDistinct(): LiveData<List<UniqueGenre>>
}