package com.example.beerlistapp.data.mappers

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.beerlistapp.data.local.BeerDao
import com.example.beerlistapp.data.local.BeerDatabase
import com.example.beerlistapp.data.local.BeerEntity
import com.example.beerlistapp.data.remote.BeerApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerApi: BeerApi,
    private val beerDb: BeerDatabase
): RemoteMediator<Int, BeerEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {

            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND ->  return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem  = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val beers = beerApi.getBeers(
                page = loadKey,
                per_page = state.config.pageSize
            )

            beerDb.withTransaction {
                if(loadType == LoadType.REFRESH){
                    beerDb.beerDao.clearAll()
                }
                beerDb.beerDao.upsertAll(beers.map { it.toBeerEntity() })
            }

            MediatorResult.Success(
                endOfPaginationReached = beers.isEmpty()
            )

        }catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}