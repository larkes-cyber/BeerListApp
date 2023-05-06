package com.example.beerlistapp.data.mappers

import com.example.beerlistapp.data.local.BeerEntity
import com.example.beerlistapp.data.remote.BeerDto

fun BeerDto.toBeerEntity():BeerEntity{
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        first_brewed = first_brewed,
        image_url = image_url
    )
}