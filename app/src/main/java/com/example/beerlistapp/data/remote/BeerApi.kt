package com.example.beerlistapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface BeerApi {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page:Int,
        @Query("per_page") per_page:Int
    ):List<BeerDto>
    companion object{
        val BEER_API = "https://api.punkapi.com/v2/"
    }
}