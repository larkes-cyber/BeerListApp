package com.example.beerlistapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface BeerDao {
    @Upsert
    fun upsertAll(beerList:List<BeerEntity>)

    @Query("SELECT * FROM BeerEntity")
    fun pagingSource(): PagingSource<Int, BeerEntity>

    @Query("DELETE FROM BeerEntity")
    suspend fun clearAll()
}