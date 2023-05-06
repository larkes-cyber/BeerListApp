package com.example.beerlistapp.data.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.beerlistapp.data.local.BeerDao
import com.example.beerlistapp.data.local.BeerDatabase
import com.example.beerlistapp.data.local.BeerEntity
import com.example.beerlistapp.data.mappers.BeerRemoteMediator
import com.example.beerlistapp.data.remote.BeerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBeerDatabase(@ApplicationContext context: Context):BeerDatabase{
        return Room.databaseBuilder(
            context,
            BeerDatabase::class.java,
            "beers.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBeerApi():BeerApi{
        return Retrofit.Builder()
            .baseUrl(BeerApi.BEER_API)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideBeerPager(
        beerDatabase: BeerDatabase,
        beerApi: BeerApi
    ):Pager<Int, BeerEntity>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRemoteMediator(
                beerDb = beerDatabase,
                beerApi = beerApi
            ),
            pagingSourceFactory = {
                beerDatabase.beerDao.pagingSource()
            }
        )
    }


}