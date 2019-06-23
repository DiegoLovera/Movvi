package com.diegolovera.movvi.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.diegolovera.movvi.data.models.ProductionCountry

@Dao
interface ProductionCountryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(productionCountry: ProductionCountry): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg productionCountry: ProductionCountry)
}